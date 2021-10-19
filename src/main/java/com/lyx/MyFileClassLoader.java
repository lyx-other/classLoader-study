package com.lyx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

/**
 * 第①步：继承ClassLoader
 * 第②步：重写findClass()方法
 */
public class MyFileClassLoader extends ClassLoader
{
    // 被加载所在的目录
    private String dir;

    /**
     * 构造方法，指定目录，默认父加载器为系统类加载器.
     */
    public MyFileClassLoader(String dir)
    {
        this.dir = dir;
    }

    /**
     * 构造方法，指定目录、指定父加载器
     */
    public MyFileClassLoader(String dir, ClassLoader parent)
    {
        super(parent);
        this.dir = dir;
    }

    /**
     * 加载类
     * @param name 类名，如com.test.TestClass
     */
    @Override
    protected Class<?> findClass(String name)
    {
        // ①根据类名找到class文件
        String classFilePath = StrUtil.format("{}{}{}.class", dir, File.separator, StrUtil.replace(name, ".", File.separator));

        // ②将文件读取为二进制文件
        byte[] fileData = FileUtil.readBytes(classFilePath);

        // ③调用父类的defineClass()方法，传入读取的二进制文件. defineClass()方法底层是用C++实现的.
        return defineClass(name, fileData, 0, fileData.length);
    }
}
