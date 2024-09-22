package ru.mipt.bit.platformer.core;

import com.badlogic.gdx.math.GridPoint2;

public enum Direction {
    UP {
        public GridPoint2 getVector() {
            return new GridPoint2(0, 1);
        }
        public float getRotation() {
            return 90f;
        }
    },
    DOWN {
        public GridPoint2 getVector() {
            return new GridPoint2(0, -1);
        }
        public float getRotation() {
            return -90f;
        }
    },
    LEFT {
        public GridPoint2 getVector() {
            return new GridPoint2(-1, 0);
        }
        public float getRotation() {
            return -180f;
        }
    },
    RIGHT {
        public GridPoint2 getVector() {
            return new GridPoint2(1, 0);
        }
        public float getRotation() {
            return 0f;
        }
    };

    public abstract GridPoint2 getVector();
    public abstract float getRotation();
}
