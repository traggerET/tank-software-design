package ru.mipt.bit.platformer.core.commands;

import ru.mipt.bit.platformer.core.IShooting;

public class ShootCommand implements ICommand {
    private final IShooting shooting;
    public ShootCommand(IShooting shooting) {
        this.shooting = shooting;
    }

    @Override
    public void execute() {
        shooting.shoot();
    }
}