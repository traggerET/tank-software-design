package ru.mipt.bit.platformer.core.ai;

import ru.mipt.bit.platformer.core.objects.Tank;

import java.util.List;

public class AIMock {
    private final List<Tank> aiTanks;

    public AIMock(List<Tank> aiTanks) {
        this.aiTanks = aiTanks;
    }

    public ICommand newCommand() {
        int commandNum = (int) (Math.random() * 7);
        int aiTankNum = ((int) (Math.random() * 100)) % aiTanks.size();

        switch (commandNum) {
            case 0:
                return new TankCommands.Right(aiTanks.get(aiTankNum));
            case 1:
                return new TankCommands.Up(aiTanks.get(aiTankNum));
            case 2:
                return new TankCommands.Left(aiTanks.get(aiTankNum));
            case 3:
                return new TankCommands.Down(aiTanks.get(aiTankNum));
            default:
                return new TankCommands.StayCommand();
        }
    }
}