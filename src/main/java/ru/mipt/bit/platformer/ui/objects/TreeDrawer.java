package ru.mipt.bit.platformer.ui.objects;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.core.objects.Tree;

public class TreeDrawer extends AbstractDrawer {
    private Texture texture;
    public TreeDrawer(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(IGameObject obj) {
        if (!(obj instanceof Tree)) {
            return;
        }

        Tree tree = (Tree) obj;

        var treeDrawable = new TreeDrawable(tree, texture, renderer.getTileMovement());
        renderer.addDrawableObject(treeDrawable);
        renderer.moveRectangleAtTileCenter(treeDrawable.getRectangle(), tree.getCoordinates());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}