package com.icloud.front.stock.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.front.stock.baseaction.BaseStockController;

@Controller
@RequestMapping("/paper")
public class PaperController extends BaseStockController {

	@RequestMapping("/{id}")
	public ModelAndView readPaper(@PathVariable("id") String id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("paper/paper");
		return model;
	}

}
