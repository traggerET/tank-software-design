package ru.mipt.bit.platformer.core.ai;

import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.Direction;


public class TankCommands {
    public static class StayCommand implements ICommand {

        public StayCommand() {}

        @Override
        public void execute() {}
    }

    public static class Up implements ICommand {
        private final Tank tank;

        public Up(Tank tank) {
            this.tank = tank;
        }

        @Override
        public void execute() {
            tank.move(Direction.UP);
        }
    }

    public static class Right implements ICommand {
        private final Tank tank;

        public Right(Tank tank) {
            this.tank = tank;
        }

        @Override
        public void execute() {
            tank.move(Direction.RIGHT);
        }
    }

    public static class Left implements ICommand {
        private final Tank tank;

        public Left(Tank tank) {
            this.tank = tank;
        }

        @Override
        public void execute() {
            tank.move(Direction.LEFT);
        }
    }

    public static class Down implements ICommand {
        private final Tank tank;

        public Down(Tank tank) {
            this.tank = tank;
        }

        @Override
        public void execute() {
            tank.move(Direction.DOWN);
        }
    }
}
