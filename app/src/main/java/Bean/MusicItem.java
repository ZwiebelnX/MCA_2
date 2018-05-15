package Bean;

import java.util.Random;

public class MusicItem{
    private int ShapeIndex;
    private int ColorIndex;
    private int BrightIndex;
    private int sound;

    public MusicItem() {
        Random rand = new Random();
        sound = rand.nextInt(24)+1;//生成音调1-24
        ShapeIndex = rand.nextInt(3)+1;//生成形状号码 1-3

        if(ShapeIndex == 2){
            ColorIndex = rand.nextInt(2)+1; //圆形只有两种颜色
        }
        else{
            ColorIndex = rand.nextInt(3)+1;
        }

        if(sound<=8){   //不同音调对应不同的颜色深浅
            BrightIndex = 1;
        }
        else if(sound>8 && sound<=16){
            BrightIndex = 2;
        }
        else{
            BrightIndex = 3;
        }

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
