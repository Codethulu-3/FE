package slicense.main.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * CodeNMore
 */
public class Assets {

    private static final int width = 32, height = 32;
    
    public static BufferedImage water,grass;
    public static BufferedImage highlight;
    
    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        
        water = sheet.crop(width * 1, 0, width, height);
        grass = sheet.crop(0, 0, width, height);
        
        highlight = sheet.crop(width, height, width, height);

    }
    
    //spritesheet crop reference
    /* 0,0 1,0 2,0 ...
     * 0,1 1,1 2,1 ...
     * 0,2
     * 0,3
     */
    
}
