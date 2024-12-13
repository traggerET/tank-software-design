package ru.mipt.bit.platformer.ui.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.ui.Drawable;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.core.objects.Bullet;


import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class BulletDrawable implements Drawable {
    private final Bullet bullet;
    private final TextureRegion textureRegion;
    private final TileMovement tileMovement;
    private Rectangle rectangle;

    public BulletDrawable(Bullet bullet, Texture texture, TileMovement tileMovement) {
        this.bullet = bullet;
        this.textureRegion = new TextureRegion(texture);
        this.tileMovement = tileMovement;
        this.rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void drawTexture(Batch batch) {
        rectangle = tileMovement.moveRectangleBetweenTileCenters(rectangle, bullet.getCoordinates(),
                bullet.getDestinationCoordinates(), bullet.getMovementProgress());

        drawTextureRegionUnscaled(batch, textureRegion, rectangle, bullet.getRotation());
    }

    @Override
    public Object getObj() {
        return bullet;
    }
}