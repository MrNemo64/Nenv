package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.EnvironmentEntry;

import java.util.Optional;

@FunctionalInterface
public interface NenvParser<T extends EnvironmentEntry<?>> {

    Optional<? extends T> tryParse(String key, String value);

}
