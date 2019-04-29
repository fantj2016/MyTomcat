package com.tomcat2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AServlet implements Servlet{
    @Override
    public void init() {
        System.out.println("AServlet init ...");
    }

    @Override
    public void service(InputStream is, OutputStream os) {
        System.out.println("AServlet service ...");
        try {
            os.write("I am AServlet".getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destory() {

    }
}
