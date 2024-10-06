package ru.mipt.bit.platformer.core;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP {
        public GridPoint2 getVector() {
            return new GridPoint2(0, 1);
        }
        public float getRotation() {
            return UP_ROTATION;
        }
    },
    DOWN {
        public GridPoint2 getVector() {
            return new GridPoint2(0, -1);
        }
        public float getRotation() {
            return DOWN_ROTATION;
        }
    },
    LEFT {
        public GridPoint2 getVector() {
            return new GridPoint2(-1, 0);
        }
        public float getRotation() {
            return LEFT_ROTATION;
        }
    },
    RIGHT {
        public GridPoint2 getVector() {
            return new GridPoint2(1, 0);
        }
        public float getRotation() {
            return RIGHT_ROTATION;
        }
    };

    public abstract GridPoint2 getVector();
    public abstract float getRotation();

    private static final float UP_ROTATION = 90f;
    private static final float DOWN_ROTATION = -UP_ROTATION;
    private static final float LEFT_ROTATION = -180f;
    private static final float RIGHT_ROTATION = 0f;
}
