package com.zhy;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        // 用于向服务端连接的客户端
        try {
            // 服务器的ip地址和端口号
            Socket client = new Socket("127.0.0.1", 8989);
            // 获取输出流.并且向输出流中写入数据,即可发送到服务器
            OutputStream os = client.getOutputStream();
            PrintStream printStream = new PrintStream(os);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean flag = true;
            while (flag){
                // 获取输入进来的文字
                System.out.println("请输入");
                String str = br.readLine();
                if (str.equals("bye")){
                    flag = false;
                }
                printStream.println(str);
            }
            //os.write(str.getBytes());
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
