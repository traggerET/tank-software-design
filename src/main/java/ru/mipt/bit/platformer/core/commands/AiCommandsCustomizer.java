package ru.mipt.bit.platformer.core.commands;

import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;

public class AiCommandsCustomizer implements ICommandsCustomizer {
    public AiCommandsCustomizer() {
    }

    public Map<Integer, ICommandFactory> getKnownCommands() {
        var movableFactory = new MovableCommandFactory();
        var shootingFactory = new ShootingCommandFactory();
        return Map.of(
                RIGHT, movableFactory,
                LEFT, movableFactory,
                DOWN, movableFactory,
                UP, movableFactory,
                SPACE, shootingFactory
        );
    }
}
