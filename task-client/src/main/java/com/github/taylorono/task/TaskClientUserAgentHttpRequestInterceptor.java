package com.github.taylorono.task;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class TaskClientUserAgentHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private String version;
    private String applicationName;

    public TaskClientUserAgentHttpRequestInterceptor(String version, String applicationName) {
        this.version = version;
        this.applicationName = applicationName;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("User-Agent", "task-client/" + version + " (" + applicationName + ")");
        return execution.execute(request, body);
    }
}
