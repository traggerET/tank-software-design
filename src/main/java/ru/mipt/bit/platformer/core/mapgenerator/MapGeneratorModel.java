package ru.mipt.bit.platformer.core.mapgenerator;

public enum MapGeneratorModel {
    RANDOM("RANDOM_GEN"),
    FROM_FILE_PLAIN_TEXT("FILE_GEN"),
            ;

    private final String name;

    MapGeneratorModel(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
