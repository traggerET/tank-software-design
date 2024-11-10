package ru.mipt.bit.platformer.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.core.events.Events;
import ru.mipt.bit.platformer.core.events.IListener;
import ru.mipt.bit.platformer.core.objects.Bullet;
import ru.mipt.bit.platformer.ui.objects.BulletDrawable;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Renderer implements IListener {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final List<Drawable> drawables;
    private final Texture bulletTexture;

    public Renderer(Batch batch, TiledMap level, List<Drawable> drawables, String bulletTexture) {
        this.batch = batch;
        this.drawables = drawables;
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.bulletTexture = new Texture(bulletTexture);
    }

    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        
        levelRenderer.render();

        batch.begin();
        for (Drawable drawable : drawables) {
            drawable.drawTexture(batch);
        }

        batch.end();
    }

    public void moveRectangleAtTileCenter(Rectangle rectangle, GridPoint2 coordinates) {
        GdxGameUtils.moveRectangleAtTileCenter(groundLayer, rectangle, coordinates);
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public void addDrawableObject(Drawable drawable) {
        drawables.add(drawable);
    }

    @Override
    public void handle(Events event, Object obj) {
        if (event.equals(Events.SHOOT)) {
            BulletDrawable bullet = new BulletDrawable((Bullet) obj, bulletTexture, getTileMovement());
            drawables.add(bullet);
        }
        if (event.equals(Events.TANK_BROKEN) || event.equals(Events.BULLET_STOPPED)) {
            drawables.removeIf(drawable -> drawable.getObj() == obj);
        }
    }
}