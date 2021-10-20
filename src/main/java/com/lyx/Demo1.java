package com.lyx;

import cn.hutool.core.lang.Console;
import cn.hutool.system.SystemUtil;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 热加载
 */
public class Demo1
{
    public static void main(String[] args) throws Exception
    {
        haveParent();
        noParent();
    }

    public static void haveParent() throws Exception
    {
        String[] pathArr = SystemUtil.get("java.class.path").split(":");
        String resources = pathArr[1];

        // 两个类加载器
        // 第一个是第二个的父加载器
        MyFileClassLoader fileClassLoader1 = new MyFileClassLoader(resources);
        MyFileClassLoader fileClassLoader2 = new MyFileClassLoader(resources, fileClassLoader1);

        // 调用父类的loadClass()方法加载类
        // 有双新委派机制
        Class<?> class1 = fileClassLoader1.loadClass("com.a1.Test1");
        Class<?> class2 = fileClassLoader2.loadClass("com.a1.Test1"); // 父加载器已经加载这个类了，直接返回父加载器加载的.

        // 打印出来的hash码是一样的
        Console.log("打印数据1：{}", class1.hashCode());
        Console.log("打印数据1：{}", class2.hashCode());
    }

    public static void noParent()  throws Exception
    {
        String[] pathArr = SystemUtil.get("java.class.path").split(":");
        String resources = pathArr[1];

        // 两个类加载器
        // 第一个是第二个的父加载器
        MyFileClassLoader fileClassLoader1 = new MyFileClassLoader(resources);
        MyFileClassLoader fileClassLoader2 = new MyFileClassLoader(resources, fileClassLoader1);

        // 直接重写的findClass()方法
        // 避开了loadClass()中的又新委派机制
        Class<?> class1 = fileClassLoader1.findClass("com.a1.Test1");
        Class<?> class2 = fileClassLoader2.findClass("com.a1.Test1");

        // 打印出来的hash码是不一样的
        Console.log("打印数据2：{}", class1.hashCode());
        Console.log("打印数据2：{}", class2.hashCode());
    }
}
