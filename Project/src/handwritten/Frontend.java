package handwritten;

public class Frontend {

    private double rate;
    public static double[][][] weight=new double[10][10][65]; 

    public void setRate(double rate){
        this.rate=rate;
    }

    public double getRate(){
        return rate;
    }

    public void getWeight(){
        for(int i=0; i<10; i++) {
            for(int j=i+1; j<10; j++) {
            System.out.println(i+","+j+":");
            System.out.println();
            for(int k=0; k<65; k++) {
                System.out.print(weight[i][j][k]+",");
            }
            System.out.println();
            }
        }
    }
}

    
    

