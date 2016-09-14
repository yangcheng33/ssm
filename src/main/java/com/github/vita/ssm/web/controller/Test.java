package com.github.vita.ssm.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

/**
 * Test
 *
 * @author Yang Cheng
 * @date 2016-09-02
 */
public class Test {
    //static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws Exception {
//        Test.class.forName("com.github.vita.ssm.web.controller.t1");
//        try {
//            URL[] extURLs = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
//            for (int i = 0; i < extURLs.length; i++) {
//                System.out.println(extURLs[i]);
//            }          } catch (Exception e) {
//            e.printStackTrace();
//        }
//                try {
//            System.out.println(ClassLoader.getSystemClassLoader());
//            System.out.println(ClassLoader.getSystemClassLoader().getParent());
//            System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getContextClassLoader() == ClassLoader.getSystemClassLoader());
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//
//                try {
//                    Thread.currentThread().setContextClassLoader(null);
//                    System.out.println(ClassLoader.getSystemClassLoader());
//                    System.out.println(Thread.currentThread().getContextClassLoader());
//                    //Thread.currentThread().getContextClassLoader().loadClass("com.github.vita.ssm.web.controller.t1");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                ;
//            }
//        };
//        t.start();
//        Thread.sleep(3000);
//        Test.class.getClassLoader().loadClass("com.github.vita.ssm.web.controller.t1");

        A a = new A();  // 加载类A
        B b = new B();  // 加载类B
        a.setB(b);  // A引用了B，把b对象拷贝到A.b
        System.out.printf("A classLoader is %s\n", a.getClass().getClassLoader());
        System.out.printf("B classLoader is %s\n", b.getClass().getClassLoader());
        System.out.printf("A.b classLoader is %s\n", a.getB().getClass().getClassLoader());
        try {
            URL[] urls = new URL[]{ new URL("file:///Users/didi/dev/ssm/target/classes/") };
            HotSwapClassLoader c1 = new HotSwapClassLoader(urls, a.getClass().getClassLoader());
            Class clazz = c1.load("com.github.vita.ssm.web.controller.A");  // 用hot swap重新加载类A
            Object aInstance = clazz.newInstance();  // 创建A类对象
            Method method1 = clazz.getMethod("setB", B.class);  // 获取setB(B b)方法
            method1.invoke(aInstance, b);    // 调用setB(b)方法，重新把b对象拷贝到A.b
            Method method2 = clazz.getMethod("getB");  // 获取getB()方法
            Object bInstance = method2.invoke(aInstance);  // 调用getB()方法
            System.out.printf("Reloaded A.b classLoader is %s\n", bInstance.getClass().getClassLoader());
            System.out.println(a.getClass()+"  "+ aInstance.getClass());
            System.out.println(aInstance.getClass().getClassLoader());
            System.out.println("--");
            clazz = c1.load("com.github.vita.ssm.web.controller.A");  // 用hot swap重新加载类A
            Object aaInstance = clazz.newInstance();  // 创建A类对象
            System.out.println(aInstance.getClass()+"  "+ aaInstance.getClass());
            System.out.println(aaInstance.getClass().getClassLoader());
            Method methoda = clazz.getMethod("out");  // 获取setB(B b)方法
            methoda.invoke(aaInstance);    // 调用setB(b)方法，重新把b对象拷贝到A.b
        } catch (MalformedURLException | ClassNotFoundException |
                InstantiationException | IllegalAccessException |
                NoSuchMethodException | SecurityException |
                IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}


class B{


}

class HotSwapClassLoader extends URLClassLoader {

    public HotSwapClassLoader(URL[] urls) {
        super(urls);
    }

    public HotSwapClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    // 下面的两个重载load方法实现类的加载，仿照ClassLoader中的两个loadClass()
    // 具体的加载过程代理给父类中的相应方法来完成
    public Class<?> load(String name) throws ClassNotFoundException {
        return load(name, false);
    }

    public Class<?> load(String name, boolean resolve) throws ClassNotFoundException {
        // 若类已经被加载，则重新再加载一次
        if (null != super.findLoadedClass(name)) {
            return reload(name, resolve);
        }
        // 否则用findClass()首次加载它
        Class<?> clazz = super.findClass(name);
        if (resolve) {
            super.resolveClass(clazz);
        }
        return clazz;
    }

    public Class<?> reload(String name, boolean resolve) throws ClassNotFoundException {
        return new HotSwapClassLoader(super.getURLs(), super.getParent()).load(
                name, resolve);
    }
}

//class MyClassLoader extends URLClassLoader {
//
//
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        super.
//    }
//}