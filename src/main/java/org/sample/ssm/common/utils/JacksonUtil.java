package org.sample.ssm.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json工具
 */
public class JacksonUtil {

    private final static ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

    static {
        // DEFAULT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        DEFAULT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 调用get方法生成json字符串
     */
    public static String toJson(Object obj) {
        try {
            return DEFAULT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换json为clazz.
     * <strong>依赖get和set方法</strong>
     */
    public static <T> T fromJson(String jsonText, Class<T> clazz) throws IOException {
        if (jsonText == null) {
            return null;
        }
        return DEFAULT_MAPPER.readValue(jsonText, clazz);
    }

    /**
     * 转换为集合类型的对象集合
     * <strong>依赖get和set方法</strong>
     *
     * <strong>example:</strong>
     *
     * <pre>
     * JacksonUtils.fromJson(jsonText, new TypeReference&lt;List&lt;FeedImage&gt;&gt;() {
     * });
     * </pre>
     */
    public static <T> T fromJson(String jsonText, TypeReference<T> valueTypeRef) throws IOException {
        if (jsonText == null) {
            return null;
        }
        return DEFAULT_MAPPER.readValue(jsonText, valueTypeRef);
    }

    /**
     * 从json字符串中读取出指定的节点
     */
    public static JsonNode getValueFromJson(String json, String key) throws JsonProcessingException, IOException {
        JsonNode node = DEFAULT_MAPPER.readTree(json);
        return node.get(key);
    }

    /**
     * 把json生成jsonNode
     */
    public static JsonNode getJsonNode(String json) throws JsonProcessingException, IOException {
        return DEFAULT_MAPPER.readTree(json);
    }

    /**
     * 把json字符串转为map
     */
    public static Map getMapFromJson(String json) throws JsonProcessingException, IOException {
        return (Map) DEFAULT_MAPPER.readValue(json, Map.class);
    }

    /**
     * 把json字符串转为list
     */
    public static List getListFromJson(String json) throws JsonProcessingException, IOException {
        return (List) DEFAULT_MAPPER.readValue(json, List.class);
    }

    /**
     * 从json字符串中读取数组节点所包含的JsonNode List
     */
    public static List<JsonNode> getListFromJson(String json, String key) throws JsonProcessingException, IOException {
        List<JsonNode> jsonNodes = null;
        JsonNode node = DEFAULT_MAPPER.readTree(json);
        JsonNode arrayNode = node.get(key);
        if (arrayNode.isArray()) {
            jsonNodes = new ArrayList<JsonNode>();
            for (JsonNode jsonNode : arrayNode) {
                jsonNodes.add(jsonNode);
            }
        }
        return jsonNodes;
    }

    /**
     * 将json转换为list格式,并指定子对象类型
     */
    public static List getListFromJson(String json, Class nodeClass) throws IOException {
        JavaType type = DEFAULT_MAPPER.getTypeFactory().
            constructCollectionType(List.class, nodeClass);
        return DEFAULT_MAPPER.readValue(json, type);
    }

    /**
     * 处理node为null的问题
     */
    public static String getStringValueFromNode(JsonNode node) {
        if (node != null) {
            return node.asText();
        }
        return null;
    }
}
