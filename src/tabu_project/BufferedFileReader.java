package tabu_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BufferedFileReader {
	public static List<String> readFile() {
		List<String> fileContent = new ArrayList<String>();

		//try (BufferedReader br = new BufferedReader(new FileReader("assign500.txt"))) {
		try (BufferedReader br = new BufferedReader(new FileReader("assign100.txt"))) {
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				fileContent.add(sCurrentLine);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileContent;
	}
	public static List<String> parseFileContentLineIntoList(List<String> fileContent) {
		List<String> data = new ArrayList<>();
		for (int i = 1; i < fileContent.size(); i++) {
			List<String> oneLine = new ArrayList<>();
			oneLine = Arrays.asList(fileContent.get(i).trim().split(" "));
			data.addAll(oneLine);
		
		}
		return data;
	}
	
	public static List<List<Integer>> prepare_list_of_integers(List<String> list){
		List<List<Integer>> listOfLists = new ArrayList<>();
		List<Integer> acumulator = new ArrayList<>();
		
		
		for (int i = 0; i < list.size(); i++) {
			acumulator.add(Integer.parseInt( list.get(i)));
			//if(acumulator.size()>=500) {
			if(acumulator.size()>=100) {
				listOfLists.add(acumulator);
				acumulator = new ArrayList<>();
			}
		}
		
		return listOfLists;
	}
}
