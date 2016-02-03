package classifier;

import java.util.HashMap;
import java.util.Map;

public class PerceptronClassifier {
	Map<Integer, int[][]> weightDigitMatrix = new HashMap<Integer, int[][]>();
	private float accuracyCounter = 0;
	private int matrixDimension1;
	private int matrixDimension2;
	
	public PerceptronClassifier(int matrixDimension1, int matrixDimension2) {
		this.matrixDimension1 = matrixDimension1;
		this.matrixDimension2 = matrixDimension2;
	}
	
	public void populateWeightMatrix(int digit, int[][] digitImageData){
		int[][] weightArray = new int[matrixDimension1][matrixDimension2];
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
				learnFromErrors(index, digit, digitImageData);
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
		int result = 0;
		int[] digitResults=new int[10];
		int[][] weightArrayForDigit;
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
		
		int max = -100000;
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
		int[][] weightArrayForSubtraction = weightDigitMatrix.get(computedResult);
		for(int i=0; i<digitImageData.length; i++){
			for(int j=0; j<digitImageData[i].length; j++){
				weightArrayForSubtraction[i][j]-= digitImageData[i][j];
			}
		}
		
		int[][] weightArrayForAddition = weightDigitMatrix.get(actualResult);
		for(int i=0; i<digitImageData.length; i++){
			for(int j=0; j<digitImageData[i].length; j++){
				weightArrayForAddition[i][j]+= digitImageData[i][j];
			}
		}
	}
	public void calculateAccuracyOfClassifier(float totalNoOfElements){
		float accuracyPerc = (accuracyCounter/totalNoOfElements)*100;
		System.out.println("Accuracy of Perceptron Classifier is "+accuracyPerc);
		accuracyCounter = 0;
	}
}
