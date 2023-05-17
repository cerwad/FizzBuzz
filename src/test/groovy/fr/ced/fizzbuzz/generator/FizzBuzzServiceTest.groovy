package fr.ced.fizzbuzz.generator


import fr.ced.fizzbuzz.generator.model.IntMatcher
import spock.lang.Specification
import spock.lang.Subject

class FizzBuzzServiceTest extends Specification {
    @Subject
    FizzBuzzService fizzBuzzService = new FizzBuzzService()


    def "test fizzbuzz with matchers: {#int1,#text1}, {#int2,#text2} and limit #limit"() {
        when:
        IntMatcher matcher1 = new IntMatcher(int1, text1)
        IntMatcher matcher2 = new IntMatcher(int2, text2)
        def actualValue = fizzBuzzService.generateFizzBuzzString(matcher1, matcher2, limit)

        then:
        noExceptionThrown()
        actualValue == expectedString

        where:
        int1    | text1     | int2   | text2   | limit     || expectedString
        3       |   "Fizz"  | 5      | "Buzz"  | 25        || "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz,16,17,Fizz,19,Buzz,Fizz,22,23,Fizz,Buzz"
        1       |   "un"    | 5      | "cinq"  | 10         || "un,un,un,un,uncinq,un,un,un,un,uncinq"
    }

}
