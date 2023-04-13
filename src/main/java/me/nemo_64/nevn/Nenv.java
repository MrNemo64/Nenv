package me.nemo_64.nevn;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Nenv implements Map<String, Object> {

    public static NenvBuilder builder() {
        return new NenvBuilder();
    }

    private final Map<String, EnvironmentEntry<?>> entries;
    private final Nenv fallback;

    Nenv(Map<String, EnvironmentEntry<?>> entries, Nenv fallback) {
        this.entries = Collections.unmodifiableMap(Objects.requireNonNull(entries, "entries"));
        this.fallback = fallback;
    }

    public Optional<EnvironmentEntry<?>> getEntry(String key) {
        if(!containsKey(key))
            return getFallback().flatMap(env -> env.getEntry(key));
        return Optional.of(entries.get(key));
    }

    public Optional<Boolean> getAsBoolean(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asBoolean);
    }

    public Optional<Number> getAsNumber(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asNumber);
    }

    public Optional<Float> getAsFloat(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asFloat);
    }

    public Optional<Double> getAsDouble(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asDouble);
    }

    public Optional<BigDecimal> getAsBigDecimal(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asBigDecimal);
    }

    public Optional<Byte> getAsByte(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asByte);
    }

    public Optional<Short> getAsShort(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asShort);
    }

    public Optional<Integer> getAsInteger(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asInteger);
    }

    public Optional<Long> getAsLong(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asLong);
    }

    public Optional<BigInteger> getAsBigInteger(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asBigInteger);
    }

    public Optional<Character> getAsCharacter(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asCharacter);
    }

    public Optional<String> getAsString(String key) {
        return getEntry(key).flatMap(EnvironmentEntry::asString);
    }

    public boolean hasAs(String key, Class<?> clazz) {
        return getEntry(key).map((environmentEntry -> environmentEntry.isOf(clazz))).orElse(false);
    }

    public boolean hasFallback() {
        return fallback != null;
    }

    public Optional<Nenv> getFallback() {
        return Optional.ofNullable(fallback);
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return entries.containsKey(key) || (fallback != null && fallback.containsKey(key));
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value) || (fallback != null && fallback.containsValue(value));
    }

    @Override
    public Object get(Object key) {
        throw new UnsupportedOperationException("Use Nenv#getEntry to get values of this Nenv");
    }

    @Override
    public Object put(String key, Object value) {
        throw new UnsupportedOperationException("Cannot edit entries of a Nenv");
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException("Cannot edit entries of a Nenv");
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        throw new UnsupportedOperationException("Cannot edit entries of a Nenv");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot edit entries of a Nenv");
    }

    @Override
    public Set<String> keySet() {
        return Collections.unmodifiableSet(entries.keySet());
    }

    @Override
    public Collection<Object> values() {
        return entries.values()
                .stream()
                .map(EnvironmentEntry::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return entries.entrySet()
                .stream()
                .map(entry -> Map.entry(entry.getKey(), (Object) entry.getValue().getValue()))
                .collect(Collectors.toUnmodifiableSet());
    }
}
