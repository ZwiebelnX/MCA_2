package Bean;

public class Line {
    private int startItemIndex;
    private int EndItemIndex;
    private float StartX, StartY, EndX, EndY;

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
