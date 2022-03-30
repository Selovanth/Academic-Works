package lab.pkg6;

public class RoomDimension {
    private double length, width, roomArea;
    
    public RoomDimension(double length, double width){
        this.length = length;
        this.width = width;
        roomArea = width * length;
    }
    public RoomDimension copy(){
        RoomDimension copyRoom = new RoomDimension(length, width);
        return copyRoom;
    }
    public double getArea(){
        return roomArea;
    }
    public String toString(){
        String str;
        return str = String.format(" has an area of: %.2f", roomArea);
    }
}
