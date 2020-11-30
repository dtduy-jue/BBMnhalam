package level;

import entities.EntitiesLayer;
import entities.Entity;
import entities.animatedEntity.Bomber;
import entities.animatedEntity.bomb.Bomb;
import entities.animatedEntity.enemy.*;
import entities.tile.Brick;
import entities.tile.Grass;
import entities.tile.Portal;
import entities.tile.Wall;
import entities.tile.item.*;
import graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {
    private static int height = 20, width = 20, level;

    private static char[][] map;

    public static void loadLevel(int level, List<Entity> entities, List<Bomb> bombs, List<Entity> stillObject) {
        clearCurrentLevel(entities, bombs, stillObject);
        loadLevelFromFile(level);
        createEntities(entities, bombs, stillObject);
    }

    public static void loadLevelWthoutClearing(int level, List<Entity> entities, List<Bomb> bombs, List<Entity> stillObject) {
        loadLevelFromFile(level);
        createEntities(entities, bombs, stillObject);
    }

    private static void clearCurrentLevel(List<Entity> entities, List<Bomb> bombs, List<Entity> stillObject) {
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

    public static void createEntities(List<Entity> entities, List<Bomb> bombs, List<Entity> stillObject) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = map[y][x];
                switch (c) {
                    // Thêm grass
                    case ' ':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage())));

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
                        entities.add(new Bomber(x, y, Sprite.player_right.getFxImage(), stillObject, bombs, entities));
                        break;

                    // Thêm balloom
                    case '1':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Balloom(x, y, Sprite.balloom_right1.getFxImage(), stillObject, bombs));
                        break;
                    // Thêm oneal
                    case '2':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Oneal(x, y, Sprite.balloom_right1.getFxImage(), stillObject, bombs));
                        break;
                    // Thêm doll
                    case '3':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Doll(x, y, Sprite.doll_right1.getFxImage(), stillObject, bombs));
                        break;

                    case '4':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Minvo(x, y, Sprite.minvo_right1.getFxImage(), stillObject, bombs));
                        break;

                    case '5':
                        stillObject.add(new Grass(x, y, Sprite.grass.getFxImage()));
                        entities.add(new Kondoria(x, y, Sprite.kondoria_right1.getFxImage(), stillObject, bombs));
                        break;

                     // Thêm BomItem
                    case 'b':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new BombItem(x, y, Sprite.powerup_bombs.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;

                    // Thêm SpeedItem
                    case 's':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new SpeedItem(x, y, Sprite.powerup_speed.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;

                    // Thêm FlameItem
                    case 'f':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new FlameItem(x, y, Sprite.powerup_flames.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;

                    case 'w':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new WallPassItem(x, y, Sprite.powerup_wallpass.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;

                    case 'y':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new FlamePassItem(x, y, Sprite.powerup_flamepass.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;
                    case 'o':
                        stillObject.add(new EntitiesLayer(x, y,
                                new Grass(x, y, Sprite.grass.getFxImage()),
                                new BombPassItem(x, y, Sprite.powerup_bombpass.getFxImage()),
                                new Brick(x, y, Sprite.brick.getFxImage())));
                        break;
                }
            }
        }
    }
}
