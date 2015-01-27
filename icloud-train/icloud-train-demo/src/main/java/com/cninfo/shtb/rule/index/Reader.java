package com.cninfo.shtb.rule.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RuleCollector;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.cninfo.shtb.rule.QueryMemberModel;
import com.cninfo.shtb.rule.model.Money;
import com.google.gson.Gson;

public class Reader {
	private Cache cache = new Cache();
	public Gson gson = new Gson();

	public void init(IndexSearcher searcher) throws CorruptIndexException,
			IOException {
		cache.clear();
		int maxDoc = searcher.maxDoc();
		for (int i = 0; i < maxDoc; i++) {
			Document doc = searcher.doc(i);
			putCache(i, "beibao", doc);
			putCache(i, "toubao", doc);
		}
	}

	private void putCache(int docId, String name, Document doc) {
		String value = doc.get(name + "_money");
		Money money = gson.fromJson(value, Money.class);
		cache.put(name + "_" + docId, money);
	}

	public void search() throws IOException {
		Directory directory = FSDirectory.open(new File(Indexer.indexPath));
		IndexSearcher searcher = new IndexSearcher(IndexReader.open(directory,
				false));
		init(searcher);

		QueryMemberModel queryModel = QueryMemberModel.getQueryModel(11, 23, 11,
				6);
		Query query = generateQuery(queryModel);
		RuleCollector ruleCollector = new RuleCollector(10, this.cache,
				queryModel);
		searcher.search(query, ruleCollector);
		System.out.println(ruleCollector.getTotalHits());
		ScoreDoc[] sdoc = ruleCollector.topDocs().scoreDocs;
		for (ScoreDoc sd : sdoc) {
			Document doc = searcher.doc(sd.doc);
			System.out.println(doc.get("docName"));
		}
	}

	private Query generateQuery(QueryMemberModel queryModel) {
		BooleanQuery query = new BooleanQuery(true);
		query.add(new TermQuery(new Term("toubao_age", queryModel.getToubao()
				.getAge() + "")), Occur.MUST);
		query.add(new TermQuery(new Term("beibao_age", queryModel.getBeibao()
				.getAge() + "")), Occur.MUST);
		return query;
	}

	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();
		reader.search();
	}
}
