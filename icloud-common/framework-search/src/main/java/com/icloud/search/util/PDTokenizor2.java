package com.icloud.search.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class PDTokenizor2 {

	public static List<String> getTokens(Analyzer analyzer, String query)
			throws IOException {
		if (analyzer == null)
			return null;
		if (query == null) {
			return null;
		}
		TokenStream ts = (TokenStream) analyzer.tokenStream("query",
				new StringReader(query));
		int start = 0;
		List<String> set = new ArrayList<String>();
		while (ts.incrementToken()) {
			TermAttribute termAtt = ts.getAttribute(TermAttribute.class);
			OffsetAttribute offset = ts.getAttribute(OffsetAttribute.class);
			String term = termAtt.term();
			// if (Format.getNumberOfChinese(term) > 1) {
			int s = offset.startOffset();
			if (s >= start) {
				if (!set.contains(term)) {
					String is = SearchUtil.getStopWords(term);
					if (is != null && is.equalsIgnoreCase(term)) {

					} else {
						set.add(term);
					}
				}
			}
			// }
			termAtt = null;
			offset = null;
		}
		try {
			ts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ts = null;
		return set;
	}

	public static String tokenStream(Analyzer analyzer, String query)
			throws IOException {
		if (analyzer == null)
			return null;
		if (query == null) {
			return null;
		}
		TokenStream ts = (TokenStream) analyzer.tokenStream("query",
				new StringReader(query));
		StringBuffer sb = new StringBuffer();
		while (ts.incrementToken()) {
			TermAttribute termAtt = ts.getAttribute(TermAttribute.class);
			sb.append(termAtt.term() + " ");
			termAtt = null;
		}
		ts.close();
		ts = null;
		return sb.toString();
	}

	public static String preTokenStream(Analyzer analyzer, String query)
			throws IOException {
		if (analyzer == null)
			return null;
		if (query == null) {
			return null;
		}
		TokenStream ts = analyzer.tokenStream("query", new StringReader(query));
		while (ts.incrementToken()) {
			TermAttribute termAtt = ts.getAttribute(TermAttribute.class);
			String term = termAtt.term();
			if (term.length() > 1)
				return term;
		}
		return query;
	}

	public static void main(String args[]) {
		String query = "永久网址 www.6080.cn 性感美女 内衣模特 性感车模 网络美女 清纯美女 动漫图片";
		IKAnalyzer paoding = new IKAnalyzer();
		try {
			List<String> set = getTokens(paoding, query);
			if (set != null) {
				for (String str : set) {
					System.out.println(str);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
