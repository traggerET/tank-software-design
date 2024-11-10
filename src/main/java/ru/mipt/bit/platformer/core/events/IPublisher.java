package ru.mipt.bit.platformer.core.events;

public interface IPublisher {
    void addListener(Events event, IListener eventListener);

    void removeListener(Events event, IListener eventListener);

    void fireEvent(Events event, Object object);
}

