package view;

public class Solution {

    private Location2D point;
    private int timesToRotate;

    public Solution(){}

    /**
     *
     * @param str format : (0,0,1)
     */
    public Solution(String str){
        String[] tempArray = str.split(",");
        this.point = new Location2D(Integer.valueOf(tempArray[0]),Integer.valueOf(tempArray[1]));
        timesToRotate = Integer.valueOf(tempArray[2]);
    }

    public Location2D getPoint() {
        return point;
    }

    public void setPoint(Location2D point) {
        this.point = point;
    }

    public int getTimesToRotate() {
        return timesToRotate;
    }

    public void setTimesToRotate(int timesToRotate) {
        this.timesToRotate = timesToRotate;
    }
}
