package me.nemo_64.nevn.entry;

import me.nemo_64.nevn.EnvironmentEntry;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractEnvironmentEntry<T> implements EnvironmentEntry<T> {

    private final T value;

    protected AbstractEnvironmentEntry(T value) {
        this.value = Objects.requireNonNull(value, "value");
    }

    @Override
    public Optional<String> asString() {
        return Optional.of(getValue().toString());
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public boolean isOf(Class<?> clazz) {
        return clazz.isAssignableFrom(getValue().getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof EnvironmentEntry)) return false;
        EnvironmentEntry<?> that = (EnvironmentEntry<?>) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
