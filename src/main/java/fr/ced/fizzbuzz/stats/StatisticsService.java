package fr.ced.fizzbuzz.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    public RequestStat getMostUsedRequest(){
        RequestStatEntity requestStatEntity = statisticsRepository.findMaxHitNumber();
        return buildRequestStat(requestStatEntity);
    }

    public void countRequestCall(String request){
        RequestStatEntity requestStat = statisticsRepository.findByRequest(request);
        if(requestStat == null){
            requestStat = new RequestStatEntity();
            requestStat.setRequest(request);
            requestStat.setHitNumber(0);
        }
        requestStat.setHitNumber(requestStat.getHitNumber()+1);
        statisticsRepository.save(requestStat);
    }

    protected RequestStat buildRequestStat(RequestStatEntity requestStatEntity){
        String request = requestStatEntity.getRequest();
        if (request == null || request.isBlank()) {
            return null;
        }
        List<String> argList = new ArrayList<>();
        String[] query = request.split("\\?");
        String endpoint = query[0];
        if(query.length > 1) {
            String args = query[1];
            String[] argArray = args.split("&");
            argList = Arrays.stream(argArray).toList();
        }
        return new RequestStat(endpoint, argList, requestStatEntity.getHitNumber());
    }
}
