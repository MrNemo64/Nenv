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

    boolean isOf(Class<?> clazz);

    Class<? extends T> getValueClass();

    T getValue();

}
