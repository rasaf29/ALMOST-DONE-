package states;

import entity.Player;
import graphics.Sprite;
import graphics.Font;
import tiles.TileManager;
import utils.GamePanel;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.Vector2f;

import java.awt.*;

public class PlayState extends GameState{

    public static Vector2f map;

    TileManager tm;
    private Font font;
    private Player player;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        tm = new TileManager("tile/NIVELUL1.xml");
        font = new Font("font/RedWood.png", 15 , 25);
        player = new Player(new Sprite("entity/Dorian.png"), new Vector2f(0 + (GamePanel.width / 2) - 200, 0 + (GamePanel.height / 2) + 50), 120);
    }
    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    @Override
    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, "HP", new Vector2f(0,0), 30, 50, 25, 0);
        Sprite.drawArray(g, font, "STAMINA", new Vector2f(0,45), 30, 50, 25, 0);
        player.render(g);
    }
}
