package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.generator.model.IntMatcher;
import org.springframework.stereotype.Service;

/**
 * Service generating a string with a list of numbers with some replaced by strings
 */
@Service
public class FizzBuzzService {

    public String generateFizzBuzzString(IntMatcher matcher1, IntMatcher matcher2, int limit) {
        StringBuilder buffer = new StringBuilder();
        if(matcher1.multiple() <= 0 || matcher2.multiple() <= 0){
            return "";
        }
        for (int i = 1; i <= limit; i++) {
            if (matcher1.isMultiple(i) || matcher2.isMultiple(i)) {
                buffer.append(matcher1.getString(i));
                buffer.append(matcher2.getString(i));
           } else {
                buffer.append(i);
            }
            if (i != limit) {
                buffer.append(',');
            }
        }
        return buffer.toString();
    }
}
