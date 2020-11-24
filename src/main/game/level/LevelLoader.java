package level;

import entities.EntitiesLayer;
import entities.Entity;
import entities.livingEntity.Bomber;
import entities.livingEntity.enemy.Balloom;
import entities.livingEntity.enemy.Oneal;
import entities.tile.Brick;
import entities.tile.Grass;
import entities.tile.Portal;
import entities.tile.Wall;
import graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private static int height = 20, width = 20, level;

    private static char[][] map;

    public static void loadLevel(int level, List<Entity> entities, List<Entity> stillObject) {
        clearCurrentLevel(entities, stillObject);
        loadLevelFromFile(level);
        createEntities(entities, stillObject);
    }

    private static void clearCurrentLevel(List<Entity> entities, List<Entity> stillObject) {
        entities.clear();
        stillObject.clear();
    }

    public static void loadLevelFromFile(int current_level) {
        List<String> list = new ArrayList<>();

        try {
            FileReader fr = new FileReader("src\\main\\resources\\levels\\Level" + current_level + ".txt");//doc tep luu map
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
                //doc file txt luu vao list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] arrays = list.get(0).trim().split(" ");
        level = Integer.parseInt(arrays[0]);
        height = Integer.parseInt(arrays[1]);
        width = Integer.parseInt(arrays[2]);
        map = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i + 1).charAt(j);
            }
        }
    }

    public static void createEntities(List<Entity> entities, List<Entity> stillObject) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = map[y][x];
                switch (c) {
                    // Thêm grass
                    case ' ':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        break;
                    // Thêm Wall
                    case '#':
                        stillObject.add(new Wall(x, y, Sprite.wall.getFxImage()));
                        break;
                    // Thêm Portal
                    case 'x':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new Portal(x, y, Sprite.portal.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;
                    // Thêm brick
                    case '*':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;
                    // Thêm Bomber
                    case 'p':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Bomber(x, y, Sprite.player_right.getFxImage(), stillObject));
                        break;

                    // Thêm balloom
                    case '1':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Balloom(x, y, Sprite.balloom_right1.getFxImage()));
                        break;
                    // Thêm oneal
                    case '2':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Oneal(x, y, Sprite.balloom_right1.getFxImage()));
                        break;
                    // Thêm doll
                    case '3':

                        break;
                    // Thêm BomItem
                    case 'b':

                        break;
                    // Thêm SpeedItem
                    case 's':

                        break;
                    // Thêm FlameItem
                    case 'f':

                        break;

                }
            }
        }
    }
}
