package me.nemo_64.nevn;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public interface EnvironmentEntry<T> {

    Optional<Boolean> asBoolean();

    Optional<Number> asNumber();

    Optional<Float> asFloat();

    Optional<Double> asDouble();

    Optional<BigDecimal> asBigDecimal();

    Optional<Byte> asByte();

    Optional<Short> asShort();

    Optional<Integer> asInteger();

    Optional<Long> asLong();

    Optional<BigInteger> asBigInteger();

    Optional<Character> asCharacter();

    Optional<String> asString();

    EnvironmentEntry<T> copy();

    boolean isOf(Class<?> clazz);

    T getValue();

    String getKey();

}
