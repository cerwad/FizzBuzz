package fr.ced.fizzbuzz.config;

import fr.ced.fizzbuzz.generator.FizzBuzzHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Configuration(proxyBeanMethods = false)
public class MyRoutingConfiguration {

    private static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);
    private static final RequestPredicate ACCEPT_TEXT = accept(MediaType.TEXT_PLAIN);

    @Bean
    public RouterFunction<ServerResponse> routerFunction(FizzBuzzHandler fizzBuzzHandler) {
        return route()
                .GET("/generate", ACCEPT_TEXT, fizzBuzzHandler::generate)
                .GET("/stats", ACCEPT_JSON, fizzBuzzHandler::stats)
                .GET("/",ACCEPT_TEXT, r -> ok().body("To begin please access /generate"))
                .build();
    }

}