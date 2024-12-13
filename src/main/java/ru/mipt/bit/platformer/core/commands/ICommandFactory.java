package ru.mipt.bit.platformer.core.commands;

public interface ICommandFactory{
    ICommand newCommand(Integer option, IGameObject obj);
}
