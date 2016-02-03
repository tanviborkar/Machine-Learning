package classifier;

import java.util.HashMap;
import java.util.Map;

public class NaiveBayesClassifier {
	private float accuracyCounter = 0;
	private int matrixDimension1;
	private int matrixDimension2;
	private double[] digitProbability = new double[10];
	Map<Integer, double[][]> probabilitiesOfOne = new HashMap<Integer, double[][]>();
	Map<Integer, double[][]> probabilitiesOfZero = new HashMap<Integer, double[][]>();
	
	
	public NaiveBayesClassifier(int matrixDimension1, int matrixDimension2) {
		this.matrixDimension1 = matrixDimension1;
		this.matrixDimension2 = matrixDimension2;
	}
	
	public void populateWeightMatrix(){
		for(int i=0;i<10;i++){
			double[][] probOne = new double[matrixDimension1][matrixDimension2];
			double[][] probZero = new double[matrixDimension1][matrixDimension2];

			probabilitiesOfOne.put(i, probOne);
			probabilitiesOfZero.put(i, probZero);
		}
	}
	
	public void trainClassifierForDigit(int digit, int[][] digitImageData){
		if(probabilitiesOfOne.isEmpty()){
			populateWeightMatrix();
		}
		double[][] probOne = probabilitiesOfOne.get(digit);
		double[][] probZero = probabilitiesOfZero.get(digit);
		for(int i=0;i<digitImageData.length;i++){
			for(int j=0;j<digitImageData[i].length;j++){
				if(digitImageData[i][j] == 1){
					probOne[i][j]++;
				}
				else{
					probZero[i][j]++;
				}
			}
		}
	}
	
	public void calculatePixelProbabilitiesForEachDigit(double[] digitCount){
		double smoothFactor = 3.1;
		for(int i=0; i<10; i++){
			double[][] probOne = probabilitiesOfOne.get(i);
			double[][] probZero = probabilitiesOfZero.get(i);
			for(int j=0;j<probOne.length;j++){
				for(int k=0;k<probOne[j].length;k++){
					probOne[j][k] = (probOne[j][k] + smoothFactor)/(digitCount[i] + smoothFactor);
				}
			}
			for(int j=0;j<probZero.length;j++){
				for(int k=0;k<probZero[j].length;k++){
					probZero[j][k] = (probZero[j][k] + smoothFactor)/(digitCount[i] + smoothFactor);
				}
			}
		}
	}
	
	public void calculateProbabilitiesOfEachDigit(double[] digitCount, int totalNoOfElements){
		for(int i=0; i<10; i++){
			digitProbability[i] = (digitCount[i]/totalNoOfElements);
		}
	}
	
	public void testForDigit(int digit, int[][] digitTestImageData){
		double[] digitProbabilityResult = new double[10];
		double featureProbability = 0;
		for(int i=0;i<digitProbabilityResult.length;i++){
			double[][] featureProbOne = probabilitiesOfOne.get(i);
			double[][] featureProbZero = probabilitiesOfZero.get(i);
			for(int j=0;j<digitTestImageData.length;j++){
				for(int k=0;k<digitTestImageData[j].length;k++){
					if(digitTestImageData[j][k] == 1){
						featureProbability+= Math.log(featureProbOne[j][k]);
					}
					else{
						featureProbability+= Math.log(featureProbZero[j][k]);					
					}
				}
			}
			digitProbabilityResult[i] = Math.log(digitProbability[i]) + featureProbability;
			featureProbability = 0;
		}
		
		double max = digitProbabilityResult[0];
		int index = 0;
		for(int i=1;i<digitProbabilityResult.length;i++)
		{
			if(digitProbabilityResult[i] > max)
			{	max = digitProbabilityResult[i];
				index = i;			
			}
		}
		
		if(index == digit){
			accuracyCounter++;
		}
	}
	
	public void calculateAccuracyOfClassifier(float totalNoOfElements){
		float accuracyPerc = (accuracyCounter/totalNoOfElements)*100;
		System.out.println("Accuracy of Naive Bayes Classifier is "+accuracyPerc);
		accuracyCounter = 0;
	}
}
