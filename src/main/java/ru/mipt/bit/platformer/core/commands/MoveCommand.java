package ru.mipt.bit.platformer.core.commands;

import ru.mipt.bit.platformer.core.objects.Movable;
import ru.mipt.bit.platformer.core.Direction;


public class MoveCommand implements ICommand {

    private final Movable movable;
    private final Direction direction;
    public MoveCommand(Movable movable, Direction direction) {
        this.direction = direction;
        this.movable = movable;
    }

    @Override
    public void execute() {
        movable.move(direction);
    }
}
