package graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;
    private int count;
    private float delay;
    private int timePlayed;
    public Animation(BufferedImage[] frames) {
        timePlayed = 0;
        setFrames(frames);
    }
    public Animation() {
        timePlayed = 0;
    }
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        delay = 2f;
        timePlayed = 0;
        numFrames = frames.length;

    }
    public void setDelay(int contor) {delay = contor;}
    public void setFrames(int contor) {currentFrame = contor;}
    public void setNumFrames(int contor) {numFrames = contor;}
    public void update() {
        if(delay == -1) return;

        count++;
        if (count == delay) {
            currentFrame++;
            count = 0;
        }
        if (currentFrame == numFrames) {
            currentFrame = 0;
            timePlayed++;
        }
    }
    public float getDelay() { return delay;}
    public int getFrame() { return currentFrame;}
    public int getCount() { return count;}
    public BufferedImage getImage() { return frames[currentFrame];}
    public boolean hasPlayedOnce() {return timePlayed > 0;}
    public boolean hasPlayed(int contor) {return timePlayed == contor;}


}
