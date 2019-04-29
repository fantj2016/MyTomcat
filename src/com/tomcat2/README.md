# MyTomcat v1.0

## Servlet
>一个接口契约


## AServlet BServlet 
>Servlet的实现类，里面用来写业务逻辑。

## TestServlet
1. 完成对请求头中url的解析，并返回静态资源。
2. 完成对配置类`conf.properties`的加载，通过反射完成动态请求的实现。

## 测试
```$xslt
url: http://localhost:8080/b

result: I am BServlet
```