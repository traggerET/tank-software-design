package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.core.commands.ICommandProducer;
import ru.mipt.bit.platformer.core.commands.*;
import ru.mipt.bit.platformer.core.events.Events;
import ru.mipt.bit.platformer.core.mapgenerator.FileMapGenerator;
import ru.mipt.bit.platformer.core.mapgenerator.IMapGenerator;
import ru.mipt.bit.platformer.core.objects.*;
import ru.mipt.bit.platformer.ui.Render;
import ru.mipt.bit.platformer.ui.Renderer;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.core.events.Events.*;

public class GameDesktopLauncher implements ApplicationListener {
    private Render render;
    private Renderer renderer;

    private Tank tank;
    private List<Tank> npcTanks;

    // level width: 10 tiles x 128px, height: 8 tiles x 128px
    private static final int Width = 1280;
    private static final int Height = 1024;

    private CollisionManager collisionManager;

    private final List<ICommandProducer> commandProducers = new ArrayList<>();
    private GameObjectsManager gomgr;


    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(Width, Height);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }

    @Override
    public void create() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appbeans.xml");

        IMapGenerator mapGenerator = context.getBean("fmapgen", FileMapGenerator.class);
        mapGenerator.generate();
        tank = mapGenerator.getPlayerTank();
        npcTanks = mapGenerator.getNpcTanks();
        gomgr = mapGenerator.getGameObjectsManager();

        configureAi();
        configurePlayer();

        var al = new ArrayList<Collidable>(npcTanks);
        al.addAll(mapGenerator.getTrees());
        al.add(tank);
        collisionManager = new CollisionManager(al);

        render = context.getBean("urender", Render.class);

        List<Events> events = new ArrayList<>();
        events.add(BULLET_STOPPED);
        events.add(TANK_BROKEN);
        events.add(SHOOT);
        EPublisher epub = new EPublisher(events);
        epub.addListener(SHOOT, render.getRenderer());
        epub.addListener(TANK_BROKEN, render.getRenderer());
        epub.addListener(BULLET_STOPPED, render.getRenderer());

        epub.addListener(SHOOT, gomgr);
        epub.addListener(TANK_BROKEN, gomgr);
        epub.addListener(BULLET_STOPPED, gomgr);

        var tanks = new ArrayList<>(npcTanks);
        tanks.add(tank);

        for (Tank value : tanks) {
            value.setPublisher(epub);
        }

        renderer = render.render(gomgr.getGameObjects());
    }

    @Override
    public void render() {
        for (var producer : commandProducers) {
            producer.nextCommand().execute();
        }

        collisionManager.manageCollisions();

        for (IGameObject obj : gomgr.getGameObjects()) {
            obj.processProgress(Gdx.graphics.getDeltaTime());
        }

        renderer.render();
    }

    private void configureAi() {
       for (var npcTank: npcTanks) {
            commandProducers.add(new AiPlayer(new AiCommandsCustomizer().getKnownCommands(), npcTank));
        }
    }

    private void configurePlayer() {
        commandProducers.add(new HumanPlayer(new HumanCommandsCustomizer().getKnownCommands(), tank));
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