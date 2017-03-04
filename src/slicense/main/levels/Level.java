package slicense.main.levels;

import java.awt.Graphics;
import slicense.main.Handler;
import slicense.main.Utils;
import slicense.main.entities.EntityManager;
import slicense.main.entities.Unit1;
import slicense.main.tiles.Tile;

/**
 *
 * CodeNMore
 */
public class Level {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    
    private int[][] tiles;
    
    //Entities
    private EntityManager entityManager;
    

    public Level(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler);
        
        entityManager.addEntity(new Unit1(handler, Tile.TILEWIDTH * 2,Tile.TILEHEIGHT*2));
        entityManager.addEntity(new Unit1(handler, Tile.TILEWIDTH *3,Tile.TILEHEIGHT*2));
        
        loadWorld(path);
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Entities
        entityManager.render(g);
        //camera
        handler.getGameCamera().updateCamera(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {//if no tile is found
            return Tile.nullTile;//defaults to grass
        }

        Tile t = Tile.tiles[tiles[x][y]];//determines which tile to put where
        if (t == null) { //backup case if the tile is not found
            return Tile.grassTile;//still is grass
        }
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    public int[][] getTiles(){
        return tiles;
    }
}
