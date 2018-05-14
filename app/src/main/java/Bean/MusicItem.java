package Bean;

import java.util.Random;

public class MusicItem {
    private int ShapeIndex;
    private int ColorIndex;
    private int sound;

    public MusicItem() {
        Random rand = new Random();
        ShapeIndex = rand.nextInt(4)+1;
        ColorIndex = rand.nextInt(4)+1;
        sound = rand.nextInt(9)+1;
    }

    public int getShapeIndex() {
        return ShapeIndex;
    }

    public int getColorIndex() {
        return ColorIndex;
    }

    public int getSound() {
        return sound;
    }

    public void setShapeIndex(int shapeIndex) {
        ShapeIndex = shapeIndex;
    }

    public void setColorIndex(int colorIndex) {
        ColorIndex = colorIndex;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }
}
