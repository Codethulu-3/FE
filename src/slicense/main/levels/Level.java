package slicense.main.levels;

import java.awt.Graphics;
import java.util.ArrayList;
import main.pathfinding.AStarPathfinding;
import slicense.main.Handler;
import slicense.main.Utils;
import slicense.main.entities.EntityManager;
import slicense.main.entities.Unit1;
import slicense.main.tiles.*;

/**
 *
 * CodeNMore
 */
public class Level {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private AStarPathfinding as;
    
    private Tile[][] tiles;
    
    //Entities
    private EntityManager entityManager;
    

    public Level(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler);       
        loadWorld(path);
        as = new AStarPathfinding(this);
    }
    
    public void initEntities() {
        entityManager.addEntity(new Unit1(handler, 5, 6));
        entityManager.addEntity(new Unit1(handler, 8, 10));
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
                int screenX = (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset());
                int screenY = (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset());
                tiles[x][y].render(g, screenX, screenY);
            }
        }
        //Entities
        entityManager.render(g);
        //camera
        handler.getGameCamera().updateCamera(g);
    }

    public TileID getTileID(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {//if no tile is found
            return TileID.Null; //defaults to null
        }

        return tiles[x][y].getID();
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new Tile[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tt = Utils.parseInt(tokens[(x + y * width) + 4]);
                switch (tt) {
                    case 0:
                        tiles[x][y] = new GrassTile(handler, x, y);
                        break;
                    case 1:
                        tiles[x][y] = new WaterTile(handler, x, y);
                        break;
                    default:
                        System.err.println("Unregonized tile id " + tt + "!");
                        break;
                }
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
    
    public Tile[][] getTiles(){
        return tiles;
    }
    
    public Tile getTileAt(int worldX, int worldY) {
        return tiles[worldX][worldY];
    }
    
    public boolean outOfBounds(int worldX, int worldY) {
        if (worldX < 0 || worldY < 0 || worldX >= width || worldY >= height)
            return true;
        return false;
    }
    
    public AStarPathfinding getPathing() {
        return as;
    }
    
    public ArrayList<Tile> getWalkableNeighbors(Tile tile) {
        ArrayList<Tile> walkableNeighbors = new ArrayList<>();
        for (int x = -1; x <= 1; x += 2) {
            Tile neighborToCheck = tiles[tile.getWorldX() + x][tile.getWorldY()];
            if (!neighborToCheck.isBlocked()) {
                walkableNeighbors.add(neighborToCheck);
            }
        }
        for (int y = -1; y <= 1; y += 2) {
            Tile neighborToCheck = tiles[tile.getWorldX()][tile.getWorldY() + y];
            if (!neighborToCheck.isBlocked()) {
                walkableNeighbors.add(neighborToCheck);
            }
        }
        return walkableNeighbors;
    }
}
