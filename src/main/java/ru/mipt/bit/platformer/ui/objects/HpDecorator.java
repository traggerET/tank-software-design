package ru.mipt.bit.platformer.ui.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

public class HpDecorator extends TankDrawable {
    private DrawHpToggler toggler;

    public HpDecorator(Tank tank, Texture texture, TileMovement tileMovement, DrawHpToggler toggler) {
        super(tank, texture, tileMovement);
        this.toggler = toggler;
    }

    private static TextureRegion getHealthBar(float health, Color color) {
        Pixmap pixmap = new Pixmap((int) (90 * health / 100), 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, (int) (90 * health / 100), 20);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(texture);
    }

    @Override
    public void drawTexture(Batch batch) {
        if (toggler.getValue()) {
            TextureRegion healthBgBar = getHealthBar(100, Color.RED);
            TextureRegion healthLeftBar = getHealthBar(tank.getHp(), Color.GREEN);
            Rectangle hpRectangle = new Rectangle(rectangle);
            hpRectangle.y += 90;
            GdxGameUtils.drawTextureRegionUnscaled(batch, healthBgBar, hpRectangle, 0);
            GdxGameUtils.drawTextureRegionUnscaled(batch, healthLeftBar, hpRectangle, 0);
        }
        super.drawTexture(batch);
    }
}
