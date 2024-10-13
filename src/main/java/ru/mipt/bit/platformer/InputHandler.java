package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.core.Direction;
import ru.mipt.bit.platformer.core.objects.Tank;
import ru.mipt.bit.platformer.core.objects.Tree;

import static com.badlogic.gdx.Input.Keys.*;

public class InputHandler {
    private final Tank tank;

    public InputHandler(Tank tank) {
        this.tank = tank;
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
    }
}
