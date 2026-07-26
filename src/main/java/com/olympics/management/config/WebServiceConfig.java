package com.olympics.management.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    public static final String NAMESPACE_URI =
            "http://olympics.com/management/soap";

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet>
    messageDispatcherServlet(ApplicationContext applicationContext) {

        MessageDispatcherServlet servlet =
                new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        ServletRegistrationBean<MessageDispatcherServlet> registration =
                new ServletRegistrationBean<>(
                        servlet,
                        "/ws/*"
                );

        registration.setLoadOnStartup(1);

        return registration;
    }

    @Bean(name = "olympics")
    public DefaultWsdl11Definition olympicsWsdl(
            XsdSchema olympicsSchema
    ) {
        DefaultWsdl11Definition definition =
                new DefaultWsdl11Definition();

        definition.setPortTypeName("OlympicsPort");
        definition.setLocationUri("/ws/");
        definition.setTargetNamespace(NAMESPACE_URI);
        definition.setSchema(olympicsSchema);

        return definition;
    }

    @Bean
    public XsdSchema olympicsSchema() {
        return new SimpleXsdSchema(
                new ClassPathResource("xsd/olympics.xsd")
        );
    }
}