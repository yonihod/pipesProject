package algorithms;

import BFS.*;

public class SearcherAdpater<T> implements Searcher<T> {

	Searcher<T> searcher = null;

	public SearcherAdpater(Integer N) {
		searcher = new DFS<T>();
		//searcher = new BFS<T>();
	}

	@Override
	public Solution search(Searchable<T> searchable) {
		return searcher.search(searchable);
	}

}
