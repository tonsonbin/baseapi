package com.tyfo.app.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyfo.app.common.mapper.annotation.JSON;
import com.tyfo.app.common.mapper.filter.JacksonJsonFilter;
import org.apache.commons.lang3.StringUtils;

public class CustomerJsonSerializer {

    private ObjectMapper mapper = JsonMapper.getMapper();

    private JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    /**
     * @param clazz   target type
     * @param include include fields
     * @param filter  filter fields
     */
    public void filter(Class<?> clazz, String include, String filter) {
        if (clazz == null) return;
        if (StringUtils.isNotBlank(include)) {
            jacksonFilter.include(clazz, include.split(","));
        }
        if (StringUtils.isNotBlank(filter)) {
            jacksonFilter.filter(clazz, filter.split(","));
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        String data = mapper.writeValueAsString(object);
        jacksonFilter.clean();
        return data;
    }

    public void filter(JSON json) {
        this.filter(json.type(), json.include(), json.filter());
    }


}
