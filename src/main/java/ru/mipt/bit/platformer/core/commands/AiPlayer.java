package ru.mipt.bit.platformer.core.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AiPlayer implements ICommandProducer {
    private final Map<Integer, ICommandFactory> knownCommandsFactories;
    private final IGameObject obj;


    public AiPlayer(Map<Integer, ICommandFactory> knownCommandsFactories, IGameObject obj) {
        this.knownCommandsFactories = knownCommandsFactories;
        this.obj = obj;
    }

    @Override
    public ICommand nextCommand() {
        List<ICommandFactory> factories = new ArrayList<>(knownCommandsFactories.values());
        var factoryId = new Random().nextInt(factories.size());
        return factories.get(factoryId).newCommand(factoryId, obj);
    }
}
