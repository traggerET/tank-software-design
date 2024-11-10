package ru.mipt.bit.platformer.core.objects;

import ru.mipt.bit.platformer.core.events.Events;
import ru.mipt.bit.platformer.core.events.IListener;
import ru.mipt.bit.platformer.core.events.IPublisher;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class EPublisher implements IPublisher {
    private final Map<Events, List<IListener>> listeners = new HashMap<>();

    public EPublisher(List<Events> eventTypes) {
        eventTypes.forEach(event -> listeners.put(event, new ArrayList<>()));
    }

    @Override
    public void addListener(Events event, IListener listener) {
        listeners.get(event).add(listener);
    }

    @Override
    public void removeListener(Events event, IListener listener) {
        listeners.get(event).remove(listener);
    }

    @Override
    public void fireEvent(Events event, Object object) {
        for (IListener listener : listeners.get(event)) {
            listener.handle(event, object);
        }
    }
}
