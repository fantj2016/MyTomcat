package com.tomcat1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        OutputStream os = null;
        try {
            // 1. 创建ServerSocket对象，监听8080端口
            serverSocket = new ServerSocket(8080);
            // 2. 等待来自客户端的请求和对应的socket对象
            socket = serverSocket.accept();
            // 3. 通过获取到的socket对象获取到输出流
            os = socket.getOutputStream();
            // 4. 通过获取到的输出流对象将HTTP协议的响应部分发送到客户端
            os.write("HTTP/1.1 200 OK\n".getBytes());
            os.write("Content-Type:text/html;charset=utf8\n".getBytes());
            os.write("\n\n".getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<html>");
            stringBuffer.append("<head><title>我是标题</title></head>");
            stringBuffer.append("<body>");
            stringBuffer.append("<h1>I am Fantj</h1>");
            stringBuffer.append("<a href='www.baidu.com'>百度</a>");
            stringBuffer.append("</body>");
            stringBuffer.append("</html>");
            os.write(stringBuffer.toString().getBytes());
            os.flush();
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}
