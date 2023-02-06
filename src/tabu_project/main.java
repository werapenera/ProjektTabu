package tabu_project;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {

		ArrayList<String> filenames = new ArrayList<>(
				Arrays.asList("a05100", "a05200", "a10100", "a10200", "a20100", "a20200"));

		for (int i = 0; i < filenames.size(); i++) {

			System.out.println("--------{" + filenames.get(i) + "}--------");

			List<String> file_content = BufferedFileReader.readFile(filenames.get(i));
			List<List<String>> unparsed_data = BufferedFileReader.split_string_list(file_content);
			List<Double> parsed_data = BufferedFileReader.parse_string_list_of_double(unparsed_data);
			int ammount_of_lists = (int) Math.round(parsed_data.get(0));
			int ammount_of_elemnts_on_list = (int) Math.round(parsed_data.get(1));
			List<Double> pure_data = BufferedFileReader.cut_off_non_data_information(parsed_data);
			List<List<Double>> data = BufferedFileReader.cut_list_into_smaller_lists(pure_data,
					ammount_of_elemnts_on_list, ammount_of_lists);


			System.out.println("Ammount of  tasks  = " + ammount_of_elemnts_on_list);
			System.out.println("Ammount of workers = " + ammount_of_lists);
			System.out.println("---------------------------");
			
			//BASE CASE
			System.out.println("Ammount of time spend on tasks for initial combination");
			List<Integer> initial_list = TabuSearch.generate_first_combination(data);
			
			//FIRST MODIFICATION
			//System.out.println("Ammount of time spend on tasks for initial combination\nModification - we pick best worker for each task");
			//List<Integer> initial_list = TabuSearch.generate_first_combination_pick_best_worker_for_each_task(data);
	
		
			System.out.println(TabuSearch.calculate_total_ammount_of_time_spend_on_tasks(initial_list, data));
			
			System.out.println("---------------------------");
			System.out.println("Ammount of time spend on tasks after tabu search (10 trials)");
			for (int j = 0; j < 10; j++) {
				List<Integer> list_after_tabu_search = TabuSearch.tabu_search(initial_list, data);
				System.out.println(
						TabuSearch.calculate_total_ammount_of_time_spend_on_tasks(list_after_tabu_search, data));
			}
			System.out.println();

		}
	}

}
