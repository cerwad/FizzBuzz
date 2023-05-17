package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.commons.errors.MissingRequiredArgument;
import fr.ced.fizzbuzz.generator.model.IntMatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
@RequiredArgsConstructor
public class FizzBuzzHandler {

    private FizzBuzzValidator fizzBuzzValidator;
    public ServerResponse generate(ServerRequest serverRequest){
        String strInt1 = (String) serverRequest.attribute("int1").orElseThrow(() -> new MissingRequiredArgument("int1"));
        String strInt2 = (String) serverRequest.attribute("int2").orElseThrow(() -> new MissingRequiredArgument("int2"));
        String text1 = (String) serverRequest.attribute("text1").orElseThrow(() -> new MissingRequiredArgument("text1"));
        String text2 = (String) serverRequest.attribute("text2").orElseThrow(() -> new MissingRequiredArgument("text2"));
        String strLim = (String) serverRequest.attribute("limit").orElseThrow(() -> new MissingRequiredArgument("limit"));

        int limit = fizzBuzzValidator.parseInt(strLim);
        IntMatcher intMatcher1 = new IntMatcher(fizzBuzzValidator.parseInt(strInt1), text1);
        IntMatcher intMatcher2 = new IntMatcher(fizzBuzzValidator.parseInt(strInt2), text2);
        fizzBuzzValidator.validateLimit(limit);
        fizzBuzzValidator.validateMatcher(intMatcher1, limit);
        fizzBuzzValidator.validateMatcher(intMatcher2, limit);

        return ServerResponse.ok().build();
    }

    public ServerResponse stats(ServerRequest serverRequest){
        return ServerResponse.ok().build();
    }
}
