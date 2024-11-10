package ru.mipt.bit.platformer.core.objects;

import java.util.List;

public class CollisionManager {
    List<Collidable> collidables;

    public CollisionManager(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    public void manageCollisions() {
        for (int i = 0; i < collidables.size(); i++) {
            for (Collidable collidable : collidables) {
                if (!collidables.get(i).equals(collidable) &&
                        collidables.get(i).getCoordinates() == collidable.getCoordinates()) {
                    collidable.doCollide(collidables.get(i));
                    collidables.get(i).doCollide(collidable);
                }
            }
        }
    }

    public void removeCollidable(Collidable collidable) {
        collidables.remove(collidable);
    }
    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

}
