package tabu_project;

public class TabuElement {
	int index_worker_before;
	int index_task;
	int index_worker_after;

	public TabuElement(int index_worker_before, int index_task, int index_worker_after) {
		this.index_worker_before = index_worker_before;
		this.index_task= index_task;
		this.index_worker_after = index_worker_after;
	}
}
