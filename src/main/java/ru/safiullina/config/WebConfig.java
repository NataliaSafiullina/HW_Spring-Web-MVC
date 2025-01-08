package ru.safiullina.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class WebConfig {
    /**
     * Регистрация конвертеров
     * @return бин
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        final var bean = new RequestMappingHandlerAdapter();
        // Получаем коллекцию конвертеров и добавляем туда GsonHttp конвертер
        bean.getMessageConverters().add(new GsonHttpMessageConverter());
        return bean;
    }
}
