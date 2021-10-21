package com.lyx;

import cn.hutool.core.lang.Console;

import java.net.URLClassLoader;
import java.sql.DriverManager;

/**
 * 线程上下文加载器
 */
public class Demo2
{
    public static void main(String[] args)
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Console.log("打印数据：{}", loader);
    }
}
