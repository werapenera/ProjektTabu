package tabu_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BufferedFileReader {
	public static List<String> readFile(String filename) {
		List<String> fileContent = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				fileContent.add(sCurrentLine);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileContent;
	}

	public static List<List<String>> split_string_list(List<String> string_list) {
		List<List<String>> list_of_lists = new ArrayList<>();
		for (int i = 0; i < string_list.size(); i++) {
			String unsplited_elements = string_list.get(i);
			List<String> splited_elements = new ArrayList<>();
			splited_elements = Arrays.asList(unsplited_elements.split(" "));
			list_of_lists.add(splited_elements);
		}
		return list_of_lists;
	}

	public static List<Double> parse_string_list_of_double(List<List<String>> old_list) {
		List<Double> parsed_elements = new ArrayList<>();

		for (int i = 0; i < old_list.size(); i++) {
			for (int j = 0; j < old_list.get(i).size(); j++) {
				if (old_list.get(i).get(j).equals(" ")||old_list.get(i).get(j).equals("")) {
					}else {
					double num = Double.parseDouble(old_list.get(i).get(j));
					parsed_elements.add(num);
				}
			}
		}
		return parsed_elements;
	}

	public static List<Double> cut_off_non_data_information(List<Double> old_list) {
		List<Double> elements = new ArrayList<>();

		for (int i = 2; i < old_list.size(); i++) {
			elements.add(old_list.get(i));
		}
		return elements;
	}

	public static List<List<Double>> cut_list_into_smaller_lists(List<Double> old_list,
			int ammount_of_elements_in_one_list, int ammount_of_lists) {
		List<List<Double>> list_of_lists = new ArrayList<>();
		int current_index = 0;

		for (int i = 0; i < ammount_of_lists; i++) {
			List<Double> small_list = new ArrayList<>();

			for (int j = 0; j < ammount_of_elements_in_one_list; j++) {
				small_list.add(old_list.get(current_index));
				current_index++;
			}
			list_of_lists.add(small_list);

		}
		return list_of_lists;
	}

	public static void print_list_of_lists(List<List<Double>> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j) + " | ");
			}
			System.out.println();
		}
	}
	public static void print_list(List<Integer> list) {
			for (int j = 0; j < list.size(); j++) {
				System.out.print(list.get(j) + " | ");
			}
			System.out.println();
	}
}