package org.sample.ssm.common.utils;

import java.text.DecimalFormat;

/**
 * 金额转换程序，负责把浮点型的金额转为法律层面认可的大写金额。
 *
 */
public class MoneyUtil {

    static final DecimalFormat df = new DecimalFormat("###,###,###,###,##0.00");

    static String HanDigiStr[] = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    static String HanDiviStr[] = new String[]{"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟"};

    /**
     * 把小数点前面的数转换为大写中文
     *
     * @param numStr
     * @return
     */
    static String positiveIntegerToHanStr(String numStr) { // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
        String rmbStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = numStr.length();
        if (len > 15) {
            return "数值过大!";
        }
        for (int i = len - 1; i >= 0; i--) {
            if (numStr.charAt(len - i - 1) == ' ') {
                continue;
            }
            n = numStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9) {
                return "输入含非数字字符!";
            }

            if (n != 0) {
                if (lastzero) {
                    rmbStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
                    // 除了亿万前的零不带到后面
                    // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) ) //
                    // 如十进位前有零也不发壹音用此行
                }
                if (!(n == 1 && (i % 4) == 1 && i == len - 1)) { // 十进位处于第一位不发壹音
                    rmbStr += HanDigiStr[n];
                }
                rmbStr += HanDiviStr[i]; // 非零值后加进位，个位为空
                hasvalue = true; // 置万进位前有值标记

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) { // 亿万之间必须有非零值方显示万
                    rmbStr += HanDiviStr[i]; // “亿”或“万”
                }
            }
            if (i % 8 == 0) {
                hasvalue = false; // 万进位前有值标记逢亿复位
            }
            lastzero = (n == 0) && (i % 4 != 0);
        }

        if (rmbStr.startsWith(HanDiviStr[1])) {
            rmbStr = HanDigiStr[1] + rmbStr;
        }

        if (rmbStr.length() == 0) {
            return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
        }
        return rmbStr;
    }

    private static String toUpper(double val) {
        String signStr = "";
        String tailStr = "";
        long fraction, integer;
        int jiao, fen;

        if (val < 0) {
            val = -val;
            signStr = "负";
        }
        if (val > 99999999999999.999 || val < -99999999999999.999) {
            return "数值位数过大!";
        }
        // 四舍五入到分
        long temp = Math.round(val * 100);
        integer = temp / 100;
        fraction = temp % 100;
        jiao = (int) fraction / 10;
        fen = (int) fraction % 10;
        if (jiao == 0 && fen == 0) {
            tailStr = "整";
        } else {
            tailStr = HanDigiStr[jiao];
            if (jiao != 0) {
                tailStr += "角";
            }
            if (integer == 0 && jiao == 0) { // 零元后不写零几分
                tailStr = "";
            }
            if (fen != 0) {
                tailStr += HanDigiStr[fen] + "分";
            }
        }

        // 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
        // if( !integer ) return SignStr+TailStr;

        return signStr + positiveIntegerToHanStr(String.valueOf(integer)) + "元" + tailStr;
    }


    public static String transToUpper(String currDesc, double val) {
        //人民币
        currDesc = currDesc == null ? "" : currDesc.trim();
        if ("人民币".equals(currDesc) || "元(人民币)".equals(currDesc) || "元（人民币）".equals(currDesc)) {
            return "人民币" + toUpper(val);
        }

        //外币
        return "(" + currDesc + ")" + toUpper(val);
    }

    public static String transToLower(String currDesc, double val) {

        currDesc = currDesc == null ? "" : currDesc.trim();

        //人民币
        if ("人民币".equals(currDesc) || "元(人民币)".equals(currDesc) || "元（人民币）".equals(currDesc)) {
            return df.format(val) + " 元(人民币)";
        }

        //外币
        return df.format(val) + " " + currDesc;
    }


    // 调试时用main函数
    public static void main(String args[]) {

        //System.out.println(-99999999999999.999 + app.toRMB(-99999999999999.999));
        //System.out.println(99999999999999.999 + app.toRMB(99999999999999.999));

        DecimalFormat f = new DecimalFormat("0.00");

        Double[] dArray = new Double[]{6007.14, 0.0, 0.10, 0.12, 1.2, 1.20, (double) 12, 12.01, 1001.01, 1111.10
                , 999999999999.99, 1409.50, 107000.53, 16409.02, 22.0, 220000.0, 22000.0};

        for (int i = 0; i < dArray.length; i++) {
            System.out.println(f.format(dArray[i]) + " : " + MoneyUtil.toUpper(dArray[i]) + " 美元");
        }
    }
} 

