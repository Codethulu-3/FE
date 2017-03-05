package main.utils;

import slicense.main.tiles.Tile;

public class SelectionNode {
    //Used to generate the highlights for what an entity can do when selected
    
    private Tile tile;
    private int dist; //The distance from the unit
    
    public SelectionNode(Tile tile, int dist) {
        this.tile = tile;
        this.dist = dist;
    }
    
    public Tile getTile() {
        return tile;
    }
    
    public int getDist() {
        return dist;
    }
}
