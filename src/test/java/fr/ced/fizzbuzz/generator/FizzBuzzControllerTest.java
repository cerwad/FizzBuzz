package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.FizzBuzzApplication;
import fr.ced.fizzbuzz.stats.RequestStatEntity;
import fr.ced.fizzbuzz.stats.StatisticsRepository;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FizzBuzzApplication.class})
@WebAppConfiguration
class FizzBuzzControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        statisticsRepository.deleteAll();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesFizzBuzzController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertThat(servletContext).isNotNull();
        assertThat(servletContext instanceof MockServletContext).isTrue();
        assertThat(webApplicationContext.getBean("fizzBuzzController")).isNotNull();
    }

    @Test
    void generate() throws Exception {
        this.mockMvc.perform(get("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6")).andDo(print())
                .andExpect(content().string("1,Fizz,Buzz,Fizz,5,FizzBuzz"));
        assertThat(statisticsRepository.findByRequest("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6"))
                .isNotNull()
                .extracting("hitNumber")
                .isEqualTo(1);
    }

    @Test
    void generateInvalidArg() throws Exception {
        this.mockMvc.perform(get("/fizzbuzz/generate?int1=3&text1=Fizz&int2=5&text2=Buzz&limit=105")).andDo(print())
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.detail").value("Limit cannot exceed 100"));
    }

    @Test
    void testStats() throws Exception {
        RequestStatEntity requestStatEntity = new RequestStatEntity();
        requestStatEntity.setHitNumber(5);
        requestStatEntity.setId(3L);
        requestStatEntity.setRequest("/fizzbuzz/generate?int1=3&text1=Fizz&int2=5&text2=Buzz&limit=10");

        statisticsRepository.save(requestStatEntity);
        this.mockMvc.perform(get("/fizzbuzz/stats")).andDo(print())
                .andExpect(jsonPath("$.endpoint").value("/fizzbuzz/generate"))
                .andExpect(jsonPath("$.hitNumber").value(5));
    }
}