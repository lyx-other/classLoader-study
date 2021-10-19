package com.lyx;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;

public class MyURLClassLoader extends ClassLoader
{
    /**
     * 包所在的url
     * 包的意思是，例如：com/lyx/Test.class
     */
    private String url;

    public MyURLClassLoader(String url)
    {
        this.url = url;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        // ①组装完整的url
        String fileUrl = StrUtil.format("{}/{}.class", url, StrUtil.replace(name, ".", "/"));

        // ②下载文件
        byte[] fileData = HttpUtil.downloadBytes(fileUrl);

        // ③调用父类的defineClass()方法，传入读取的二进制文件. defineClass()方法底层是用C++实现的.
        return defineClass(name, fileData, 0, fileData.length);
    }
}
