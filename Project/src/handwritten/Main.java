package handwritten;

public class Main {

    public static void main(String[] args) {
    
	optdigits od = new optdigits(0.5);
    Frontend f = new Frontend();
    f.setRate(0.5);

	int[][] traindata = optdigits.readFile("beautifulData/Project/optdigits.test");
	int[][] testdata = optdigits.readFile("beautifulData/Optical-Recognition-of-Handwritten-Digits-Dataset-in-Java/optdigits.train");

	// train the perceptrons
	int[][] ep = od.train(traindata);
	int[] epoch = new int[45];
	int k = 0;
	for(int i=0; i<10; i++) {
	    for(int j=i+1; j<10; j++) {
		epoch[k++] = ep[i][j];
	    }
	}
    f.setWeight(od.getWeight());

	 //od.dispWeight();
     System.out.println("Rate:"+f.getRate());
     System.out.println();
     System.out.println("Weight:");
     f.getWeight();

}
    
}
