package com.icloud.leetcode.simplifyPath;

import java.util.ArrayList;
import java.util.List;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月27日 上午11:23:09
 */
public class SimplifyPath {
	public String simplifyPath(String path) {
		if (path.startsWith("/"))
			path = path.substring(1);

		String[] paths = path.split("/");
		List<String> list = new ArrayList<String>();
		// list.add("/");
		int index = 0;
		for (String p : paths) {
			if ("..".equalsIgnoreCase(p)) {
				if (index > 0) {
					list.remove(--index);
				}
			} else if (".".equalsIgnoreCase(p)) {
			} else if ("".equalsIgnoreCase(p)) {
			} else {
				// list.add(index++);
				list.add(p);
				index++;
			}
		}
		if (list.size() == 0)
			return "/";
		StringBuffer sb = new StringBuffer();
		for (String p : list) {
			sb.append("/" + p);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SimplifyPath path = new SimplifyPath();
		String s = "/a/./b/../../c/";
//		s = "/..";
		// /..
		String paths = path.simplifyPath(s);
		System.out.println(paths);
	}
}
