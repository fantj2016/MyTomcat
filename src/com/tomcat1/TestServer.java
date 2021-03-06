package com.tomcat1;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    // 定义一个变量，存放WebContent目录的绝对路径
    private static String WEB_ROOT = System.getProperty("user.dir")+"\\"+"WebContent";
    // 定义静态变量，用于存放请求的静态页面名称
    private  static String url = "";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket  = new ServerSocket(8080);
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {

            while (true){
                // 获取到客户端对应的socket
                socket = serverSocket.accept();
                // 获取到输入流对象
                is = socket.getInputStream();
                // 获取到输出流对象
                os = socket.getOutputStream();
                // 获取请求部分，截取资源名称并赋值给url
                parse(is);
                // 发送静态资源
                sendStaticResoutce(os);
            }
        }catch (IOException e) {
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
    }

    private static void sendStaticResoutce(OutputStream os) throws IOException {
        // 定义一个 字节数组，用于存放静态资源的内容
        byte [] bytes = new byte[2048];
        // 定义一个文件输入流
        FileInputStream fis = null;
        try {
            // 创建文件对象File
            File file = new File(WEB_ROOT,url);
            // 如果文件存在
            if (file.exists()){
                // 像客户端输出响应行/头
                os.write("HTTP/1.1 200 OK\n".getBytes());
                os.write("com.tomcat1.Server:apache-Coyote/1.1\n".getBytes());
                os.write("Content-Type:text/html;charset=utf8\n".getBytes());
                os.write("\n".getBytes());
                // 获取文件输入流对象
                fis = new FileInputStream(file);
                // 读取file内容到数组中
                int ch = fis.read(bytes);
                while (ch != -1){
                    os.write(bytes,0,ch);
                    ch = fis.read(bytes);
                }
                // 将数组中的内容通过输出流发送到客户端

            }else {
                //如果文件不存在
                // 向客户端发送不存在消息
                os.write("HTTP/1.1 404 Not Found\n".getBytes());
                os.write("com.tomcat1.Server:apache-Coyote/1.1\n".getBytes());
                os.write("Content-Type:text/html;charset=utf8\n".getBytes());
                os.write("\n".getBytes());
                String errorMsg = "File Not Found";
                os.write(errorMsg.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }

        }
    }

    private static void parse(InputStream is) throws IOException {
        // 定义一个变量，存放HTTP协议请求部分
        StringBuffer content = new StringBuffer(2048);
        // 定义一个数组，存放HTTP协议请求部分数据
        byte[] buffer = new byte[2048];
        // 定义一个变量i，表示存放数据量的大小
        int i = -1;
        // 将传来的数据放到stringBuffer中，i代表读的字节
        i = is.read(buffer);
        // 遍历字节数组，将数组中的数据追加到content变量中
        for (int j = 0; j < i; j++) {
            content.append((char)buffer[j]);
        }
        System.out.print(content);
        // 打印HTTP协议请求部分数据
        // 截取请求路径，并赋值给url
        parseUrl(content.toString());
    }

    private static void parseUrl(String content) {
        // 定义2个变量，存放请求行两个空格的位置
        int index1,index2;
        // 获取http请求部分第一空格的位置
        index1 = content.indexOf(" ");
        if (index1 != -1){
            // 第二空格的位置
            index2 = content.indexOf(" ",index1+1);
            if (index2>index1){
                // 获取字符串到本次请求资源的名称
                url = content.substring(index1+2,index2);
            }
        }
        // 打印本次请求静态资源名称
        System.out.println(url);
    }
}
