package pack;

import java.util.ArrayList;
import java.util.Comparator;

public class FileSorter implements Comparator<FilePack> {
	private ArrayList<String> SORTED_LIST = new ArrayList<String>();
	
	public FileSorter(String word1, String word2, String word3) {
		SORTED_LIST.add(word1);
		SORTED_LIST.add(word2);
		SORTED_LIST.add(word3);
	}
	public FileSorter(String word1, String word2, String word3,String word4) {
		SORTED_LIST.add(word1);
		SORTED_LIST.add(word2);
		SORTED_LIST.add(word3);
		SORTED_LIST.add(word4);

	}
	public FileSorter(String word1, String word2, String word3,String word4, String word5) {
		SORTED_LIST.add(word1);
		SORTED_LIST.add(word2);
		SORTED_LIST.add(word3);
		SORTED_LIST.add(word4);
		SORTED_LIST.add(word5);
	}
	
	@Override
	public int compare(FilePack o1, FilePack o2) {
		if (SORTED_LIST.contains(o1.getSuffix())&&SORTED_LIST.contains(o2.getSuffix())) {
			return SORTED_LIST.indexOf(o1.getSuffix()) - SORTED_LIST.indexOf(o2.getSuffix());
		}
		if (SORTED_LIST.contains(o1.getSuffix())) {
			return -1;
		}
		if (SORTED_LIST.contains(o2.getSuffix())) {
			return 1;
		}
		
		return o1.getSuffix().toString().compareTo(o2.getSuffix().toString());
	}
}
