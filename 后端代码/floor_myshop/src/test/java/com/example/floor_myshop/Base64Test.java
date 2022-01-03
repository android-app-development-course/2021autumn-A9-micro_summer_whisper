package com.example.floor_myshop;

import cn.hutool.core.codec.Base64;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author tong
 * @date 2021/12/22
 */
public class Base64Test {

    @Test
    public void testBase64(){
        final String encode = Base64.encode(new File("/home/ulonglonggogo/MyDisk/PROJECTS/GradelProjects/floor_myshop/file_download_server/pictures/products/a.png"));
        System.out.println(encode);

        final File file = Base64.decodeToFile(encode, new File("/home/ulonglonggogo/MyDisk/PROJECTS/GradelProjects/floor_myshop/file_download_server/pictures/products/b.png"));


    }

}
