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
        for (int i = 1; i <= limit; i++) {
            if (i % matcher1.multiple() == 0 || i % matcher2.multiple() == 0) {
                if (i % matcher1.multiple() == 0) {
                    buffer.append(matcher1.text());
                }
                if (i % matcher2.multiple() == 0) {
                    buffer.append(matcher2.text());
                }
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
