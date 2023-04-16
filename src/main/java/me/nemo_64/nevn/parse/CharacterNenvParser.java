package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.entry.CharacterEnvironmentEntry;

import java.util.Optional;

public class CharacterNenvParser implements NenvParser<CharacterEnvironmentEntry> {

    public static final CharacterNenvParser INSTANCE = new CharacterNenvParser();

    private CharacterNenvParser() {}

    @Override
    public Optional<? extends CharacterEnvironmentEntry> tryParse(String key, String value) {
        return Optional.ofNullable(value.length() == 1 ? CharacterEnvironmentEntry.of(value.charAt(0)) : null);
    }
}
