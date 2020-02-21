package cn.settile.sblog.filter.aspect;

import cn.settile.sblog.exception.result.Result;
import cn.settile.sblog.utils.RedisUtil;
import cn.settile.sblog.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/** 短时间内IP访问接口次数限制
 * @author : lzjyz
 * @date : 2020-02-20 18:31
 */
@Aspect
@Order
@Component
@Slf4j
public class AccessLimitAspect {
    @Autowired
    private RedisUtil redisUtil;

    public static final String IP_LIMIT_CACHENAME = "IP_LIMIT_CACHE";
    public static final String IP_LIMIT_CACHE = "IP_LIMIT_CACHE::";

    @Pointcut(value = "@annotation(cn.settile.sblog.filter.aspect.AccessLimit)" )
    public void pointCut(){};

    @Around(value = "pointCut())")
    public Object requestLimitAround(ProceedingJoinPoint pjp) {

        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        AccessLimit limit = methodSignature.getMethod().getAnnotation(AccessLimit.class);

        // 限制访问次数
        int count = limit.count();
        long time = limit.timeUnit().toSeconds(limit.time());
        // 获取 request ， 然后获取访问 ip
        HttpServletRequest request = SpringContextUtil.getHttpServletRequest();
        String requestIp = AccessLimitAspect.getRequestIp(request);
        if(StringUtils.isEmpty(requestIp)){
            return "非法访问！ip不能为空！";
        }

        //获取request 的请求路径
        String url = request.getRequestURI();
        String key = IP_LIMIT_CACHE +url + ":" + requestIp;

        int visitCount = 0;
        Object c = null;
        if((c = redisUtil.get(key))!=null) {
            visitCount = (int) c;
        }

        if( visitCount >= count ){
            return Result.REFUSE;
        }
        // 将访问存进缓存
        redisUtil.set(key,++visitCount,time);

        //获取传入目标方法的参数
        Object[] args = pjp.getArgs();
        Object result = null;
        try {
            // 执行访问并返回数据
            result = pjp.proceed(args);
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public static String getRequestIp(HttpServletRequest request){
        // 获取请求IP
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equals(ip)){
            ip = "" + request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equals(ip)){
            ip = "" + request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equals(ip)){
            ip = "" + request.getRemoteAddr();
        }

        return ip;
    }

}
