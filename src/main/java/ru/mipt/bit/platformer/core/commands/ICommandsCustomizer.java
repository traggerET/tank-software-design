package ru.mipt.bit.platformer.core.commands;

import java.util.Map;

public interface ICommandsCustomizer {
    Map<Integer, ICommandFactory> getKnownCommands();
}
