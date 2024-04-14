package tiles.blocks;

import utils.AABB;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block{

    public NormBlock(BufferedImage img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

    @Override
    public boolean update(AABB p) {
        return false;
    }
}
