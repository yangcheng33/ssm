package org.sample.ssm.biz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HuQingmiao on 2016-5-23.
 */

@Service
public class ProductService {

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Map<String, String> findAllProdMap() throws Exception {

        Map<String, String> allProdMap = new LinkedHashMap<String, String>();
        allProdMap.put("hqd", "好期待");
        allProdMap.put("llh", "零零4%-花");

        return allProdMap;
    }
}
