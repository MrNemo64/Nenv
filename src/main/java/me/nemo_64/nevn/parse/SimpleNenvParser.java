package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.EnvironmentEntry;
import me.nemo_64.nevn.entry.BooleanEnvironmentEntry;
import me.nemo_64.nevn.entry.CharacterEnvironmentEntry;
import me.nemo_64.nevn.entry.NumericalEnvironmentEntry;
import me.nemo_64.nevn.entry.StringEnvironmentEntry;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimpleNenvParser implements NenvParser {

    @Override
    public Map<String, EnvironmentEntry<?>> parse(Map<String, String> lines) {
        return lines.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> parse(entry.getKey(), entry.getValue())));
    }

    protected EnvironmentEntry<?> parse(String key, String value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");

        // Check for boolean
        Boolean booleanValue = tryParseBoolean(value);
        if(booleanValue != null)
            return new BooleanEnvironmentEntry(key, booleanValue);

        // Check for number
        Number numericalValue = tryParseNumber(value);
        if(numericalValue != null)
            return new NumericalEnvironmentEntry(key, numericalValue);

        // Check for char
        Character charValue = tryParseChar(value);
        if(charValue != null)
            return new CharacterEnvironmentEntry(key, charValue);

        // Only can be string
        return new StringEnvironmentEntry(key, value);
    }

    protected Boolean tryParseBoolean(String value) {
        if("true".equalsIgnoreCase(value)) {
            return Boolean.TRUE;
        } else if("false".equalsIgnoreCase(value)) {
            return Boolean.FALSE;
        }
        return null;
    }

    protected Number tryParseNumber(String value) {
        if("NaN".equals(value))
            return Double.NaN;
        Integer integerValue = tryParseNumber(value, Integer::parseInt);
        if(integerValue != null)
            return integerValue;
        Long longValue = tryParseNumber(value, Long::parseLong);
        if(longValue != null)
            return longValue;
        BigInteger bigIntegerValue = tryParseNumber(value, BigInteger::new);
        if(bigIntegerValue != null)
            return bigIntegerValue;
        BigDecimal bigDecimalValue = tryParseNumber(value, BigDecimal::new);
        if(bigDecimalValue != null) {
            BigDecimal doubleValue = BigDecimal.valueOf(bigDecimalValue.doubleValue());
            return bigDecimalValue.equals(doubleValue) ? bigDecimalValue.doubleValue() : bigDecimalValue;
        }
        return null;
    }

    protected Character tryParseChar(String value) {
        return value.length() == 1 ? value.charAt(0) : null;
    }

    protected <T extends Number> T tryParseNumber(String value, Function<String, T> parser) {
        try {
            return parser.apply(value);
        } catch (Exception e) {
            return null;
        }
    }

}
