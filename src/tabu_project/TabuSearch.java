package tabu_project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuSearch {

	public static List<Integer> tabu_search(List<Integer> list_of_worker_index, List<List<Integer>> workers_and_tasks) {
		int MAX_ITER = 1000000;
		int MAX_TABU_LENGTH = 100;
		List<Integer> worker_index_pair = new ArrayList<>();
		List<Integer> best_list_of_worker_index_list = new ArrayList<>();
		List<TabuElement> tabu_list = new ArrayList<TabuElement>();

		best_list_of_worker_index_list = new ArrayList<>(list_of_worker_index);
		for (int i = 0; i < MAX_ITER; i++) {
			worker_index_pair = generate_pair_for_swap(workers_and_tasks);
			int index_of_first_worker_task = -1;
			int index_of_second_worker_task = -1;
			
			for (int j = 0; j < list_of_worker_index.size(); j++) {
				if (list_of_worker_index.get(j).equals(worker_index_pair.get(0))) {
					index_of_first_worker_task = j;
				}
				if (list_of_worker_index.get(j).equals(worker_index_pair.get(1))) {
					index_of_second_worker_task= j;
				}
			}

			TabuElement element = new TabuElement(worker_index_pair.get(0), index_of_first_worker_task, worker_index_pair.get(0), index_of_second_worker_task);
			if (!Tabu.is_element_on_list(tabu_list, element)) {
				List<Integer> swaped_list_of_worker_index = swap_workers(list_of_worker_index, worker_index_pair);

				if (calcualte_total_time(swaped_list_of_worker_index,
						workers_and_tasks) < calcualte_total_time(list_of_worker_index, workers_and_tasks)) {
					best_list_of_worker_index_list = new ArrayList<>(swaped_list_of_worker_index);
					tabu_list = Tabu.add_element_to_tabu_list(tabu_list, element, MAX_TABU_LENGTH);
				}
			}
		}

		return best_list_of_worker_index_list;
	}

	public static List<Integer> swap_workers(List<Integer> list_of_worker_index, List<Integer> worker_pair) {
		List<Integer> swaped_worker_list = new ArrayList<Integer>(list_of_worker_index);

		swaped_worker_list.set(worker_pair.get(0), worker_pair.get(1));
		swaped_worker_list.set(worker_pair.get(1), worker_pair.get(0));
		return swaped_worker_list;
	}

	public static List<Integer> generate_pair_for_swap(List<List<Integer>> workers_and_tasks) {

		List<Integer> worker_index_pair = new ArrayList<>();
		Random random = new Random();

		int randomly_generated_first_worker_index = random.nextInt(workers_and_tasks.size());
		int randomly_generated_second_worker_index = random.nextInt(workers_and_tasks.size());

		while (randomly_generated_first_worker_index == randomly_generated_second_worker_index) {
			randomly_generated_second_worker_index = random.nextInt(workers_and_tasks.size());
		}

		// musimy sprawdzić czy jest na liście tabu
		worker_index_pair.add(randomly_generated_first_worker_index);
		worker_index_pair.add(randomly_generated_second_worker_index);
		return worker_index_pair;
	}

	public static int calcualte_total_time(List<Integer> list_of_worker_index, List<List<Integer>> workers_and_tasks) {
		int sum = 0;
		for (int i = 0; i < list_of_worker_index.size(); i++) {
			sum = sum + workers_and_tasks.get(list_of_worker_index.get(i)).get(i);
		}

		return sum;
	}
}
