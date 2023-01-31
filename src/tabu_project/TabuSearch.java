package tabu_project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuSearch {

	public static List<Integer> tabu_search(List<Integer> list_of_worker_index, List<List<Integer>> workers_and_tasks) {
		int MAX_ITER = 1000;
		List<Integer> worker_index_pair = new ArrayList<>();
		List<Integer> best_list_of_worker_index_list = new ArrayList<>();

		best_list_of_worker_index_list = new ArrayList<>(list_of_worker_index);
		for (int i = 0; i < MAX_ITER; i++) {
			worker_index_pair = generate_pair_for_swap(workers_and_tasks);
			List<Integer> swaped_list_of_worker_index = swap_workers(list_of_worker_index, worker_index_pair);
			if (calcualte_total_time(swaped_list_of_worker_index,
					workers_and_tasks) < calcualte_total_time(list_of_worker_index, workers_and_tasks)) {
				best_list_of_worker_index_list = new ArrayList<>(swaped_list_of_worker_index);
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
