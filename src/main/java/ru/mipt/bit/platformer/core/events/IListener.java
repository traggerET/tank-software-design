package ru.mipt.bit.platformer.core.events;

public interface IListener {
    void handle(Events event, Object object);
}
