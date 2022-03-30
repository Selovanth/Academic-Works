package lab.pkg6;

public class LandTract {
    private double length, width;
    
    public LandTract(double length, double width){
        this.length=length;
        this.width=width;
    }
    public double tractArea(){
        double area = length * width;
        return area;
    }
    public boolean equals(LandTract object){
        if (this.length != object.length || this.width != object.width)
            return false;
        else
            return true;
    }
    public String toString(){
        String str;
        return str = String.format("The area of the entered land tract is: %.2f", this.tractArea());
    }
}