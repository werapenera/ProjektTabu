package tabu_project;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {
		List<String> fileContent = BufferedFileReader.readFile();
		List<String> rawData = BufferedFileReader.parseFileContentLineIntoList(fileContent);
		List<List<Integer>> data = BufferedFileReader.prepare_list_of_integers(rawData);

		//System.out.println(data.size());
		//System.out.println(data.get(0).size());

		// pierwszy jest indeks pracownika a drugi index zadania
		// pionowo pracownik
		// poziomo jest zadanie

		//List<Integer> first_worker_index_combination = generate_first_combination(data);
		// print_list_of_numbers(first_worker_index_combination);

		//System.out.println(calcualte_total_cost(first_worker_index_combination, data));
		List<Integer> randomly_generated_worker_list = assign_randomly_withought_repetitions(data);
		System.out.println(TabuSearch.calcualte_total_time(randomly_generated_worker_list, data));
		System.out.println("");
		
		print_list_of_numbers(randomly_generated_worker_list);
		System.out.println("");
		System.out.println(randomly_generated_worker_list.size());
		
		List<Integer> result = TabuSearch.tabu_search(randomly_generated_worker_list, data);
		System.out.println(TabuSearch.calcualte_total_time(result, data));
		
		List<Integer> result2 = TabuSearch.tabu_search(randomly_generated_worker_list, data);
		System.out.println(TabuSearch.calcualte_total_time(result2, data));
	}
	

	public static List<Integer> assign_randomly_withought_repetitions(List<List<Integer>> data) {
		List<Integer> list_of_worker_index = new ArrayList<>();

		for (int task_index = 0; task_index < data.get(0).size(); task_index++) {

			Random random = new Random();

			int randomly_generated_worker_index = random.nextInt(data.size());

			while (is_on_worker_list(randomly_generated_worker_index, list_of_worker_index)) {
				randomly_generated_worker_index = random.nextInt(data.size());
			}
			list_of_worker_index.add(randomly_generated_worker_index);
		}
		return list_of_worker_index;
	}

	public static List<Integer> generate_first_combination(List<List<Integer>> data) {
		List<Integer> list_of_worker_index = new ArrayList<>();

		for (int task_index = 0; task_index < data.get(0).size(); task_index++) {
			int cost_of_performing_the_task = 9000000;
			int most_eficient_worker_index = -1;

			for (int worker_index = 0; worker_index < data.size(); worker_index++) {
				int current_cost_performing_task = data.get(worker_index).get(task_index);

				if ((current_cost_performing_task < cost_of_performing_the_task)
						&& (!is_on_worker_list(worker_index, list_of_worker_index))) {
					most_eficient_worker_index = worker_index;
					cost_of_performing_the_task = current_cost_performing_task;
				}

			}
			list_of_worker_index.add(most_eficient_worker_index);
		}
		return list_of_worker_index;
	}

	public static boolean is_on_worker_list(int worker_index, List<Integer> list_of_worker_index) {
		return list_of_worker_index.contains(worker_index);
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
