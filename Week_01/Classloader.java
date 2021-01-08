package com.yq.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class Classloader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class cla = new Classloader().findClass("Hello.xlass");
            Object obj = cla.newInstance();
            Method method = cla.getDeclaredMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream in = Classloader.class.getClassLoader().getResourceAsStream(name);
        int d;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            while ((d = in.read()) != -1) {
                byteStream.write((byte) (255-d));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytes = byteStream.toByteArray();

        return defineClass(name.substring(0,name.indexOf(".")), bytes, 0, bytes.length);

    }
}
