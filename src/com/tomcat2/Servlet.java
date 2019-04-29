package com.tomcat2;

import java.io.InputStream;
import java.io.OutputStream;

public interface Servlet {
    public void init();
    public void service(InputStream is, OutputStream os);
    public void destory();
}
