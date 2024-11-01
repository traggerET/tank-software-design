package ru.mipt.bit.platformer.ui.objects;

import ru.mipt.bit.platformer.core.objects.Toggler;

public class DrawHpToggler implements Toggler {
    boolean drawHp;
    @Override
    public void switchToggler() {
        drawHp = !drawHp;
    }

    @Override
    public boolean getValue() {
        return drawHp;
    }
}
