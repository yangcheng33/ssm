package com.github.walker.common.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by HuQingmiao on 2016-1-16.
 */
public class FreemarkerUtil {
    private final static Logger log = LoggerFactory.getLogger(FreemarkerUtil.class);
    public final static String version = "2.3.23";

    public static File generateFile(File ftlDirc, String ftlFilename, Map<String, Object> pvMap, String outFilename) throws Exception {
        if (!ftlDirc.exists()) {
            throw new Exception("不存在的模板目录: " + ftlDirc.getName());
        }

        Configuration cfg = new Configuration(new Version(version));
        cfg.setObjectWrapper(new DefaultObjectWrapper(new Version(version)));

        cfg.setEncoding(Locale.CHINESE, "UTF-8");
        cfg.setDirectoryForTemplateLoading(ftlDirc);
        Template temp = cfg.getTemplate(ftlFilename);

        File outFile = new File(outFilename);
        FileWriter fw = new FileWriter(outFile);
        try {
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(pvMap, bw);
            bw.flush();
        } catch (IOException e) {
            log.error("", e);
            throw e;
        } finally {
            fw.close();
        }
        return outFile;
    }


    public static File generateFile(String ftlFilename, Map<String, Object> pvMap, String outFilename) throws Exception {
        Configuration cfg = new Configuration(new Version(version));
        cfg.setObjectWrapper(new DefaultObjectWrapper(new Version(version)));

        cfg.setEncoding(Locale.CHINESE, "UTF-8");
        cfg.setClassForTemplateLoading(FreemarkerUtil.class,"/tpl");
        Template temp = cfg.getTemplate(ftlFilename);

        File outFile = new File(outFilename);
        FileWriter fw = new FileWriter(outFile);
        try {
            BufferedWriter bw = new BufferedWriter(fw);
            temp.process(pvMap, bw);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            log.error("", e);
            throw e;
        } finally {
            fw.close();
        }
        return outFile;
    }


    public static String generateContent(String ftlFilename, Map<String, Object> kvMap) throws Exception {
        Configuration cfg = new Configuration(new Version(version));
        cfg.setObjectWrapper(new DefaultObjectWrapper(new Version(version)));
        cfg.setEncoding(Locale.CHINESE, "UTF-8");

        cfg.setClassForTemplateLoading(FreemarkerUtil.class,"/tpl");
        Template temp = cfg.getTemplate(ftlFilename);

        Writer out = new StringWriter(2048);
        temp.process(kvMap, out);
        return out.toString();
    }


    public static void main(String[] args) {

        try {
            try {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("custName", "牛二");
                map.put("idNo", "4506231985532601");
                map.put("cYear", "2015");
                map.put("cMonth", "12");
                map.put("cDay", "05");
                map.put("product", "好期待");
                map.put("currYear", "2016");
                map.put("currMonth", "03");
                map.put("currDay", "9");
                FreemarkerUtil.generateFile("销户证明模板.xml", map, "d:/my销户证明2.doc");


                map.clear();

                map.put("custName","牛二");
                map.put("idNo","4506231985532601");
                map.put("creditNo","xy00324");

                List<Map<String,Object>> itemList = new ArrayList<Map<String,Object>>();
                Map<String,Object> itemMap = new HashMap<String, Object>();
                itemMap.put("idx","1");
                itemMap.put("contractNo","contract001");
                itemMap.put("amt","50.0");
                itemMap.put("cnAmt","伍拾圆整");
                itemMap.put("dType","按月还");
                itemMap.put("dCnt","6");
                itemMap.put("cYear","2014");
                itemMap.put("cMonth","06");
                itemMap.put("cDay","04");
                itemMap.put("dYear","2015");
                itemMap.put("dMonth","12");
                itemMap.put("dDay","05");
                itemMap.put("char","；");
                itemList.add(itemMap);

                itemMap = new HashMap<String, Object>();
                itemMap.put("idx",2);
                itemMap.put("contractNo","contract002");
                itemMap.put("amt","100.0");
                itemMap.put("cnAmt","一佰圆整");
                itemMap.put("dType","按月还");
                itemMap.put("dCnt","8");
                itemMap.put("cYear","2015");
                itemMap.put("cMonth","06");
                itemMap.put("cDay","04");
                itemMap.put("dYear","2016");
                itemMap.put("dMonth","3");
                itemMap.put("dDay","05");
                itemMap.put("char","。");
                itemList.add(itemMap);

                map.put("itemList",itemList);

                map.put("currYear","2016");
                map.put("currMonth","03");
                map.put("currDay","9");

                FreemarkerUtil.generateFile("贷款结清证明模板.xml", map, "d:/my贷款结清2.doc");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


