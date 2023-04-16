package me.nemo_64.nevn.entry;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class BooleanEnvironmentEntry extends AbstractEnvironmentEntry<Boolean> {

    public static final BooleanEnvironmentEntry TRUE = new BooleanEnvironmentEntry(true);
    public static final BooleanEnvironmentEntry FALSE = new BooleanEnvironmentEntry(false);

    public static BooleanEnvironmentEntry of(boolean value) {
        return value ? TRUE : FALSE;
    }

    private BooleanEnvironmentEntry(boolean value) {
        super(value);
    }

    @Override
    public Optional<Boolean> asBoolean() {
        return Optional.of(getValue());
    }

    @Override
    public Optional<Number> asNumber() {
        return Optional.empty();
    }

    @Override
    public Optional<Float> asFloat() {
        return Optional.empty();
    }

    @Override
    public Optional<Double> asDouble() {
        return Optional.empty();
    }

    @Override
    public Optional<BigDecimal> asBigDecimal() {
        return Optional.empty();
    }

    @Override
    public Optional<Byte> asByte() {
        return Optional.empty();
    }

    @Override
    public Optional<Short> asShort() {
        return Optional.empty();
    }

    @Override
    public Optional<Integer> asInteger() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> asLong() {
        return Optional.empty();
    }

    @Override
    public Optional<BigInteger> asBigInteger() {
        return Optional.empty();
    }

    @Override
    public Optional<Character> asCharacter() {
        return Optional.empty();
    }

}
