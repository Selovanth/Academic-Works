package lab.pkg10;

public class TestScores {
    private double testScoresIn[];
    
    
    public TestScores(double [] testScoresIn){
        this.testScoresIn = new double[testScoresIn.length];        
        
        for(int element = 0; element < this.testScoresIn.length; element++){
            if(testScoresIn[element] < 0)
                throw new IllegalArgumentException("\n-------------------INVALID TEST SCORES ENTERED--------------------\n");
            else
                this.testScoresIn[element] = testScoresIn[element];
        }   
    }
    
    public double testScoresAverage(){
        double averages = 0;
        for(int element = 0; element < this.testScoresIn.length; element++){
            averages += testScoresIn[element];
        }
        averages = averages / testScoresIn.length;
        
        return averages;
    }
}
