package org.sample.ssm.common.codetable;

import java.util.HashMap;
import java.util.Map;

/**
 * 平台类别.
 *
 * @author Yang Cheng
 * @version v 0.1 2017-07-03 18:07
 */
public enum AppOSEnum {
    IOS((byte) 0),
    ANDROID((byte) 1);

    private static Map<Byte, AppOSEnum> codeMap = new HashMap<>();

    static {
        AppOSEnum[] values = AppOSEnum.values();
        for (AppOSEnum value : values) {
            codeMap.put(value.getCode(), value);
        }
    }

    private byte code;

    AppOSEnum(byte code) {
        this.code = code;
    }

    public static AppOSEnum getEnumByCode(Byte code) {
        return codeMap.get(code);
    }

    public byte getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
