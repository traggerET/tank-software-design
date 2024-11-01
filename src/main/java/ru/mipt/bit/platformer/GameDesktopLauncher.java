package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.core.ai.AIMock;
import ru.mipt.bit.platformer.core.mapgenerator.FileMapGenerator;
import ru.mipt.bit.platformer.core.mapgenerator.IMapGenerator;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.ui.Render;
import ru.mipt.bit.platformer.ui.Renderer;
import ru.mipt.bit.platformer.ui.objects.DrawHpToggler;

import java.util.ArrayList;
import java.util.List;

public class GameDesktopLauncher implements ApplicationListener {
    private Render render;
    private Renderer renderer;

    private Tank tank;
    private final DrawHpToggler drawHp =  new DrawHpToggler();
    private List<Tank> npcTanks;
    private AIMock npcController;
    private InputHandler inputHandler;

    private static final String TmxMapFileName = "level.tmx";
    private static final String TankTexturePath = "images/tank_blue.png";
    private static final String TreeTexturePath = "images/greenTree.png";
    private static final String TxtMapPath = "src/main/resources/map.txt";

    // level width: 10 tiles x 128px, height: 8 tiles x 128px
    private static final int Width = 1280;
    private static final int Height = 1024;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(Width, Height);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        IMapGenerator mapGenerator = new FileMapGenerator(TxtMapPath);
        tank = mapGenerator.getTank();
        npcTanks = mapGenerator.getNpcTanks();
        npcController = new AIMock(npcTanks);

        render = new Render(TmxMapFileName, TankTexturePath, TreeTexturePath, drawHp);

        var tanks = new ArrayList<>(npcTanks);
        tanks.add(tank);

        renderer = render.render(tanks, mapGenerator.getTrees());
        inputHandler = new InputHandler(tank, drawHp);
    }

    @Override
    public void render() {
        inputHandler.handleInputs();

        if (!npcTanks.isEmpty()) {
            npcController.newCommand().execute();
        }

        tank.processMovementProgress(Gdx.graphics.getDeltaTime());
        for (Tank tank : npcTanks) {
            tank.processMovementProgress(Gdx.graphics.getDeltaTime());
        }

        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        for (Disposable disposable : render.getDisposables()) {
            disposable.dispose();
        }
    }
}