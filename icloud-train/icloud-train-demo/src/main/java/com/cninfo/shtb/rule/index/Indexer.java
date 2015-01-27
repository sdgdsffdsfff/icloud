package com.cninfo.shtb.rule.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.FSDirectory;

import com.cninfo.shtb.rule.model.Age;
import com.cninfo.shtb.rule.model.MemberRule;
import com.cninfo.shtb.rule.model.Money;
import com.cninfo.shtb.rule.model.PARule;
import com.google.gson.Gson;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.search.util.LuceneIndexManager;

public class Indexer {
	public static final String indexPath = "/data/test/index";
	public Gson gson = new Gson();

	public void index() throws IOException {
		FSDirectory directory = FSDirectory.open(new File(indexPath));
		Analyzer analyzer = LuceneIndexManager.getInstance().getIKAnalyzer();
		IndexWriter writer = new IndexWriter(directory, analyzer, true,
				MaxFieldLength.LIMITED);
		writer.setUseCompoundFile(false);

		List<PARule> list = getList();

		for (PARule rule : list) {
			Document doc = generate(rule);
			writer.addDocument(doc);
		}
		writer.close();
	}

	private Document generate(PARule rule) {
		Document doc = new Document();
		// doc.add(new Field());
		MemberRule beibao = rule.getBeibao();
		MemberRule toubao = rule.getToubao();
		Field field = getField("docName", rule.getName(), Field.Store.YES,
				Index.NOT_ANALYZED, 1.0f);
		doc.add(field);
		List<Field> fields = generateFiles("beibao", beibao);
		List<Field> fields2 = generateFiles("toubao", toubao);
		for (Field f : fields)
			doc.add(f);
		for (Field f : fields2)
			doc.add(f);
		return doc;
	}

	private List<Field> generateFiles(String filename, MemberRule beibao) {
		List<Field> list = new ArrayList<Field>();
		Money money = beibao.getMoney();
		Field f2 = getField(filename + "_money", gson.toJson(money),
				Field.Store.YES, Index.NO, 1.0f);
		list.add(f2);

		if (ICloudUtils.isNotNull(beibao)) {
			Age age = beibao.getAge();
			if (age != null) {
				int startAge = age.getStartAge();
				int endAge = age.getEndAge();
				if (!age.isStartBoolean()) {
					startAge++;
				}
				if (!age.isEndBoolean()) {
					endAge--;
				}
				for (; startAge <= endAge; startAge++) {
					Field field = getField(filename + "_age", startAge + "",
							Field.Store.YES, Index.NOT_ANALYZED, 1.0f);
					list.add(field);
				}
			}
		} else {
			int startAge = 0;
			int endAge = 100;
			for (; startAge <= endAge; startAge++) {
				Field field = getField(filename + "_age", startAge + "",
						Field.Store.YES, Index.NOT_ANALYZED, 1.0f);
				list.add(field);
			}
		}
		return list;
	}

	protected Field getField(String fieldName, String fieldValue,
			Field.Store store, Index index, float boost) {
		if (fieldName != null && fieldValue != null) {
			Field field = new Field(fieldName, fieldValue, store, index);
			field.setBoost(boost);
			return field;
		}
		return null;
	}

	private List<PARule> getList() {
		List<PARule> list = new ArrayList<PARule>();
		list.add(PARule.getPARule1());
		list.add(PARule.getPARule2());
		return list;
	}

	public static void main(String[] args) throws IOException {
		Indexer indexer = new Indexer();
		indexer.index();
	}
}
