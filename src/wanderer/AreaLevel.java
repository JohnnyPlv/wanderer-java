package wanderer;

import java.util.ArrayList;
import java.util.List;
// TODO class to generate the are levels
public class AreaLevel {
    int currentArea;
    List<Entity> listOfEntities;
    int [][] map;

    public AreaLevel(int currentArea) {
        this.currentArea = currentArea;
        listOfEntities = new ArrayList<>();
        generateLevel();
    }

    public void generateLevel() {
        if (currentArea == 1) {
            listOfEntities.clear(); // better way to use built in method .clear instead of using while loop and remove them one by one
            listOfEntities.add(new Skeleton(216,216));
            listOfEntities.add(new Skeleton(648, 648));
            listOfEntities.add(new Skeleton(0, 648));
            listOfEntities.add(new Boss());
            map = new int[][]{
                    {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                    {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                    {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
                    {0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1, 1, 0, 0, 0}
            };

        }
        if (currentArea == 2) {
            listOfEntities.clear();
            listOfEntities.add(new Skeleton(0,576));
            listOfEntities.add(new Skeleton(576, 576));
            listOfEntities.add(new Skeleton(0, 648));
            listOfEntities.add(new Skeleton(144, 648));
            listOfEntities.add(new Boss());
            map = new int[][]{
                    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
                    {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                    {0, 0, 0, 0, 0, 1, 1, 0, 1, 0},
                    {0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1, 1, 0, 0, 0}
            };

        }
    }
}
