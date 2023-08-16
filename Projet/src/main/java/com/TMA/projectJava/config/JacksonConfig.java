package com.TMA.projectJava.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Tắt các thuộc tính ràng buộc khi tham gia chuyển đổi Json
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        Hibernate5Module hibernate5Module = new Hibernate5Module();
            hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        objectMapper.registerModule(hibernate5Module);
        return objectMapper;
    }
}
