package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import classifier.MIRAClassifier;
import classifier.NaiveBayesClassifier;
import classifier.PerceptronClassifier;

public class FileReaderForImageDataUtility {

	private BufferedReader br = null;
	private int[][] elementMatrix = null;
	private int noOfElement = 0;
	private PerceptronClassifier percClassifier;
	private NaiveBayesClassifier nBClassifier;
	private MIRAClassifier mClassifier;
	private int matrixDimension1;
	private int matrixDimension2;
	private int typeOfClassifier;
	private int trainingElementLimit;
	
	public FileReaderForImageDataUtility(int matrixDimension1, int matrixDimension2, int typeOfClassifier, double cValue) {
		this.matrixDimension1 = matrixDimension1;
		this.matrixDimension2 = matrixDimension2;
		
		this.typeOfClassifier = typeOfClassifier;
		if(typeOfClassifier == 1){
			percClassifier = new PerceptronClassifier(matrixDimension1, matrixDimension2);
		}
		else if(typeOfClassifier == 2){
			nBClassifier = new NaiveBayesClassifier(matrixDimension1, matrixDimension2);
		}
		else if(typeOfClassifier == 3){
			mClassifier = new MIRAClassifier(matrixDimension1, matrixDimension2);
			mClassifier.setValueForC(cValue);
		}
	}

	public void readFromFile(String fileName, List<Integer> digitList, int dataType){
		try {
			
			String currentLine;
			int lineNo = 0;
			br = new BufferedReader(new FileReader(fileName));
			int noOfLinesRead = 0;
			boolean isFirstLineRead = false;
			while ((currentLine = br.readLine()) != null) {
				if((currentLine.contains("+")) || (currentLine.contains("#"))){
					isFirstLineRead = true;
				}
				if((isFirstLineRead) && (noOfLinesRead < matrixDimension1)){
					if(elementMatrix == null){
						noOfElement++;
						elementMatrix = new int[matrixDimension1][matrixDimension2];
					}
					createMatrixForEachPattern(currentLine, lineNo);	
					lineNo++;
					noOfLinesRead++;
				}
				if(noOfLinesRead >= matrixDimension1){
					if((dataType == 1) && (noOfElement<=trainingElementLimit)){
						extractFeaturesFromElements(digitList, dataType);
					}
					else if(dataType == 2){
						extractFeaturesFromElements(digitList, dataType);
					}
					isFirstLineRead = false;
					noOfLinesRead = 0;
					lineNo = 0;
					//displayMatrix();
					elementMatrix = null;
				}
			}
			if((dataType == 1) && (typeOfClassifier == 2)){
				double[] digitCount = countOfDigitOccurence(digitList);
				nBClassifier.calculatePixelProbabilitiesForEachDigit(digitCount);
				nBClassifier.calculateProbabilitiesOfEachDigit(digitCount, digitList.size());
			}
			if((dataType == 2)){
				if(typeOfClassifier == 1){
					percClassifier.calculateAccuracyOfClassifier(digitList.size());
				}
				else if(typeOfClassifier ==2){
					nBClassifier.calculateAccuracyOfClassifier(digitList.size());
				}
				else if(typeOfClassifier ==3){
					mClassifier.calculateAccuracyOfClassifier(digitList.size());
				}			
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void createMatrixForEachPattern(String currentLine, int lineNo){
		for(int i=0;i<currentLine.length();i++){
			if((currentLine.charAt(i) == '#') || (currentLine.charAt(i) == '+') ){
				elementMatrix[lineNo][i] = 1;
			}
			else{
				elementMatrix[lineNo][i] = 0;
			}
		}
	}
	
	
	
	public void extractFeaturesFromElements(List<Integer> digitList, int dataType){
		if(dataType == 1){
			if(typeOfClassifier == 1){
				percClassifier.trainClassifierForDigit(digitList.get(noOfElement - 1), elementMatrix);
			}
			else if(typeOfClassifier == 2){
				nBClassifier.trainClassifierForDigit(digitList.get(noOfElement - 1), elementMatrix);
			}
			else if(typeOfClassifier == 3){
				mClassifier.trainClassifierForDigit(digitList.get(noOfElement - 1), elementMatrix);
			}
		}
		else{
			if(typeOfClassifier == 1){
				percClassifier.testForDigit(digitList.get(noOfElement - 1), elementMatrix);
			}
			else if(typeOfClassifier == 2){
				nBClassifier.testForDigit(digitList.get(noOfElement - 1), elementMatrix);
			}
			else if(typeOfClassifier == 3){
				mClassifier.testForDigit(digitList.get(noOfElement - 1), elementMatrix);
			}
		}
	}
	
	public void reInitializeData(){
		noOfElement = 0;
		elementMatrix = null;
	}
	
	public double[] countOfDigitOccurence(List<Integer> digitList){
		double[] digitOcuurence = new double[10];
		for(Integer digit : digitList){
			digitOcuurence[digit]++;
		}
		return digitOcuurence;
	}
	
	public void setTrainElementLimit(int trainingElementLimit){
		this.trainingElementLimit = trainingElementLimit;
	}
}

