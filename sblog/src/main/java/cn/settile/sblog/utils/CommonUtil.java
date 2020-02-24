package cn.settile.sblog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** 检查工具类
 * @author : lzjyz
 * @date : 2020-02-01 21:38
 */
public class CommonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();
    public static Random random = new Random();

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }

    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Map<String, List<String>> parseRole(String roleStr) throws IOException {
        objectMapper = new ObjectMapper();
        Map<String,List<String>> map = objectMapper.readValue(roleStr, HashMap.class);
        return map;
    }

    /** 定义一个获取随机验证码的方法
     * @param n 验证码长度
     * @return 验证码串
     */
    public static String getCode(int n) {
        //支持的验证码字符序列
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            //返回[0,string.length)范围的int值    作用：保存下标
            int index = random.nextInt(string.length());
            //charAt() : 返回指定索引处的 char 值   ==》赋值给char字符对象ch
            char ch = string.charAt(index);
            // append(char c) :将 char 参数的字符串表示形式追加到此序列  ==》即将每次获取的ch值作拼接
            sb.append(ch);
        }
        return sb.toString();
    }
}
