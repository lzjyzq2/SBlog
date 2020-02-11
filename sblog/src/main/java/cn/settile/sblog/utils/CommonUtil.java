package cn.settile.sblog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 检查工具类
 * @author : lzjyz
 * @date : 2020-02-01 21:38
 */
public class CommonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

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
}
