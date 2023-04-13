package me.nemo_64.nevn.parse;

import me.nemo_64.nevn.EnvironmentEntry;

import java.util.Map;

@FunctionalInterface
public interface NenvParser {

    Map<String, EnvironmentEntry<?>> parse(Map<String, String> lines);

}
