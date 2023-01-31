package tabu_project;

import java.util.ArrayList;
import java.util.List;

public class Tabu {
	public static boolean elements_are_identical(TabuElement first_element, TabuElement second_element) {
		if (first_element.index_worker1 == second_element.index_worker1
				&& first_element.index_worker2 == second_element.index_worker2
				&& first_element.index_task1 == second_element.index_task1
				&& first_element.index_task2 == second_element.index_task2) {
			return true;
		}
		return false;
	}

	public static List<TabuElement> add_element_to_tabu_list(List<TabuElement> tabu_list, TabuElement element,
			int max_list_length) {

		List<TabuElement> new_list = new ArrayList<TabuElement>(tabu_list);
		if (new_list.size() >= max_list_length) {
			new_list.remove(0);
		}
		new_list.add(element);
		return new_list;
	}

	public static boolean is_element_on_list(List<TabuElement> tabu_list, TabuElement element) {
		for (int i = 0; i < tabu_list.size(); i++) {
			if (Tabu.elements_are_identical(tabu_list.get(i), element)) {
				return true;
			}
		}
		return false;
	}
}
