package com.icloud.front.Paper.bussiness;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.stock.model.Paper;
import com.icloud.user.service.IPaperService;

@Service("paperBussiness")
public class PaperBussiness {
	@Resource(name = "paperService")
	protected IPaperService paperService;

	public Paper getPaper(int id) {
		return this.paperService.getById(id);
	}

	public void addPaper(Paper paper) {
		this.paperService.save(paper);
	}

	public Pagination<Paper> listPapers(int pageNo) {
		long count = this.paperService.count();
		int limit = 1;
		Pagination<Paper> pagination = Pagination.getInstance(pageNo, limit);
		pagination.setTotalItemCount(count);
		List<Paper> list = this.paperService.findMetaList(
				pagination.getStart(), pagination.getPageSize());
		pagination.setData(list);
		pagination.build();
		return pagination;
	}
}
