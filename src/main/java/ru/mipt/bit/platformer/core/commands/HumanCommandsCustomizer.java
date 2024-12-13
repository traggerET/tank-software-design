package ru.mipt.bit.platformer.core.commands;

import java.util.Map;
import static com.badlogic.gdx.Input.Keys.*;


public class HumanCommandsCustomizer implements ICommandsCustomizer{
    public HumanCommandsCustomizer() {
    }

    public Map<Integer, ICommandFactory> getKnownCommands() {
        var movableFactory = new MovableCommandFactory();
        var shootingFactory = new ShootingCommandFactory();
        return Map.of(
                RIGHT, movableFactory,
                LEFT, movableFactory,
                DOWN, movableFactory,
                UP, movableFactory,
                D, movableFactory,
                S, movableFactory,
                A, movableFactory,
                W, movableFactory,
                SPACE, shootingFactory,
                L, new ToggleCommandFactory()
        );
    }
}
