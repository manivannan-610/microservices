package com.apigateway.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

//@Component
public class RequestResponseLoggingFilter implements WebFilter {

    Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    private static final Set<String> SKIP_PATHS = Set.of(
            "/actuator/health",
            "/actuator/metrics"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             WebFilterChain chain) {
        long  start = System.currentTimeMillis();
        String path = exchange.getRequest().getURI().getPath();
        System.out.println("path="+path);
        if (path.startsWith("/actuator")) {
            return chain.filter(exchange);
        }
        return chain.filter(exchange).doFinally(signal->{
            long time = System.currentTimeMillis() - start;
            log.info("{} {} → {} ({} ms)",
                    exchange.getRequest().getMethod(),
                    exchange.getRequest().getURI(),
                    exchange.getResponse().getStatusCode(),
                    time
            );
        });
    }

//    private void logRequest(ContentCachingRequestWrapper req) {
//        String body = new String(req.getContentAsByteArray(), StandardCharsets.UTF_8);
//        System.out.println("checking is request in logging");
//        log.info("REQUEST → {} {} body={}",
//                req.getMethod(),
//                req.getRequestURI(),
//                truncate(body));
//    }
//
//    private void logResponse(ContentCachingRequestWrapper req,
//                             ContentCachingResponseWrapper res,
//                             long start) {
//
//        String body = new String(res.getContentAsByteArray(), StandardCharsets.UTF_8);
//        long duration = System.currentTimeMillis() - start;
//
//        log.info("RESPONSE ← {} {} status={} time={}ms body={}",
//                req.getMethod(),
//                req.getRequestURI(),
//                res.getStatus(),
//                duration,
//                truncate(body));
//    }

    private String truncate(String body) {
        return body.length() > 1000 ? body.substring(0, 1000) + "..." : body;
    }
}

