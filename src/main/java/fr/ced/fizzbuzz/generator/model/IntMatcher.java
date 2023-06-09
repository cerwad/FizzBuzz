package fr.ced.fizzbuzz.generator.model;

public record IntMatcher(int multiple, String text) {

    public boolean isMultiple(int number){
        return number % multiple() == 0;
    }

    public String getString(int number){
        if(isMultiple(number)){
            return text;
        }
        return "";
    }
}
