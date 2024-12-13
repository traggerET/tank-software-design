package ru.mipt.bit.platformer.core.commands;

import ru.mipt.bit.platformer.core.IShooting;

public class ShootingCommandFactory implements ICommandFactory {
    public ShootingCommandFactory() {}

    @Override
    public ICommand newCommand(Integer option, IGameObject obj) {
        return new ShootCommand((IShooting)obj);
    }
}
