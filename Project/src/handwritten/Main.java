package handwritten;

public class Main {

    public static void main(String[] args) {
    
	optdigits od = new optdigits(0.001);
    Frontend f = new Frontend();
    f.setRate(0.5);

	int[][] traindata = optdigits.readFile("beautifulData/Project/optdigits.test");
	int[][] testdata = optdigits.readFile("beautifulData/Project/optdigits.train");

	// train the perceptrons
	int[][] ep = od.train(traindata);

	//Converts ep values to a single 1D Array
	int[] epoch = new int[45];
	int k = 0;
	for(int i=0; i<10; i++) {
	    for(int j=i+1; j<10; j++) {
		epoch[k++] = ep[i][j];
	    }
	}

	 //od.dispWeight();
     //System.out.println("Rate:"+f.getRate());
     //System.out.println();
     //System.out.println("Weight:");
     //f.getWeight();

	 // calculate the accuracy for the training data

}
    
}
