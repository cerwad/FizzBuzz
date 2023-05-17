package fr.ced.fizzbuzz.generator

import fr.ced.fizzbuzz.commons.errors.BadArgumentsException
import fr.ced.fizzbuzz.commons.errors.MissingRequiredArgument
import fr.ced.fizzbuzz.generator.model.IntMatcher
import spock.lang.Specification
import spock.lang.Subject

/**
 * Data driven test using Spock
 * Testing the Validation class
 */
class FizzBuzzValidatorSpec extends Specification {

    @Subject
    FizzBuzzValidator fizzBuzzValidator = new FizzBuzzValidator()

    def 'test invalid limit'() {
        when:
        fizzBuzzValidator.validateLimit(limit)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        limit                     || expectedException | expectedMessage
        101                       || BadArgumentsException         | 'Limit cannot exceed 100'
        -1                        || BadArgumentsException         | 'Limit cannot be negative or 0'
        0                         || BadArgumentsException         | 'Limit cannot be negative or 0'
        null                      || MissingRequiredArgument         | 'Missing required argument: limit'
    }

    def "test validateLimit"() {
        when:
            Integer lim = Integer.valueOf(limit)
            fizzBuzzValidator.validateLimit(lim)

        then:
            noExceptionThrown()

        where:
            limit << [1, 50, 100]
    }

    def 'test int not null'() {
        when:
        fizzBuzzValidator.assertNotNull(int1, int2)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        int1    | int2            || expectedException | expectedMessage
        null    |   2            || MissingRequiredArgument         | 'Missing required argument: int1'
        1       | null           || MissingRequiredArgument         | 'Missing required argument: int2'
        null    | null           || MissingRequiredArgument         | 'Missing required argument: int1'
    }

    def 'test validateMatcher bad argument'() {
        given:
        IntMatcher matcher = new IntMatcher(multiple, text)

        when:
        fizzBuzzValidator.validateMatcher(matcher, limit)

        then:
        def error = thrown(expectedException)
        error.message == expectedMessage

        where:
        multiple    | text      | limit     || expectedException | expectedMessage
        -1      |   "Fizz"      | 100       || BadArgumentsException         | "An integer matcher cannot be negative"
        101     | "Buzz"        | 100       || BadArgumentsException         | "An integer matcher cannot be greater than the limit"
        3       | null          | 100       || BadArgumentsException         | "The replacement text cannot be empty"
        3       | "a"*257       | 100       || BadArgumentsException         | "The replacement text max length is 256 char"
    }

}
