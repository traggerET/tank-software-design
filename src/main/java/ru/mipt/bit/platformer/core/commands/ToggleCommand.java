package ru.mipt.bit.platformer.core.commands;

public class ToggleCommand implements ICommand {
    private final IToggle toggle;

    public ToggleCommand(IToggle toggle) {
        this.toggle = toggle;
    }

    public void execute() {
        toggle.switchToggle();
    }
}
