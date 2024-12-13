package ru.mipt.bit.platformer.core.commands;

import com.badlogic.gdx.Gdx;

import java.util.Map;

public class HumanPlayer implements ICommandProducer {
    private final Map<Integer, ICommandFactory> knownCommandsFactories;
    private final IGameObject obj;

    public HumanPlayer(Map<Integer, ICommandFactory> knownCommandsFactories, IGameObject obj) {
        this.knownCommandsFactories = knownCommandsFactories;
        this.obj = obj;
    }
    @Override
    public ICommand nextCommand() {
        for (var entry : knownCommandsFactories.entrySet()) {
            if (Gdx.input.isKeyPressed(entry.getKey())) {
                return entry.getValue().newCommand(entry.getKey(), obj);
            }
        }
        return null;
    }
}
