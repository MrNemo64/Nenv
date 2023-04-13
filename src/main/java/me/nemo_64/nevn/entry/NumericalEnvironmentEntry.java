package me.nemo_64.nevn.entry;

import me.nemo_64.nevn.EnvironmentEntry;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class NumericalEnvironmentEntry extends AbstractEnvironmentEntry<Number> {

    public NumericalEnvironmentEntry(String key, Number value) {
        super(key, value);
    }

    @Override
    public Optional<Boolean> asBoolean() {
        return Optional.empty();
    }

    @Override
    public Optional<Number> asNumber() {
        return Optional.of(getValue());
    }

    @Override
    public Optional<Float> asFloat() {
        return Optional.of(getValue().floatValue());
    }

    @Override
    public Optional<Double> asDouble() {
        return Optional.of(getValue().doubleValue());
    }

    @Override
    public Optional<BigDecimal> asBigDecimal() {
        if(getValue() instanceof BigDecimal)
            return Optional.of((BigDecimal) getValue());
        return Optional.of(BigDecimal.valueOf(getValue().doubleValue()));
    }

    @Override
    public Optional<Byte> asByte() {
        return Optional.of(getValue().byteValue());
    }

    @Override
    public Optional<Short> asShort() {
        return Optional.of(getValue().shortValue());
    }

    @Override
    public Optional<Integer> asInteger() {
        return Optional.of(getValue().intValue());
    }

    @Override
    public Optional<Long> asLong() {
        return Optional.of(getValue().longValue());
    }

    @Override
    public Optional<BigInteger> asBigInteger() {
        if (getValue() instanceof BigInteger)
            return Optional.of((BigInteger) getValue());
        return Optional.of(BigInteger.valueOf(getValue().longValue()));
    }

    @Override
    public Optional<Character> asCharacter() {
        return Optional.empty();
    }

    @Override
    public NumericalEnvironmentEntry copy() {
        return new NumericalEnvironmentEntry(getKey(), getValue());
    }

}
