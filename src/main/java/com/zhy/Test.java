package com.zhy;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("ZHY");
            String ip = address.getHostAddress();
            System.out.println(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
