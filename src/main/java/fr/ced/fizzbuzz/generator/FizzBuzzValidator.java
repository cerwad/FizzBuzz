package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.commons.errors.BadArgumentsException;
import fr.ced.fizzbuzz.commons.errors.MissingRequiredArgument;
import fr.ced.fizzbuzz.generator.model.IntMatcher;
import org.springframework.stereotype.Component;

/**
 * Argument validator for the Fizzbuzz service
 */
@Component
public class FizzBuzzValidator {

    public void validateLimit(Integer limit){
        if(limit == null) {
            throw new MissingRequiredArgument("limit");
        }
        if(limit > 100){
            throw new BadArgumentsException("Limit cannot exceed 100");
        }
        if(limit <= 0){
            throw new BadArgumentsException("Limit cannot be negative or 0");
        }
    }

    public void assertNotNull(Integer int1, Integer int2){
        if(int1 == null) {
            throw new MissingRequiredArgument("int1");
        }
        if(int2 == null) {
            throw new MissingRequiredArgument("int2");
        }
    }

    public void validateMatcher(IntMatcher matcher, int limit){
        if(matcher.multiple() <= 0){
            throw new BadArgumentsException("An integer matcher cannot be negative or equal zero");
        }
        if(matcher.multiple() > limit){
            throw new BadArgumentsException("An integer matcher cannot be greater than the limit");
        }
        if(matcher.text() == null || matcher.text().isBlank()){
            throw new BadArgumentsException("The replacement text cannot be empty");
        }
        if(matcher.text().length() > 256){
            throw new BadArgumentsException("The replacement text max length is 256 char");
        }
    }

}
