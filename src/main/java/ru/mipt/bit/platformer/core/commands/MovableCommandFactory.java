package ru.mipt.bit.platformer.core.commands;

import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.objects.Movable;

import static com.badlogic.gdx.Input.Keys.*;


public class MovableCommandFactory implements ICommandFactory {
    public MovableCommandFactory() {
    }



    @Override
    public ICommand newCommand(Integer option, IGameObject obj) {
        var movable = (Movable)obj;
        switch (option)  {
            case  (RIGHT):
            case (D):
                return new MoveCommand(movable, Direction.RIGHT);
            case (LEFT):
            case (A):
                return new MoveCommand(movable, Direction.LEFT);
            case (DOWN):
            case (S):
                return new MoveCommand(movable, Direction.DOWN);
            case (UP):
            case (W):
                return new MoveCommand(movable, Direction.UP);
        }
        return null;
    }
}
