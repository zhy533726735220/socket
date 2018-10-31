package com.zhy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {
    public static void main(String [] args){
        // 接下来要做的,一个是socket服务器,一个是socket客户端
        // 初始化Socket服务器对象,构造方法中填入监听的端口号
        // 端口号的取值范围是 0-65535
        // 1-1024 已经规定给了特定的协议使用
        // 0和65535两个端口号原则上不允许使用

        try {
            System.out.println("服务器开始运行");
            ServerSocket serverSocket = new ServerSocket(8989);
            //通过serverSocket.accept()方法,获取客户端的socket连接
            // 这个方法是一个阻塞的方法,会阻塞程序的执行
            //如果没有客户端进行连接,则程序会阻塞(也就是卡住)
            System.out.println("等待客户端连接....");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new SocketRunnable(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static class SocketRunnable implements Runnable{
        private Socket socket;

        public SocketRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            // 通过socket对象可以获取到输入流或者输出流
            InputStream is = null;
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String reciver = "";
            StringBuffer buffer = new StringBuffer();
            try {
                while ((reciver = br.readLine()) != null){
                    buffer.append(reciver);
                    System.out.println(reciver);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //socket 使用之后,要关闭
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
