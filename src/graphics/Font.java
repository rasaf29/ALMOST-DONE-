package graphics;

import utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Font {

    private  BufferedImage FONT_SHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;
    public Font(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");
        FONT_SHEET = loadFont(file);
        wLetter = FONT_SHEET.getWidth() / w;
        hLetter = FONT_SHEET.getHeight() / h;
        loadSpriteArray();
    }
    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;
        System.out.println("Loading: " + file + "...");
        FONT_SHEET = loadFont(file);
        wLetter = FONT_SHEET.getWidth() / w;
        hLetter = FONT_SHEET.getHeight() / h;
        loadSpriteArray();
    }

    public void setSize(int width, int heigth) {
        setWidth(width);
        setHeight(heigth);
    }

    public void setWidth(int i) {
        w = i;
        wLetter = FONT_SHEET.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hLetter = FONT_SHEET.getHeight() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    private BufferedImage loadFont(String file){
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("Could not load file: " + file + "!!!");

        }
        return sprite;
    }
    public void loadSpriteArray() {
        spriteArray = new BufferedImage[wLetter][hLetter]; // w = coloane , h = rows
        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                spriteArray[x][y] = getLetter(x,y);
            }
        }
    }
    public BufferedImage getSpriteSheet() {
        return FONT_SHEET;
    }
    public BufferedImage getLetter(int x, int y) {
        return FONT_SHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getFont(char letter) {
        int value = letter - 65;
        int x = value % wLetter;
        int y = value / wLetter;
        return getLetter(x,y);
    }


}
