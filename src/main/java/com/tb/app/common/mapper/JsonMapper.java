package com.tb.app.common.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.tb.app.common.persistence.DataEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class JsonMapper{
    private static ObjectMapper mapper;

    public void setUpObjectMap(ObjectMapper mapper) {
        JsonMapper.mapper = mapper;
        configMapper();
    }

    private void configMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        //json中多余的参数不报错，不想要可以改掉
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        //设置全局的时间转化
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(smt);
        //解决时区差8小时问题
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException {
                if (o instanceof Integer) {
                    jsonGenerator.writeNumber(0);
                } else if (o instanceof List) {
                    jsonGenerator.writeStartArray();
                    jsonGenerator.writeEndArray();
                } else if (o instanceof DataEntity) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeEndObject();
                } else {
                    jsonGenerator.writeString("");
                }
            }
        });
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

}
