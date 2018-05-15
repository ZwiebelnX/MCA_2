package Bean;

import android.graphics.Bitmap;

import java.util.Random;

public class MusicItem{
    private int ShapeIndex;
    private int ColorIndex;
    private int BrightIndex;
    private int sound;
    private int X,Y; //图形坐标 在左上角
    private int cX, cY; //修正坐标 在图形中央 用于绘图
    private Bitmap img;

    public MusicItem() {
        Random rand = new Random();
        sound = rand.nextInt(21)+1;//生成音调1-21
        ShapeIndex = rand.nextInt(3)+1;//生成形状号码 1-3
        if(ShapeIndex != 3){
            ColorIndex = rand.nextInt(2)+1; //圆形只有两种颜色
        }
        else{
            ColorIndex = rand.nextInt(3)+1;
        }

        if(sound<=7){   //不同音调对应不同的颜色深浅
            BrightIndex = 1;
        }
        else if(sound<=16){
            BrightIndex = 2;
        }
        else{
            BrightIndex = 3;
        }
        X = rand.nextInt(100)+1;
        Y = rand.nextInt(100)+1;
        currectXY();

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

    public int getBrightIndex() {
        return BrightIndex;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public Bitmap getImg() {
        return img;
    }

    public int getcX() {
        return cX;
    }

    public int getcY() {
        return cY;
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

    public void setBrightIndex(int brightIndex) {
        BrightIndex = brightIndex;
    }

    public void setX(int x) {
        X = x;
        currectXY();
    }

    public void setY(int y) {
        Y = y;
        currectXY();
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    private void currectXY(){
        if(getShapeIndex()==1){
            cX = X + 40;
            cY = Y + 150;
        }
        else{
            cX = X + 60;
            cY = Y + 60;
        }
    }
}
