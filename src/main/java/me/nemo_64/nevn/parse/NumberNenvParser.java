package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.NumericalEnvironmentEntry;

import java.math.BigDecimal;
import java.util.Optional;

public class NumberNenvParser implements NenvParser<NumericalEnvironmentEntry> {

    public static final NumberNenvParser INSTANCE = new NumberNenvParser();

    private NumberNenvParser() {}

    @Override
    public Optional<? extends NumericalEnvironmentEntry> tryParse(String key, String value) {
        try {
            BigDecimal decimalValue = new BigDecimal(value);
            if(decimalValue.scale() == 0) { // No decimals
                // Fit in a byte
                if(hasIntegerRepresentation(decimalValue, Byte.MIN_VALUE, Byte.MAX_VALUE))
                    return Optional.of(NumericalEnvironmentEntry.of(decimalValue.byteValueExact()));

                // Fit in a short
                if(hasIntegerRepresentation(decimalValue, Short.MIN_VALUE, Short.MAX_VALUE))
                    return Optional.of(NumericalEnvironmentEntry.of(decimalValue.shortValueExact()));

                // Fit in an integer
                if(hasIntegerRepresentation(decimalValue, Integer.MIN_VALUE, Integer.MAX_VALUE))
                    return Optional.of(NumericalEnvironmentEntry.of(decimalValue.intValueExact()));

                // Fit in a long
                if(hasIntegerRepresentation(decimalValue, Long.MIN_VALUE, Long.MAX_VALUE))
                    return Optional.of(NumericalEnvironmentEntry.of(decimalValue.longValueExact()));

                // Need a big integer
                return Optional.of(NumericalEnvironmentEntry.of(decimalValue.toBigInteger()));
            } else {
                // Representable as float
                if(hasFloatRepresentation(decimalValue))
                    return Optional.of(NumericalEnvironmentEntry.of(decimalValue.floatValue()));

                // Representable as double
                if(hasDoubleRepresentation(decimalValue))
                    return Optional.of(NumericalEnvironmentEntry.of(decimalValue.doubleValue()));

                // Decimal value that cannot be represented by float or decimal
                return Optional.of(NumericalEnvironmentEntry.of(decimalValue));
            }
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    protected boolean hasIntegerRepresentation(BigDecimal value, long min, long max) {
        final BigDecimal decimalMin = BigDecimal.valueOf(min);
        final BigDecimal decimalMax = BigDecimal.valueOf(max);
        return decimalMin.compareTo(value) <= 0 // min <= value
                && value.compareTo(decimalMax) <= 0; // value <= max
    }

    protected boolean hasFloatRepresentation(BigDecimal value) {
        float result = value.floatValue();
        if(Float.isNaN(result) || Float.isInfinite(result))
            return false;
        return new BigDecimal(String.valueOf(result)).compareTo(value) == 0;
    }

    protected boolean hasDoubleRepresentation(BigDecimal value) {
        double result = value.doubleValue();
        if(Double.isNaN(result) || Double.isInfinite(result))
            return false;
        return new BigDecimal(String.valueOf(result)).compareTo(value) == 0;
    }

}
