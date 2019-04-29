package com.tomcat1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1. 建立socket对象，连接 ip和端口
            socket = new Socket("www.baidu.com",80);
            // 2. 获取到输出对象
            is = socket.getInputStream();
            // 3. 获取到输入流对象
            os = socket.getOutputStream();
            // 4. 将请求协议发送给客户端
            os.write("GET /index.html HTTP/1.1\n".getBytes());
            os.write("HOST:www.baidu.com\n".getBytes());
            os.write("Content:Type:text/html;charset=utf8\n".getBytes());
            os.write("\n".getBytes());
            // 5. 读取来自服务端的数据打印到控制台
            int i = is.read();
            while (i != -1){
                System.out.print((char) i);
                i = is.read();
            }
            // 6. 释放资源
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        System.out.println("Hello World!");
    }
}
