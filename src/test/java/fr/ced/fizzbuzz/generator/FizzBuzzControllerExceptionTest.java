package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.FizzBuzzApplication;
import fr.ced.fizzbuzz.stats.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FizzBuzzApplication.class, FizzBuzzControllerExceptionTest.Config.class})
@WebAppConfiguration
public class FizzBuzzControllerExceptionTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FizzBuzzController fizzBuzzController;

    private MockMvc mockMvc;



    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        when(fizzBuzzController.generate(any(), any(), any(), any(), any())).thenThrow(new RuntimeException("Unexpected Exception"));
    }

    @Test
    void generateUnexpectedException() throws Exception {
        this.mockMvc.perform(get("/fizzbuzz/generate?int1=3&text1=Fizz&int2=5&text2=Buzz&limit=105")).andDo(print())
                .andExpect(jsonPath("$.title").value("Unexpected error"))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.detail").value("Unexpected Exception"));
    }

    public static class Config {

        @Bean
        @Primary
        public FizzBuzzController fizzBuzzController(){
            return Mockito.mock(FizzBuzzController.class);
        }
    }
}
