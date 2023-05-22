package fr.ced.fizzbuzz.stats;

import java.util.List;

public record RequestStat(String endpoint, List<String> args, int hitNumber) {
}
