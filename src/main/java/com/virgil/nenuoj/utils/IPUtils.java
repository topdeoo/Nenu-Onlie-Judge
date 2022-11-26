package com.virgil.nenuoj.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

@Slf4j(topic = "nenu_oj")
public class IPUtils {

    public static String getUserAddress( HashMap<String, String> request_header ){
        String ipAddress = null;
        try{

            ipAddress = request_header.get("x-forwarded-for");

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request_header.get("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request_header.get("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                return null;
            }
            // 通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress.contains(",")) {
                return ipAddress.split(",")[0];
            } else {
                return ipAddress;
            }
        }
        catch (Exception e){
            log.error("get ip address exception");
            return null;
        }
    }

}
