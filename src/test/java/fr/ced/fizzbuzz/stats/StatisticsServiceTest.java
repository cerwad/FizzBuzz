package fr.ced.fizzbuzz.stats;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;
    @InjectMocks
    private StatisticsService statisticsService;
    @Test
    void getMostUsedRequest() {
        // GIVEN
        RequestStatEntity requestStatEntity = new RequestStatEntity();
        requestStatEntity.setHitNumber(2);
        requestStatEntity.setId(3L);
        requestStatEntity.setRequest("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6");
        when(statisticsRepository.findMaxHitNumber()).thenReturn(requestStatEntity);

        // WHEN
        RequestStat requestStat = statisticsService.getMostUsedRequest();

        // THEN
        assertThat(requestStat.hitNumber()).isEqualTo(2);
        assertThat(requestStat.endpoint()).isEqualTo("/fizzbuzz/generate");
        assertThat(requestStat.args()).hasSize(5).contains("int1=2","text1=Fizz", "int2=3", "text2=Buzz", "limit=6");
    }

    @Test
    void countRequestCall() {
        // GIVEN
        RequestStatEntity requestStatEntity = new RequestStatEntity();
        requestStatEntity.setHitNumber(2);
        requestStatEntity.setId(3L);
        requestStatEntity.setRequest("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6");

        RequestStatEntity expected = new RequestStatEntity();
        expected.setHitNumber(3);
        expected.setId(3L);
        expected.setRequest("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6");
        when(statisticsRepository.findByRequest(requestStatEntity.getRequest())).thenReturn(requestStatEntity);
        when(statisticsRepository.save(expected)).thenReturn(expected);

        // WHEN
        statisticsService.countRequestCall("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6");

        // THEN
        verify(statisticsRepository).save(expected);

    }

    @Test
    void buildRequestStat() {
        // GIVEN
        RequestStatEntity requestStatEntity = new RequestStatEntity();
        requestStatEntity.setHitNumber(2);
        requestStatEntity.setId(3L);
        requestStatEntity.setRequest("/fizzbuzz/generate?int1=2&text1=Fizz&int2=3&text2=Buzz&limit=6");

        // WHEN
        RequestStat requestStat = statisticsService.buildRequestStat(requestStatEntity);

        // THEN
        assertThat(requestStat.hitNumber()).isEqualTo(2);
        assertThat(requestStat.endpoint()).isEqualTo("/fizzbuzz/generate");
        assertThat(requestStat.args()).hasSize(5).contains("int1=2","text1=Fizz", "int2=3", "text2=Buzz", "limit=6");
    }
}