package ru.mipt.bit.platformer.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;
import ru.mipt.bit.platformer.ui.objects.TankDrawable;
import ru.mipt.bit.platformer.ui.objects.TreeDrawable;

import java.util.ArrayList;
import java.util.List;

public class Render {
    private final Renderer renderer;
    private final List<Disposable> disposables = new ArrayList<>();
    private final Texture tankTexture;
    private final Texture treeTexture;

    public Render(String levelConfigFileName, String tankTextureFile, String treeTextureFile) {
        var lvl = new TmxMapLoader().load(levelConfigFileName);
        var batch = new SpriteBatch();
        renderer = new Renderer(batch, lvl, new ArrayList<>());
        tankTexture = new Texture(tankTextureFile);
        disposables.add(tankTexture);
        disposables.add(lvl);
        disposables.add(batch);
        treeTexture = new Texture(treeTextureFile);
        disposables.add(treeTexture);
    }

    public List<Disposable> getDisposables() {
        return disposables;
    }

    public Renderer render(List<Tank> tanks, List<Tree> trees) {
        renderTanks(tanks);
        renderTrees(trees);
        return renderer;
    }

    private void renderTrees(List<Tree> trees) {
        for (Tree tree : trees) {
            TreeDrawable treeGraphics = new TreeDrawable(tree, treeTexture, renderer.getTileMovement());
            renderer.addDrawableObject(treeGraphics);
            renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
        }
    }

    private void renderTanks(List<Tank> tanks) {
        for (Tank tank : tanks) {
            TankDrawable tankDrawable = new TankDrawable(tank, tankTexture, renderer.getTileMovement());
            renderer.addDrawableObject(tankDrawable);
        }
    }
}
