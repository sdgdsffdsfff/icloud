package com.icloud.user.service;

import java.util.List;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.stock.model.Paper;

public interface IPaperService extends ISqlBaseService<Paper> {
	public List<Paper> findMetaList(int start, int limit);
}
