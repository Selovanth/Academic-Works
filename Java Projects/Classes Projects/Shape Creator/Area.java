package lab.pkg6;

 class Area {
     private double pi = Math.PI, rectArea, cylinderArea, circleArea;
     private int shapeType;
     
     public Area(double radius){
         this.circleArea = Math.pow(radius, 2) * pi;
         shapeType = 1;
     }
     public Area(double width, double length){
         this.rectArea = width * length;
         shapeType = 2;
     }
     public Area(double radius, int height){
         this.cylinderArea = Math.pow(radius, 2) * pi * height;
         shapeType = 3;
     }
     public String toString(){
         String str;
         switch (shapeType){
             case 1:
                 str = String.format("The created shape is a circle. Its area is: %.2f", circleArea);
                 break;
             case 2:
                 str = String.format("The created shape is a rectangle. Its area is: %.2f", rectArea);
                 break;
             case 3:
                 str = String.format("The created shape is a cylinder. Its volume is: %.2f", cylinderArea);
                 break;
             default:
                 str = "I was not able to create a shape from the provided measurements.\n\n";
         }
         return str;
     }
}
