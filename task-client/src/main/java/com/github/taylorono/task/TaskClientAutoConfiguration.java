package com.github.taylorono.task;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;
import java.util.Collections;

@Configuration
@Validated
@EnableConfigurationProperties
@ConditionalOnMissingBean(name = "taskClient")
public class TaskClientAutoConfiguration {

    @Bean
    public TaskClient taskClient(
            @Qualifier("taskClientRestTemplate") RestTemplate taylorStarterServiceRestTemplate,
            @NotEmpty(message = "task-service.url is required") @Value("${task-service.url}") String serviceUrl) {
        return new TaskClient(taylorStarterServiceRestTemplate, serviceUrl);
    }

    @Bean(name = "taskClientRestTemplate")
    @ConditionalOnMissingBean(name = "taskClientRestTemplate")
    public RestTemplate taskClientRestTemplate(
            @Value("${task-client.version}") String version,
            @Value("${spring.application.name}") String applicationName) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new TaskClientUserAgentHttpRequestInterceptor(version, applicationName)));

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }
}
