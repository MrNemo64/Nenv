package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.NumericalEnvironmentEntry;

import java.util.Optional;

public class NumberNenvParser implements NenvParser<NumericalEnvironmentEntry> {

    public static final NumberNenvParser INSTANCE = new NumberNenvParser();

    private NumberNenvParser() {}

    @Override
    public Optional<? extends NumericalEnvironmentEntry> tryParse(String key, String value) {
        throw new UnsupportedOperationException("");
    }

}
