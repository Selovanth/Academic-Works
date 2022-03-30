package lab.pkg6;

public class RoomCarpet {
    private double carpetCost, totalRoomCost;
    private RoomDimension userRoom;
    
    public RoomCarpet(RoomDimension dimensions, double carpetCost){
        this.carpetCost=carpetCost;
        userRoom = dimensions.copy();
        totalRoomCost = carpetCost * userRoom.getArea();
    }
    public String toString(){
        String str;
        return str = String.format("\nThe total cost to carpet the submitted room is: $%.2f", totalRoomCost);
    }
}
