package com.tomcat2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BServlet implements Servlet{
    @Override
    public void init() {
        System.out.println("BServlet init ...");
    }

    @Override
    public void service(InputStream is, OutputStream os) {
        System.out.println("BServlet service ...");
        try {
            os.write("I am BServlet".getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destory() {

    }
}
