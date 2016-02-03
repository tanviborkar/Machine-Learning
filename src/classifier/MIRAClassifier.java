package classifier;

import java.util.HashMap;
import java.util.Map;

public class MIRAClassifier {
	Map<Integer, double[][]> weightDigitMatrix = new HashMap<Integer, double[][]>();
	private float accuracyCounter = 0;
	private int matrixDimension1;
	private int matrixDimension2;
	private double c=0.1;
	
	public MIRAClassifier(int matrixDimension1, int matrixDimension2) {
		this.matrixDimension1 = matrixDimension1;
		this.matrixDimension2 = matrixDimension2;
	}
	
	public void populateWeightMatrix(int digit, int[][] digitImageData){
		double[][] weightArray = new double[matrixDimension1][matrixDimension2];
		for(int j=0; j<weightArray.length; j++){
			for(int k=0;k<weightArray[j].length; k++){
				weightArray[j][k] = digitImageData[j][k];
			}
		}
		weightDigitMatrix.put(digit, weightArray);
	}
	
	public void trainClassifierForDigit(int digit, int[][] digitImageData){
		if(!weightDigitMatrix.containsKey(digit)){
			populateWeightMatrix(digit, digitImageData);
		}else{
			int index = predictDigitAndLearn(digitImageData);
			if(index != digit){
				if((weightDigitMatrix.containsKey(index)) && (weightDigitMatrix.containsKey(digit))){
					learnFromErrors(index, digit, digitImageData);
				}
			}
		}
	}
	
	public void testForDigit(int digit, int[][] digitTestImageData){
		int index = predictDigitAndLearn(digitTestImageData);
		if(index == digit){
			accuracyCounter++;
		}
	}
	
	public int predictDigitAndLearn(int[][] digitImageData){
		double result = 0;
		double[] digitResults=new double[10];
		double[][] weightArrayForDigit;
		for(int i=0;i<digitResults.length;i++){
			if(weightDigitMatrix.containsKey(i)){
				weightArrayForDigit = weightDigitMatrix.get(i);
				for(int j=0;j<digitImageData.length;j++){
					for(int k=0;k<digitImageData[j].length;k++)
					{
						result = result + (weightArrayForDigit[j][k] * digitImageData[j][k]);
					}
				}
				digitResults[i] = result;
				result = 0;	
			}
		}
		
		double max = -100000.0;
		int index = -10000;
		for(int i=0;i<digitResults.length;i++)
		{
			if(weightDigitMatrix.containsKey(i)){
				if(digitResults[i]>max)
				{	
						max = digitResults[i];
						index = i;	
				}
			}
		}
		
		return index;
	}
	
	public void learnFromErrors(int computedResult, int actualResult, int[][] digitImageData){
		double[][] weightArrayIncorrect = weightDigitMatrix.get(computedResult);
		double[][] weightArrayCorrect = weightDigitMatrix.get(actualResult);
		double tao = 0.0;
		for(int i=0;i< digitImageData.length; i++){
			for(int j=0;j<digitImageData[i].length; j++){
				tao = calculateMultiplyingFactor(weightArrayIncorrect[i][j], weightArrayCorrect[i][j], digitImageData[i][j]);
				weightArrayIncorrect[i][j]-= tao * digitImageData[i][j];
				weightArrayCorrect[i][j]+= tao * digitImageData[i][j];
			}
		}
	}
	
	public double calculateMultiplyingFactor(double weightArrayIncorrect, double weightArrayCorrect, int digitImageData){
		double tao = (((weightArrayIncorrect - weightArrayCorrect) * digitImageData) + 1)/(digitImageData *digitImageData *2);
		if(c<tao){
			return c;
		}
		else{
			return tao;
		}
	}
	public void calculateAccuracyOfClassifier(float totalNoOfElements){
		float accuracyPerc = (accuracyCounter/totalNoOfElements)*100;
		System.out.println("Accuracy of MIRA Classifier is "+accuracyPerc);
		accuracyCounter = 0;
	}
	
	public void setValueForC(double c){
		this.c = c;
	}
}
