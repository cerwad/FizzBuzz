package fr.ced.fizzbuzz.generator;

import fr.ced.fizzbuzz.generator.model.IntMatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/fizzbuzz")
@RequiredArgsConstructor
@Slf4j
public class FizzBuzzController {

    private final FizzBuzzValidator fizzBuzzValidator;

    @GetMapping("/")
    public String home(){
        return "Please call /generate?int1=3&text1=Fizz&int2=5&text2=Buzz&limit=100 to test this server";
    }
    @GetMapping("/generate")
    public String generate(@RequestParam Integer int1, @RequestParam String text1, @RequestParam Integer int2, @RequestParam String text2, @RequestParam Integer limit){
        log.info("Received request with args "+int1+" "+text1);
        fizzBuzzValidator.validateLimit(limit);
        fizzBuzzValidator.assertNotNull(int1, int2);
        IntMatcher intMatcher1 = new IntMatcher(int1, text1);
        IntMatcher intMatcher2 = new IntMatcher(int2, text2);
        fizzBuzzValidator.validateMatcher(intMatcher1, limit);
        fizzBuzzValidator.validateMatcher(intMatcher2, limit);

        return String.format("placeholder i1: %d s1: %s i2: %d s2: %s lim: %d", int1, text1, int2, text2, limit);
    }

    @GetMapping("/stats")
    public String stats(){
        return "placeholder stats";
    }
}
