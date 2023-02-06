package tabu_project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuSearch {

	public static List<Integer> generate_first_combination(List<List<Double>> data) {
		List<Integer> initial_combination = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < data.get(0).size(); i++) {
			initial_combination.add(random.nextInt(data.size()));
		}

		return initial_combination;
	}

	public static List<Integer> generate_first_combination_pick_best_worker_for_each_task(List<List<Double>> data) {
		List<Integer> initial_combination = new ArrayList<>();
		for (int i = 0; i < data.get(0).size(); i++) {
			int best_worker_index=0;
			for (int j = 1; j < data.size(); j++) {
				if (data.get(j).get(i)<data.get(best_worker_index).get(i)) {
				best_worker_index=j;	
				}
			}
			initial_combination.add(best_worker_index);
		}

		return initial_combination;
	}
	
	
	
	public static List<Integer> tabu_search(List<Integer> initial_list, List<List<Double>> data) {
		List<Integer> final_assignment = new ArrayList<>(initial_list);
		List<TabuElement> tabu_list = new ArrayList<TabuElement>();
		int MAX_ITER = 1000000;
		int MAX_TABU_LENGTH = 100;

		for (int i = 0; i < MAX_ITER; i++) {

			List<Integer> assignment_worker_pair = generate_assignment_worker_pair(data, final_assignment);
			TabuElement element = new TabuElement(initial_list.get(assignment_worker_pair.get(0)),
					assignment_worker_pair.get(0), assignment_worker_pair.get(1));

			if (!Tabu.is_element_on_list(tabu_list, element)) {
				List<Integer> new_assignement = change_assignment(final_assignment, assignment_worker_pair);
				if (calculate_total_ammount_of_time_spend_on_tasks(new_assignement,
						data) < calculate_total_ammount_of_time_spend_on_tasks(final_assignment, data)) {

					final_assignment = new ArrayList<>(new_assignement);
					tabu_list = Tabu.add_element_to_tabu_list(tabu_list, element, MAX_TABU_LENGTH);
				}
			}
		}

		return final_assignment;
	}

	
	public static List<Integer> tabu_search_with_aspiration(List<Integer> initial_list, List<List<Double>> data) {
		List<Integer> final_assignment = new ArrayList<>(initial_list);
		List<TabuElement> tabu_list = new ArrayList<TabuElement>();
		int MAX_ITER = 1000000;
		int MAX_TABU_LENGTH = 100;

		for (int i = 0; i < MAX_ITER; i++) {

			List<Integer> assignment_worker_pair = generate_assignment_worker_pair(data, final_assignment);
			TabuElement element = new TabuElement(initial_list.get(assignment_worker_pair.get(0)),
					assignment_worker_pair.get(0), assignment_worker_pair.get(1));

			if (!Tabu.is_element_on_list(tabu_list, element)) {
				List<Integer> new_assignement = change_assignment(final_assignment, assignment_worker_pair);
				double new_assignment_time_spend = calculate_total_ammount_of_time_spend_on_tasks(new_assignement, data);
				double final_assignment_time_spend = calculate_total_ammount_of_time_spend_on_tasks(final_assignment, data);
				
				// Kryterium aspiracji
				if (new_assignment_time_spend < final_assignment_time_spend 
					|| (new_assignment_time_spend == final_assignment_time_spend && 
					Tabu.is_element_on_list(tabu_list, element))) {
					
					final_assignment = new ArrayList<>(new_assignement);
					tabu_list = Tabu.add_element_to_tabu_list(tabu_list, element, MAX_TABU_LENGTH);
				}
			}
		}

		return final_assignment;
	}
	
	
	
	private static List<Integer> generate_assignment_worker_pair(List<List<Double>> data, List<Integer> initial_list) {
		Random random = new Random();
		List<Integer> assignment_worker_pair = new ArrayList<>();

		int temp_assignment = random.nextInt(data.get(0).size());
		int temp_worker = random.nextInt(data.size());

		while (initial_list.get(temp_assignment) == temp_worker) {
			temp_assignment = random.nextInt(data.get(0).size());
			temp_worker = random.nextInt(data.size());
		}
		assignment_worker_pair.add(temp_assignment);
		assignment_worker_pair.add(temp_worker);

		return assignment_worker_pair;
	}

	public static Double calculate_total_ammount_of_time_spend_on_tasks(List<Integer> list_of_indexes,
			List<List<Double>> data) {
		double old_sum = 0;
		for (int i = 0; i < data.size(); i++) {
			double new_sum = 0;
			for (int j = 0; j < data.get(0).get(j); j++) {
				if (list_of_indexes.get(j) == i) {
					new_sum = new_sum + data.get(i).get(j);
				}
			}
			if (new_sum > old_sum) {
				old_sum = new_sum;
			}
		}
		return old_sum;
	}

	public static List<Integer> change_assignment(List<Integer> assignment, List<Integer> assignment_worker_pair) {
		List<Integer> changed_assignment = new ArrayList<Integer>(assignment);

		changed_assignment.set(assignment_worker_pair.get(0), assignment_worker_pair.get(1));

		return changed_assignment;
	}
//	public static List<Integer> tabu_search(List<Integer> list_of_worker_index, List<List<Integer>> workers_and_tasks) {
//		int MAX_ITER = 1000000;
//		int MAX_TABU_LENGTH = 100;
//		List<Integer> worker_index_pair = new ArrayList<>();
//		List<Integer> best_list_of_worker_index_list = new ArrayList<>();
//		List<TabuElement> tabu_list = new ArrayList<TabuElement>();
//
//		best_list_of_worker_index_list = new ArrayList<>(list_of_worker_index);
//		for (int i = 0; i < MAX_ITER; i++) {
//			worker_index_pair = generate_pair_for_swap(workers_and_tasks);
//			int index_of_first_worker_task = -1;
//			int index_of_second_worker_task = -1;
//			
//			for (int j = 0; j < list_of_worker_index.size(); j++) {
//				if (list_of_worker_index.get(j).equals(worker_index_pair.get(0))) {
//					index_of_first_worker_task = j;
//				}
//				if (list_of_worker_index.get(j).equals(worker_index_pair.get(1))) {
//					index_of_second_worker_task= j;
//				}
//			}
//
//			TabuElement element = new TabuElement(worker_index_pair.get(0), index_of_first_worker_task, worker_index_pair.get(0), index_of_second_worker_task);
//			if (!Tabu.is_element_on_list(tabu_list, element)) {
//				List<Integer> swaped_list_of_worker_index = swap_workers(list_of_worker_index, worker_index_pair);
//
//				if (calcualte_total_time(swaped_list_of_worker_index,
//						workers_and_tasks) < calcualte_total_time(list_of_worker_index, workers_and_tasks)) {
//					best_list_of_worker_index_list = new ArrayList<>(swaped_list_of_worker_index);
//					tabu_list = Tabu.add_element_to_tabu_list(tabu_list, element, MAX_TABU_LENGTH);
//				}
//			}
//		}
//
//		return best_list_of_worker_index_list;
//	}
//	
//	
//	public static List<Integer> tabu_search_aspiracja(List<Integer> list_of_worker_index, List<List<Integer>> workers_and_tasks) {
//				int MAX_ITER = 1000000;
//				int MAX_TABU_LENGTH = 100;
//				int ASPIRATION_CRITERIA = 50;
//				List<Integer> worker_index_pair = new ArrayList<>();
//				List<Integer> best_list_of_worker_index_list = new ArrayList<>();
//				List<TabuElement> tabu_list = new ArrayList<TabuElement>();
//				best_list_of_worker_index_list = new ArrayList<>(list_of_worker_index);
//		for (int i = 0; i < MAX_ITER; i++) {
//			worker_index_pair = generate_pair_for_swap(workers_and_tasks);
//			int index_of_first_worker_task = -1;
//			int index_of_second_worker_task = -1;
//			
//			for (int j = 0; j < list_of_worker_index.size(); j++) {
//				if (list_of_worker_index.get(j).equals(worker_index_pair.get(0))) {
//					index_of_first_worker_task = j;
//				}
//				if (list_of_worker_index.get(j).equals(worker_index_pair.get(1))) {
//					index_of_second_worker_task= j;
//				}
//			}
//		
//			TabuElement element = new TabuElement(worker_index_pair.get(0), index_of_first_worker_task, worker_index_pair.get(0), index_of_second_worker_task);
//			if (!Tabu.is_element_on_list(tabu_list, element) || (calcualte_total_time(best_list_of_worker_index_list, workers_and_tasks) - calcualte_total_time(list_of_worker_index, workers_and_tasks) > ASPIRATION_CRITERIA)) {
//				List<Integer> swaped_list_of_worker_index = swap_workers(list_of_worker_index, worker_index_pair);
//		
//				if (calcualte_total_time(swaped_list_of_worker_index,
//						workers_and_tasks) < calcualte_total_time(list_of_worker_index, workers_and_tasks)) {
//					best_list_of_worker_index_list = new ArrayList<>(swaped_list_of_worker_index);
//					tabu_list = Tabu.add_element_to_tabu_list(tabu_list, element, MAX_TABU_LENGTH);
//				}
//			}
//		}
//		
//		return best_list_of_worker_index_list;
//			
//	}
//
//	
//
//
//	public static List<Integer> generate_pair_for_swap(List<List<Integer>> workers_and_tasks) {
//
//		List<Integer> worker_index_pair = new ArrayList<>();
//		Random random = new Random();
//
//		int randomly_generated_first_worker_index = random.nextInt(workers_and_tasks.size());
//		int randomly_generated_second_worker_index = random.nextInt(workers_and_tasks.size());
//
//		while (randomly_generated_first_worker_index == randomly_generated_second_worker_index) {
//			randomly_generated_second_worker_index = random.nextInt(workers_and_tasks.size());
//		}
//
//		// musimy sprawdzić czy jest na liście tabu
//		worker_index_pair.add(randomly_generated_first_worker_index);
//		worker_index_pair.add(randomly_generated_second_worker_index);
//		return worker_index_pair;
//	}
//
//	public static int calcualte_total_time(List<Integer> list_of_worker_index, List<List<Integer>> workers_and_tasks) {
//		int sum = 0;
//		for (int i = 0; i < list_of_worker_index.size(); i++) {
//			sum = sum + workers_and_tasks.get(list_of_worker_index.get(i)).get(i);
//		}
//
//		return sum;
//	}
}
