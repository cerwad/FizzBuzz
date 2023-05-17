package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.commons.errors.BadArgumentsException;
import fr.ced.fizzbuzz.generator.model.IntMatcher;
import org.springframework.stereotype.Component;

@Component
public class FizzBuzzValidator {

    public void validateLimit(int limit){
        if(limit > 100){
            throw new BadArgumentsException("Limit cannot exceed 100");
        }
        if(limit < 0){
            throw new BadArgumentsException("Limit cannot be negative");
        }
    }

    public void validateMatcher(IntMatcher matcher, int limit){
        if(matcher.multiple() < 0){
            throw new BadArgumentsException("An integer matcher cannot be negative");
        }
        if(matcher.multiple() > limit){
            throw new BadArgumentsException("An integer matcher cannot be greater than the limit");
        }
        if(matcher.text() == null || matcher.text().isBlank()){
            throw new BadArgumentsException("The replacement text cannot be empty");
        }
    }

    public int parseInt(String strInt){
        Integer number;
        try {
            number = Integer.parseInt(strInt);
        } catch (NumberFormatException e){
            throw new BadArgumentsException("Parameter "+strInt+" must be an integer");
        }
        return number;
    }
}
