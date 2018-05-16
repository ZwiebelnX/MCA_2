package com.zwiebelnx.mca_2.Bean;


/**
 * 连线属性类
 * 在DrawItemView中作为绘图以及获取音高pitch使用
 */
public class Line {
    private int startItemIndex;
    private int EndItemIndex;

    public Line(int StartI, int EndI){
        startItemIndex = StartI;
        EndItemIndex = EndI;
    }

    public void setEndItemIndex(int endItemIndex) {
        EndItemIndex = endItemIndex;
    }

    public void setStartItemIndex(int startItemIndex) {
        this.startItemIndex = startItemIndex;
    }

    public int getStartItemIndex() {
        return startItemIndex;
    }

    public int getEndItemIndex() {
        return EndItemIndex;
    }
}
