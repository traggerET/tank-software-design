package ru.mipt.bit.platformer.core.objects;

import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

public class MapNavigator {
    private final int height;
    private final int width;

    private final List<Tree> trees;
    private final List<Tank> tanks;
    private final int navigatorOwnerId;

    public MapNavigator(int width, int height, List<Tree> trees, List<Tank> tanks, int navigatorOwnerId) {
        this.height = height;
        this.width = width;
        this.trees = trees;
        this.tanks = tanks;
        this.navigatorOwnerId = navigatorOwnerId;
    }

    public boolean isFreeTile(GridPoint2 pos) {
        if (pos.x < 0 || pos.x >= width) {
            return false;
        }
        if (pos.y < 0 || pos.y >= height) {
            return false;
        }
        for (var tree: trees) {
            if (pos.equals(tree.getCoordinates())) {
                return false;
            }
        }
        for (var tank: tanks) {
            if (tank == tanks.get(navigatorOwnerId)) {
                continue;
            }

            if (pos.equals(tank.getCoordinates())) {
                return false;
            }

            if (pos.equals(tank.getPlayerDestinationCoordinates())) {
                return false;
            }
        }
        return true;
    }
}
