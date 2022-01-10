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
            while (!listOfEntities.isEmpty()) {
                listOfEntities.remove(0);
            }
            listOfEntities.add(new Skeleton());
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
            while (!listOfEntities.isEmpty()) {
                listOfEntities.remove(0);
            }
            listOfEntities.add(new Skeleton());
            listOfEntities.add(new Skeleton(648, 648));
            listOfEntities.add(new Skeleton(0, 648));
            listOfEntities.add(new Boss());
            map = new int[][]{
                    {1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                    {1, 0, 0, 1, 1, 1, 0, 1, 1, 0},
                    {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
                    {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                    {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                    {0, 0, 0, 0, 1, 1, 1, 0, 1, 0},
                    {0, 1, 1, 1, 1, 0, 0, 0, 1, 0},
                    {0, 0, 0, 1, 0, 1, 1, 0, 0, 0}
            };

        }
    }
}
