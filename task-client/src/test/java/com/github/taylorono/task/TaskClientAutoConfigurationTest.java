package com.github.taylorono.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskClientAutoConfigurationTest {
    private TaskClientAutoConfiguration subject;

    @BeforeEach
    public void setUp() {
        subject = new TaskClientAutoConfiguration();
    }

    @Test
    public void restTemplate_hasCustomRequestInterceptor() throws Exception {
        RestTemplate restTemplate  = subject.taskClientRestTemplate("version", "applicationName");

        assertThat(restTemplate.getInterceptors()).hasSize(1);
        assertThat(restTemplate.getInterceptors().get(0)).isInstanceOf(TaskClientUserAgentHttpRequestInterceptor.class);
    }

    @Test
    public void restTemplate_hasSimpleClientRequestFactory_withOutputStreamingFalse() {
        RestTemplate restTemplate = subject.taskClientRestTemplate("version", "applicationName");

        ClientHttpRequestFactory interceptingRequestFactory = restTemplate.getRequestFactory();
        SimpleClientHttpRequestFactory simpleRequestFactory = (SimpleClientHttpRequestFactory) ReflectionTestUtils.getField(interceptingRequestFactory, "requestFactory");
        boolean outputStreaming = (boolean) ReflectionTestUtils.getField(simpleRequestFactory, "outputStreaming");

        assertFalse(outputStreaming);
    }
}