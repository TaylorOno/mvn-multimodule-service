package com.github.taylorono.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.mock.http.client.MockClientHttpRequest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskClientUserAgentHttpRequestInterceptorTest {

    @Mock
    ClientHttpRequestExecution clientHttpRequestExecution;

    @Captor
    private ArgumentCaptor<HttpRequest> captor;

    @Test
    public void intercept_addsUserAgentRequestHeader() throws IOException {
        TaskClientUserAgentHttpRequestInterceptor interceptor = new TaskClientUserAgentHttpRequestInterceptor("1.0", "applicationName");
        byte[] body = "body".getBytes();

        interceptor.intercept(new MockClientHttpRequest(), body, clientHttpRequestExecution);

        verify(clientHttpRequestExecution).execute(captor.capture(), eq(body));

        List<String> userAgentHeader = captor.getValue().getHeaders().get("User-Agent");
        assertEquals( "task-client/1.0 (applicationName)", userAgentHeader.get(0));
    }
}