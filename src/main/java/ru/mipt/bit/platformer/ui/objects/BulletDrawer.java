package ru.mipt.bit.platformer.ui.objects;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.core.objects.Bullet;

public class BulletDrawer extends AbstractDrawer{
    private final Texture texture;

    public BulletDrawer(Texture texture) {
        this.texture  = texture;
    }

    @Override
    public void draw(IGameObject obj) {
        if (!(obj instanceof Bullet)) {
            return;
        }

        BulletDrawable tankDrawable = new BulletDrawable((Bullet) obj, texture, renderer.getTileMovement());
        renderer.addDrawableObject(tankDrawable);
    }
    @Override
    public void dispose() {

    }
}
