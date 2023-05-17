package fr.ced.fizzbuzz.commons.errors;

public class MissingRequiredArgument extends BadArgumentsException {

    public MissingRequiredArgument(String argumentName) {
        super("Missing required argument: "+argumentName);
    }
}
