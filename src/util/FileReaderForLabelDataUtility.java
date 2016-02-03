package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderForLabelDataUtility {

	private BufferedReader br = null;
	private List<Integer> digitList = null;

	public void readFromFile(String fileName){
		try {
			String currentLine;
			br = new BufferedReader(new FileReader(fileName));
	
			while ((currentLine = br.readLine()) != null) {
				if((!currentLine.equals("")) && (currentLine.matches("[0-9]+"))){
					if(digitList == null){
						digitList = new ArrayList<Integer>();
					}
					digitList.add(Integer.parseInt(currentLine));
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
	
	public void displayFileElements(){
		for(Integer digit : digitList){
			System.out.println(digit);
		}
	}

	public List<Integer> getListOfLabelElements(String fileName){
		readFromFile(fileName);
		return digitList;
	}
	
	public void clearList(){
		digitList.clear();
	}
}

