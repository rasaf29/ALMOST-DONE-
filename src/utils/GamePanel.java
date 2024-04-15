package utils;

import states.GameStateManager;
import utils.KeyHandler;
import utils.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements  Runnable{

    public static int width, height;

    private Thread thread;
    private boolean running;
    private BufferedImage img;
    private Graphics2D g;

    private GameStateManager gsm;

    private MouseHandler mouse;
    private KeyHandler key;

    public GamePanel(int width , int height) {

        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();


    }

    public void addNotify() {
        super.addNotify();

        if(thread ==  null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }
    @Override
    public void run() {
        init();

        final double GAME_HZ = 60.0;
        final double TBU = 1000000000 / GAME_HZ; // time before update

        final int MUBR = 5; // most updates before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double FPS = 60;
        final double TBR = 1000000000 / FPS; // time before render

        int fpsCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while(running) {

            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                input(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }
            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }
            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            fpsCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if(fpsCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + fpsCount);
                    oldFrameCount = fpsCount;
                }
                fpsCount = 0;
                lastSecondTime= thisSecond;
            }
            while (now - lastRenderTime < TBR && now - lastUpdateTime < TBU) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch(Exception e) {
                    System.out.println("ERROR: yielding thread");

                }
                now = System.nanoTime();
            }
        }
    }


    private void update() {
        gsm.update();
    }
    private void input(MouseHandler mouse, KeyHandler key) {
        gsm.input(mouse,key);
    }
    private void render() {
        if ( g != null) {
            g.setColor(new Color(0,0,0));
            g.fillRect(0,0, width, height);
            gsm.render(g);
        }

    }
    private void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();

    }

}
