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
import java.util.Properties;

/**
 * Test
 *
 * @author Yang Cheng
 * @date 2016-09-02
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        try {
            String filePath = "/Users/didi/test.sh";
            File f = ResourceUtils.getFile("classpath:log4j.properties");
            System.out.println(f.exists());
            File f1 = ResourceUtils.getFile("file:"+filePath);
            Properties p = new Properties();
            p.load(new FileInputStream(f));
            System.out.println(p.getProperty("log4j.logger.org.apache"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
