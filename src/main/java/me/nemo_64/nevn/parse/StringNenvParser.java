package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.StringEnvironmentEntry;

import java.util.Optional;

public class StringNenvParser implements NenvParser<StringEnvironmentEntry> {

    public static final StringNenvParser INSTANCE = new StringNenvParser();

    @Override
    public Optional<? extends StringEnvironmentEntry> tryParse(String key, String value) {
        return Optional.of(new StringEnvironmentEntry(key, value));
    }
}
