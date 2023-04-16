package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.BooleanEnvironmentEntry;

import java.util.Optional;

public class BooleanNenvParser implements NenvParser<BooleanEnvironmentEntry> {

    public static final BooleanNenvParser INSTANCE = new BooleanNenvParser();

    private BooleanNenvParser() {}

    @Override
    public Optional<? extends BooleanEnvironmentEntry> tryParse(String key, String value) {
        if("true".equalsIgnoreCase(value)) {
            return Optional.of(BooleanEnvironmentEntry.TRUE);
        } else if("false".equalsIgnoreCase(value)) {
            return Optional.of(BooleanEnvironmentEntry.FALSE);
        }
        return Optional.empty();
    }
}
