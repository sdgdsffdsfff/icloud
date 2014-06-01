package com.icloud.stock.search;

import java.util.HashSet;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;

import com.icloud.search.autocomplete.index.AutoCompleteConstants;
import com.icloud.search.autocomplete.search.AutoCompleteSearchServer;
import com.icloud.search.autocomplete.search.BeanSortedQueue;
import com.icloud.search.util.LuceneIndexManager;
import com.icloud.search.util.SearchUtil;
import com.icloud.search.util.StringUtil;

public class TopsAutoCompleteSearchServer extends
		AutoCompleteSearchServer<AutoCompleteBean> {

	public TopsAutoCompleteSearchServer() {
		super();
		// TODO Auto-generated constructor stub
	}

	// singleton
	private static class SingletonHolder {
		static final TopsAutoCompleteSearchServer INSTANCE = new TopsAutoCompleteSearchServer();
		static {
			INSTANCE.init();
		}
	}

	public static TopsAutoCompleteSearchServer getInstance() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		analyzer = LuceneIndexManager.getInstance().getIKAnalyzer();
	}

	@Override
	protected AutoCompleteBean convertDocToSearchBean(Document doc, int docid,
			float score) {
		// TODO Auto-generated method stub
		if (doc == null)
			return null;
		String metaType = doc.get(AutoCompleteConstants.META_TYPE);
		if (metaType == null)
			return null;
		try {
			Integer.parseInt(metaType);
		} catch (Exception e) {
			return null;
		}

		PinyinBean pinyinBean = new PinyinBean();
		pinyinBean.setDocId(docid);
		pinyinBean.setStatWeight(score);
		pinyinBean.setMatch(false);
		pinyinBean.setMetaType(Integer.parseInt(doc
				.get(AutoCompleteConstants.META_TYPE)));
		pinyinBean.setIataCode(doc.get(AutoCompleteConstants.AUTOCOMPLETE_ID));
		pinyinBean.setPinyin(doc
				.get(AutoCompleteConstants.AUTOCOMPLETE_PINYIN_NAME));
		pinyinBean.setIndexName(doc
				.get(AutoCompleteConstants.AUTOCOMPLETE_READL_NAME));

		AutoCompleteBean autoCompleteBean = new AutoCompleteBean(pinyinBean,
				doc);
		return autoCompleteBean;
	}

	@Override
	protected BeanSortedQueue getAutoCompleteBeanSortedQueue(int limit) {
		// TODO Auto-generated method stub
		return new AutoCompleteBeanSortedQueue(limit);
	}

	@Override
	protected boolean hotQueueSort(BeanSortedQueue queue, List listPinyinBean,
			String searchWord, Boolean isEnglish) {
		// TODO Auto-generated method stub
		String searchName = searchWord.toLowerCase();
		// 判断searchName是否有连续的叠字（3个字）
		boolean flag = checkIsOverlap(searchName);
		boolean isMatched = false;
		int len = searchName.length();
		for (Object object : listPinyinBean) {
			AutoCompleteBean autoCompleteBean = (AutoCompleteBean) object;
			PinyinBean pb = autoCompleteBean.getBean();
			String code = null;
			if (autoCompleteBean.getDoc() != null) {
				code = autoCompleteBean.getDoc().get(
						AutoCompleteConstants.AUTOCOMPLETE_ID);
				if (code != null) {
					code = code.toLowerCase();
				}
			}
			if (pb.getIndexName() != null && pb.getPinyin() != null) {
				String realChinese = SearchUtil.getNoStopWord(pb.getIndexName()
						.toLowerCase());
				String chinese = SearchUtil.getNoStopWord(pb.getPinyin())
						.toLowerCase();
				double stime = pb.getStatWeight();
				int searchwordSize = Math.max(searchWord.length(),
						chinese.length()); // 与纠错词比较的最大值
				int realLd = StringUtil.LD(searchName, realChinese); // 检索词与真实纠错词的编辑距离
				int ld = StringUtil.LD(searchName, chinese); // 检索词与纠错词的编辑距离
				if (isEnglish) {
					stime += (1 - (float) ld / searchwordSize) * 10;
					if (realLd == 0) {
						// 有命中结果
						stime += 10.0f;
						pb.setMatch(true);
						isMatched = true;
					} else { // 有初始分数的功能,加2.0f,如果开始的话
						if (code != null && searchName != null) {
							if (code.startsWith(searchName)) {
								stime += 1.0f * searchName.length();
							}
						}
					}

					pb.setStatWeight(stime);
					queue.insertWithOverflow(autoCompleteBean);
				} else {
					stime += (1 - (float) ld / searchwordSize) * 10;
					if (realLd == 0) {
						// 有命中结果
						pb.setMatch(true);
						stime += 10.0f; // 命中加10分
					}
					// 对于一些ld可能过低，但是其互相包含的那种，情况，此对其进行了加分4f
					boolean isAddChineseScore = false;
					if (!isAddChineseScore) {
						int chineselen = chinese.length();
						if (len > 0 && chineselen > 0) {
							if (chinese.indexOf(searchName) != -1
									|| searchName.indexOf(chinese) != -1) {
								stime += 4.0f;
								isAddChineseScore = true;
							} else if (realChinese.indexOf(searchName) != -1
									|| searchName.indexOf(realChinese) != -1) {
								stime += 4.0f;
								isAddChineseScore = true;
							}
						}
					}
					if (!isAddChineseScore) {
						int chineselen = realChinese.length();
						if (len > 0 && chineselen > 0) {
							if (realChinese.indexOf(searchName) != -1
									|| searchName.indexOf(realChinese) != -1) {
								stime += 4.0f;
								isAddChineseScore = true;
							}
						}
					}

					// 对于连续有3个叠字的的情况，需要判断编辑距离和searchwordSize的关系
					// 普遍认为当ld>1的时候，都不满足条件了
					if (flag) {//
						if (ld > 2 && realLd > 0) {
							continue;
						}
					}

					pb.setStatWeight(stime);
					queue.insertWithOverflow(autoCompleteBean);
				}
			}
		}
		return isMatched;
	}

	@Override
	protected void addOtherQuery(BooleanQuery query, int type) {
		// TODO Auto-generated method stub
		BooleanQuery typeBooleanQuery = new BooleanQuery(true);
		typeBooleanQuery.add(new TermQuery(new Term(
				AutoCompleteConstants.META_TYPE, type + "")),
				BooleanClause.Occur.MUST);
		query.add(typeBooleanQuery, BooleanClause.Occur.MUST);
	}

	@Override
	protected boolean beanIsOverLop(HashSet set, AutoCompleteBean bean) {
		// TODO Auto-generated method stub
		if (set == null)
			return false;
		return !set.add(bean.getBean().getIataCode()
				+ bean.getBean().getMetaType());
	}

	@Override
	protected AutoCompleteBean[] createArray(int size) {
		// TODO Auto-generated method stub
		return new AutoCompleteBean[size];
	}
}
