package test;

import util.FileReaderForImageDataUtility;
import util.FileReaderForLabelDataUtility;

public class TestImplementation {

	public static void main(String[] args) {
		//code for calling perceptron
		runPerceptron();
		
		//code for calling Naive Bayes
		runNaiveBayesClassifier();
		
		//code for calling MIRA
		runMIRAClassifier();
		
	}
	
	public static void runPerceptron(){
		//code for digits
		long startTimeDigit = System.currentTimeMillis();
		int noOfIterations = 1;
		String fileName1 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\traininglabels";
		String fileName2 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\trainingimages";
		FileReaderForLabelDataUtility fileReader = new FileReaderForLabelDataUtility();
		FileReaderForImageDataUtility fileImageReader = new FileReaderForImageDataUtility(20, 30, 1, -1);
		fileImageReader.setTrainElementLimit(5000);
		for(int i=0; i< noOfIterations; i++){
			fileImageReader.readFromFile(fileName2, fileReader.getListOfLabelElements(fileName1), 1);
			fileReader.clearList();
			fileImageReader.reInitializeData();
		}
		
		String fileName3 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\testlabels";
		String fileName4 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\testimages";
		fileImageReader.readFromFile(fileName4, fileReader.getListOfLabelElements(fileName3), 2);
		long stopTimeDigit = System.currentTimeMillis();
		System.out.println("Time for executing Perceptron on digit data in milli seconds: " +((stopTimeDigit - startTimeDigit)/*/1000*/ ));
		//String fileName9 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\validationlabels";
		//String fileName10 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\validationimages";
		//fileImageReader.readFromFile(fileName10, fileReader.getListOfLabelElements(fileName9), 2);
		
		
		//code for face
		long startTimeFace = System.currentTimeMillis();
		String fileName5 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatrainlabels";
		String fileName6 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatrain";
		FileReaderForLabelDataUtility fileReader1= new FileReaderForLabelDataUtility();
		FileReaderForImageDataUtility fileImageReader1 = new FileReaderForImageDataUtility(68, 60, 1, -1);
		fileImageReader1.setTrainElementLimit(452);
		for(int i=0; i< noOfIterations; i++){
			fileImageReader1.readFromFile(fileName6, fileReader1.getListOfLabelElements(fileName5), 1);
			fileReader1.clearList();
			fileImageReader1.reInitializeData();
		}
		
		String fileName7 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatestlabels";
		String fileName8 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatest";
		fileImageReader1.readFromFile(fileName8, fileReader1.getListOfLabelElements(fileName7), 2);
		long stopTimeFace = System.currentTimeMillis();
		System.out.println("Time for executing Perceptron on face data in milli seconds: " +((stopTimeFace - startTimeFace)/*/1000*/ ));
		//String fileName11 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatavalidationlabels";
		//String fileName12 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatavalidation";
		//fileImageReader1.readFromFile(fileName12, fileReader1.getListOfLabelElements(fileName11), 2);
		
	}
	
	public static void runNaiveBayesClassifier(){
		//code for digits
		long startTimeDigit = System.currentTimeMillis();
		String fileName1 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\traininglabels";
		String fileName2 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\trainingimages";
		FileReaderForLabelDataUtility fileReader = new FileReaderForLabelDataUtility();
		FileReaderForImageDataUtility fileImageReader = new FileReaderForImageDataUtility(20, 30, 2, -1);
		fileImageReader.setTrainElementLimit(5000);
		fileImageReader.readFromFile(fileName2, fileReader.getListOfLabelElements(fileName1), 1);
		fileReader.clearList();
		fileImageReader.reInitializeData();
				
		String fileName3 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\testlabels";
		String fileName4 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\testimages";
		fileImageReader.readFromFile(fileName4, fileReader.getListOfLabelElements(fileName3), 2);
			//String fileName9 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\validationlabels";
			//String fileName10 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\validationimages";
			//fileImageReader.readFromFile(fileName10, fileReader.getListOfLabelElements(fileName9), 2);
		long stopTimeDigit = System.currentTimeMillis();
		System.out.println("Time for executing Naive Bayes Classifier on digit data in milli seconds: " +((stopTimeDigit - startTimeDigit)/*/1000*/ ));

		//code for face
		long startTimeFace = System.currentTimeMillis();
		String fileName5 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatrainlabels";
		String fileName6 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatrain";
		FileReaderForLabelDataUtility fileReader1= new FileReaderForLabelDataUtility();
		FileReaderForImageDataUtility fileImageReader1 = new FileReaderForImageDataUtility(68, 60, 2, -1);
		fileImageReader1.setTrainElementLimit(452);
		fileImageReader1.readFromFile(fileName6, fileReader1.getListOfLabelElements(fileName5), 1);
		fileReader1.clearList();
		fileImageReader1.reInitializeData();
		
		String fileName7 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatestlabels";
		String fileName8 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatest";
		fileImageReader1.readFromFile(fileName8, fileReader1.getListOfLabelElements(fileName7), 2);
		long stopTimeFace = System.currentTimeMillis();
		System.out.println("Time for executing Naive Bayes Classifier on face data in milli seconds: " +((stopTimeFace - startTimeFace)/*/1000*/ ));

			//String fileName11 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatavalidationlabels";
			//String fileName12 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatavalidation";
			//fileImageReader1.readFromFile(fileName12, fileReader1.getListOfLabelElements(fileName11), 2);
		
	}
	
	public static void runMIRAClassifier(){
		//code for digits
		int noOfIterations = 3;
		long startTimeDigit = System.currentTimeMillis();
		String fileName1 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\traininglabels";
		String fileName2 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\trainingimages";
		FileReaderForLabelDataUtility fileReader = new FileReaderForLabelDataUtility();
		FileReaderForImageDataUtility fileImageReader = new FileReaderForImageDataUtility(20, 30, 3,0.01);
		fileImageReader.setTrainElementLimit(5000);
		for(int i=0; i< noOfIterations; i++){
			fileImageReader.readFromFile(fileName2, fileReader.getListOfLabelElements(fileName1), 1);
			fileReader.clearList();
			fileImageReader.reInitializeData();
		}
				
		String fileName3 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\testlabels";
		String fileName4 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\testimages";
		fileImageReader.readFromFile(fileName4, fileReader.getListOfLabelElements(fileName3), 2);
		
		//String fileName9 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\validationlabels";
		//String fileName10 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\digitdata\\validationimages";
		//fileImageReader.readFromFile(fileName10, fileReader.getListOfLabelElements(fileName9), 2);
		long stopTimeDigit = System.currentTimeMillis();
		System.out.println("Time for executing MIRA classifier on digit data in milli seconds: " +((stopTimeDigit - startTimeDigit)/*/1000*/ ));

	
		//code for face
		long startTimeFace = System.currentTimeMillis();
		String fileName5 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatrainlabels";
		String fileName6 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatrain";
		FileReaderForLabelDataUtility fileReader1= new FileReaderForLabelDataUtility();
		FileReaderForImageDataUtility fileImageReader1 = new FileReaderForImageDataUtility(68, 60, 3,0.1);
		fileImageReader1.setTrainElementLimit(452);
		for(int i=0; i< noOfIterations; i++){
			fileImageReader1.readFromFile(fileName6, fileReader1.getListOfLabelElements(fileName5), 1);
			fileReader1.clearList();
			fileImageReader1.reInitializeData();
		}
		
		String fileName7 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatestlabels";
		String fileName8 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatatest";
		fileImageReader1.readFromFile(fileName8, fileReader1.getListOfLabelElements(fileName7), 2);
		
		//String fileName11 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatavalidationlabels";
		//String fileName12 = "C:\\Study Material\\Artificial Intelligence - 520\\Assignments\\Project\\data\\facedata\\facedatavalidation";
		//fileImageReader1.readFromFile(fileName12, fileReader1.getListOfLabelElements(fileName11), 2);
		long stopTimeFace = System.currentTimeMillis();
		System.out.println("Time for executing MIRA classifier on face data in milli seconds: " +((stopTimeFace - startTimeFace)/*/1000*/ ));

	}
}
