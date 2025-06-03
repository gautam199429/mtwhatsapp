package com.moneytree.moneywhatsapp.configurations;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class FeignConfiguration {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    // Parameterize pool and timeout values for flexibility
    @Value("${feign.okhttp.max-idle-connections:10}")
    private int maxIdleConnections;

    @Value("${feign.okhttp.keep-alive-duration:5}")
    private long keepAliveDuration;

    @Value("${feign.okhttp.timeout:180}")
    private long timeoutSeconds;

    public FeignConfiguration(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    /**
     * OkHttpClient bean for Feign with connection pool and timeouts.
     */
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MINUTES))
                .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Feign FormEncoder bean for multipart/form-data support.
     */
    @Bean
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    Encoder feignFormEncoder() {
        return new FormEncoder(new SpringEncoder(messageConverters));
    }
}
