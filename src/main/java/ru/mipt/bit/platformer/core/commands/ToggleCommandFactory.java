package ru.mipt.bit.platformer.core.commands;

public class ToggleCommandFactory implements ICommandFactory {
    public ToggleCommandFactory() {
    }
    @Override
    public ICommand newCommand(Integer option, IGameObject obj) {
        return new ToggleCommand((IToggle) obj);
    }
}
