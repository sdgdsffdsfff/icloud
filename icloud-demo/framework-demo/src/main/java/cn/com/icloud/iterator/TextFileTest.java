package cn.com.icloud.iterator;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import com.icloud.search.autocomplete.index.AutoCompleteConstants;

public class TextFileTest {
	public static void main(String[] args) {
		TextFile textFile = new TextFile(
				"/home/jiangningcui/workspace/tz/tz-data/test/run.sh");
		// while (iterator.hasNext()) {
		// System.out.println(iterator.next());
		// }
		for (String line : textFile) {
			System.out.println(line);
		}

	}

	protected Document getAutoCompleteDocument(String search_name, String id,
			String pinyin, String simplePinyin, float sc2) {
		Document doc = new Document();
		Field search_field = new Field("name1", search_name, Store.YES,
				Index.ANALYZED);
		Field pinyin_field = new Field("name2", pinyin, Store.YES,
				Index.ANALYZED);
		Field simple_pinyin_field = new Field("name3", simplePinyin, Store.YES,
				Index.ANALYZED);
		Field address_field = new Field("name4", id, Store.YES, Index.NO);
		doc.add(search_field);
		doc.add(address_field);
		doc.add(pinyin_field);
		doc.add(simple_pinyin_field);
		return doc;
	}
}
