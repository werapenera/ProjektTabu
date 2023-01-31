package tabu_project;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {
		List<String> fileContent = BufferedFileReader.readFile();
		List<String> rawData = BufferedFileReader.parseFileContentLineIntoList(fileContent);
		List<List<Integer>> data = BufferedFileReader.prepare_list_of_integers(rawData);
		
		
		List<Integer> first_combination = generate_first_combination(data);
		//i pracownik
		//j zadanie
		
		// 
		
		
	}
	public static List<Integer> generate_first_combination(List<List<Integer>> data) {
		List<Integer> list_of_worker_index = new ArrayList<>();

		for (int task_index = 0; task_index < data.get(0).size(); task_index++) {
			int cost_of_performing_the_task = 9000000;
			int most_eficient_worker_index = -1;

			for (int worker_index = 0; worker_index < data.size(); worker_index++) {
				int current_cost_performing_task = data.get(worker_index).get(task_index);


			}
			list_of_worker_index.add(most_eficient_worker_index);
		}
		return list_of_worker_index;
	}
	
	public static void print_list_of_numbers(List<Integer> list_of_numbers) {
		for (int i = 0; i < list_of_numbers.size(); i++) {
			if (i % 10 == 0) {
				System.out.println();
			}
			System.out.print(list_of_numbers.get(i).toString() + "|");
		}
	}

}
