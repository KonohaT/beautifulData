package handwritten;

import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
    
	optdigits od = new optdigits(0.001);
    Frontend f = new Frontend();
    f.setRate(0.001);

	int[][] traindata = optdigits.readFile("beautifulData/Project/optdigits.train");
	int[][] testdata = optdigits.readFile("beautifulData/Project/optdigits.test");

	// train the perceptrons
	int[][] ep = od.train(traindata);


	//This prints out the number of epochs/tries it took to ajust the weights
	/*
	 * for(int i=0;i<ep.length;i++)
	{
		for(int j=0;j<ep[0].length;j++)
		{
			System.out.print(ep[i][j]);
		}
		System.out.println();
	}
	 * 
	 * 
	 */

	//Converts ep values to a single 1D Array
	int[] epoch = new int[45];
	int k = 0;
	for(int i=0; i<10; i++) {
	    for(int j=i+1; j<10; j++) {
		epoch[k++] = ep[i][j];
	    }
	}

	// calculate the accuracy for the training data
	int[][] confusion = od.test(traindata);
	double[] trainacc = new double[confusion.length];
	for(int i=0; i<confusion.length; i++) {
	    trainacc[i] = (confusion[i][0] + confusion[i][3]) * 1.0 / (confusion[i][0]+confusion[i][1]+confusion[i][2]+confusion[i][3]);
	}

	// calculate the accuracy for the test data
	confusion = od.test(testdata);
	double[] testacc = new double[confusion.length];
	for(int i=0; i<confusion.length; i++) {
	    testacc[i] = (confusion[i][0] + confusion[i][3]) * 1.0 / (confusion[i][0]+confusion[i][1]+confusion[i][2]+confusion[i][3]);
	}

	// generate report
	String[] label = new String[45];
	k = 0;
	for(int i=0; i<10; i++) {
	    for(int j=i+1; j<10; j++) {
		label[k++] = new String("("+i+","+j+"):");
	    }
	}
	DecimalFormat df = new DecimalFormat("#.##");
	for(int i=0; i<confusion.length; i++) {
	    System.out.println(label[i]+" Epoch: "+epoch[i]+" Training Accuracy:"+df.format(trainacc[i])+" Testing Accuracy "+df.format(testacc[i])+" True Positive"+confusion[i][0]+" False Positive"+confusion[i][1]+" True Negative"+confusion[i][2]+" False Negative"+confusion[i][3]);
	}
    }

}
    

