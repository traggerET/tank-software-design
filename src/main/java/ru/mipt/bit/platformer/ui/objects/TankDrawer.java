package ru.mipt.bit.platformer.ui.objects;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.core.objects.Tank;

public class TankDrawer extends AbstractDrawer {
    private final DrawHpToggler drawHpToggler;
    private final Texture texture;
    public TankDrawer(Texture texture, DrawHpToggler drawHpToggler) {
        this.drawHpToggler = drawHpToggler;
        this.texture = texture;
    }

    @Override
    public void draw(IGameObject obj) {
        if (!(obj instanceof Tank)) {
            return;
        }

        TankDrawable tankDrawable = new HpDecorator((Tank) obj, texture, renderer.getTileMovement(), drawHpToggler);
        renderer.addDrawableObject(tankDrawable);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
