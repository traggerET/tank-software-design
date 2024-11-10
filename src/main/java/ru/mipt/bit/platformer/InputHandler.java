package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;
import ru.mipt.bit.platformer.ui.objects.DrawHpToggler;

import static com.badlogic.gdx.Input.Keys.*;

public class InputHandler {
    private final Tank tank;
    private final DrawHpToggler drawHp;

    public InputHandler(Tank tank, DrawHpToggler drawHp) {
        this.tank = tank;
        this.drawHp = drawHp;
    }

    public void handleInputs() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (tank.canMoveInThisTick()) {
                tank.move(Direction.UP);
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (tank.canMoveInThisTick()) {
                tank.move(Direction.LEFT);
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (tank.canMoveInThisTick()) {
                tank.move(Direction.DOWN);
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (tank.canMoveInThisTick()) {
                tank.move(Direction.RIGHT);
            }
        }
        if (Gdx.input.isKeyPressed(L)) {
            drawHp.switchToggler();
        }
        if (Gdx.input.isKeyPressed(SPACE)) {
            tank.shoot();
        }
    }
}
