package org.sample.ssm.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * 提供常用的工具类方法
 *
 * @author HuQingmiao
 */
public class CommonUtil {

    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 将以逗号分隔的字符串转换成Set<String>
     *
     * @param str 类似如:"4,5,33,43"的字符串
     * @return
     */
    public static HashSet<String> toSet(String str, String delim) {
        HashSet<String> set = new HashSet<String>();
        StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (!"".equals(token)) {
                set.add(token);
            }
        }
        return set;
    }

    public static String toString(Set set, String delim) {

        StringBuffer buff = new StringBuffer();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            Object obj = it.next();

            buff.append(obj.toString());
            buff.append(delim);
        }
        if (buff.length() > 0) {
            buff.deleteCharAt(buff.length() - 1);
        }

        return buff.toString();
    }

    /**
     * 构造由某指定字符重复组成的字符串
     *
     * @param ch  字符
     * @param num 字隔字符数
     */
    public static String buildRepeatChars(char ch, int num) {

        StringBuffer buff = new StringBuffer();

        for (int i = 0; i < num; i++) {
            buff.append(ch);
        }
        return buff.toString();
    }

    /**
     * 将字符串str剪到不超过count个字节的长度
     *
     * @param str     要剪切的字符串
     * @param byteCnt 要求剪切后的最大字节数
     * @return
     * @author Huqingmiao
     */
    public static String substr(String str, int byteCnt) {

        StringBuffer buff = new StringBuffer(str);

        while (buff.toString().getBytes().length > byteCnt) {
            buff.deleteCharAt(buff.length() - 1);
        }
        return buff.toString();
    }


    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int randomNum = new Random().nextInt(100);
            System.out.println(randomNum);
        }
    }


}