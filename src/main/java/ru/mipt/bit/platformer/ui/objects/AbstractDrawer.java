package ru.mipt.bit.platformer.ui.objects;

import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.ui.Renderer;

public abstract class AbstractDrawer implements Disposable {
    protected Renderer renderer;

    public void draw(IGameObject obj) {
    }
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

}
