package com.microserviceDemo.moviecatalogservice.BeanDtls;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.client5.http.*;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyStore;

@Configuration
//@Profile("Dev")
public class CommonBean {

//
//    @Bean
//    public SSLContext createSSLContext() throws Exception {
//        KeyStore keyStore = KeyStore.getInstance("JKS");
//        keyStore.load(getClass().getResourceAsStream("/keystore.jks"), "changeit".toCharArray());
//
//        KeyStore trustStore = KeyStore.getInstance("JKS");
//        trustStore.load(getClass().getResourceAsStream("/truststore.jks"), "changeit".toCharArray());
//
//        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        kmf.init(keyStore, "changeit".toCharArray());
//
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        tmf.init(trustStore);
//
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//
//        return sslContext;
//    }
//
//    @Bean(name="restTemplateOne")
//    public RestTemplate restTemplateOne() throws Exception {
//        SSLContext sslContext = createSSLContext();
//
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory() {
//            @Override
//            protected void prepareConnection(HttpURLConnection connection, String httpMethod)
//                    throws IOException {
//                if (connection instanceof HttpsURLConnection https) {
//                    https.setSSLSocketFactory(sslContext.getSocketFactory());
//                }
//                super.prepareConnection(connection, httpMethod);
//            }
//        };
//        return new RestTemplate(requestFactory);
//    }

//    @Bean(name="restOne")
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
}
