package com.lyx;

import cn.hutool.core.lang.Console;
import java.lang.reflect.Method;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/lgf/my-dir/download/test/TestClass.class");
        Class<?> testClass = myClassLoader.findClass("com.test.TestClass");

        Method funMethod = testClass.getDeclaredMethod("fun", int.class);
        Object instance = testClass.getDeclaredConstructor().newInstance();
        Object returnResult = funMethod.invoke(instance, 10);

        Console.log("打印数据：{}", returnResult);
    }
}
