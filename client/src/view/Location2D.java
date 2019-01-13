package view;

public class Location2D {
    private int i,j;

    public Location2D(int i,int j){
        this.i = i;
        this.j =j;
    }
    public Location2D(){
        this.i = 0;
        this.j =0;
    }

    public void set(int i,int j){
        this.i = i;
        this.j = j;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {

        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
