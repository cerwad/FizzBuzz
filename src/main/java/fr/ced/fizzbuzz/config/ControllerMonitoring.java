package fr.ced.fizzbuzz.config;

import fr.ced.fizzbuzz.stats.StatisticsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Handles common logging and stat gathering for FizzBuzzController
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ControllerMonitoring implements HandlerInterceptor {

    private final StatisticsService statisticsService;
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        log.info("[Http][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() + request.getQueryString());
        incrementRequestCounter(request.getRequestURI()+"?"+request.getQueryString());
        return true;
    }

    @Async("singleThreadExecutor")
    public void incrementRequestCounter(String request){
        statisticsService.countRequestCall(request);
    }
}
