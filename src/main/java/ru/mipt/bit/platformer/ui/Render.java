package ru.mipt.bit.platformer.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.core.commands.IGameObject;
import ru.mipt.bit.platformer.ui.objects.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Render {
    private final Renderer renderer;
    private final List<Disposable> disposables = new ArrayList<>();
    private final DrawHpToggler drawHp;
    private final List<AbstractDrawer> drawers;

    public Render(String levelConfigFileName, List<AbstractDrawer> drawers, String bulletTexture, DrawHpToggler drawHp) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appbeans.xml");

        TmxMapLoader loader = context.getBean("tmxmaploader", TmxMapLoader.class);
        var lvl = loader.load(levelConfigFileName);
        var batch = context.getBean("batch", SpriteBatch.class);
        var bTexture = new Texture(bulletTexture);
        renderer = new Renderer(batch, lvl, new ArrayList<>(), bTexture);
        for (var drawer: drawers) {
            drawer.setRenderer(renderer);
        }
        this.drawers = drawers;
        disposables.addAll(drawers);
        disposables.add(lvl);
        disposables.add(batch);
        disposables.add(bTexture);
       this.drawHp = drawHp;
    }

    public List<Disposable> getDisposables() {
        return disposables;
    }

    public Renderer render(List<IGameObject> objs) {
        for (var obj : objs) {
            for (var drawer: drawers) {
                drawer.draw(obj);
            }
        }
        return renderer;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
