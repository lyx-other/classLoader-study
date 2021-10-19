package com.lyx;

import cn.hutool.core.io.FileUtil;

/**
 * 只加载一个类
 */
public class MyClassLoader extends ClassLoader
{
    /**
     * .class文件路径
     */
    private String filePath;

    /**e
     * 构造方法
     * @param filePath .class文件路径
     */
    public MyClassLoader(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * 加载类
     * @param name 要加载的类的全类名，如com.lyx.Test
     * @return 加载的类的class对象
     */
    @Override
    protected Class<?> findClass(String name)
    {
        byte[] data = FileUtil.readBytes(filePath);

        return this.defineClass(name, data, 0, data.length);
    }
}
