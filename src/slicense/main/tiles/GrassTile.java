package slicense.main.tiles;

import java.awt.Graphics;
import slicense.main.gfx.Assets;
import static slicense.main.tiles.Tile.TILEHEIGHT;
import static slicense.main.tiles.Tile.TILEWIDTH;

public class GrassTile extends Tile {

	public GrassTile(int worldX, int worldY) {
            super(worldX, worldY, Assets.grass, TileID.Grass);
	}

        @Override
        public void render(Graphics g, int x, int y) {
        g.drawImage(Assets.grass, x, y, TILEWIDTH, TILEHEIGHT, null);
        }
}
