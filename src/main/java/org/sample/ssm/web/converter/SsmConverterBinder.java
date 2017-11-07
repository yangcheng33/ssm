package org.sample.ssm.web.converter;

import org.sample.ssm.common.codetable.AppOSEnum;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;

/**
 * 参数绑定器.
 *
 * @author Yang Cheng
 * @version v 0.1 2017-06-01 16:55
 */
public class SsmConverterBinder {
    public static void bindParam(WebDataBinder binder) {
        binder.registerCustomEditor(AppOSEnum.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(final String text) throws IllegalArgumentException {
                setValue(AppOSEnum.getEnumByCode(Byte.valueOf(text.trim())));
            }
        });
    }
}
