package com.icloud.front.stock.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.stock.model.Paper;

@Controller
@RequestMapping("/paper")
public class PaperController extends BaseStockController {

	@RequestMapping("/reader/{id}")
	public ModelAndView readPaper(@PathVariable("id") int id) {
		Paper paper = this.paperBussiness.getPaper(id);
		ModelAndView model = new ModelAndView("paper/paper");
		if (ICloudUtils.isNotNull(paper)) {
			model.addObject("paper", paper);
		}
		return model;
	}

	@RequestMapping("/list/papers")
	// @PathVariable("pageNo")
	public ModelAndView listPapers(String pageNo) {
		Pagination<Paper> pagination = this.paperBussiness
				.listPapers(ICloudUtils.parseInt(pageNo, 0));
		ModelAndView model = new ModelAndView("paper/list-paper");
		if (ICloudUtils.isNotNull(pagination)) {
			PageView pageView = PageView.convertPage(pagination);
			model.addObject("pagination", pagination);
			model.addObject("pageView", pageView);
			pageView.print();
		}
		return model;
	}
}
