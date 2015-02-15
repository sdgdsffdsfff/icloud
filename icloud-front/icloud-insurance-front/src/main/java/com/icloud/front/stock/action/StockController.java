package com.icloud.front.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.BaseStockMenu;
import com.icloud.front.stock.pojo.StockBean;
import com.icloud.front.stock.pojo.StockCompleteResult;
import com.icloud.front.stock.pojo.StockDateHistoryResult;
import com.icloud.front.stock.pojo.StockMenuBean;
import com.icloud.stock.model.Category;
import com.icloud.stock.model.Stock;
import com.icloud.stock.model.StockDateHistory;
import com.icloud.stock.model.StockDetail;
import com.icloud.stock.model.constant.StockEnum.BaseCategory;
import com.icloud.stock.vo.StockVO;

@Controller
@RequestMapping("/stock")
public class StockController extends BaseStockController {
	protected List<BaseStockMenu> addMainMenus(ModelAndView model) {
		List<BaseStockMenu> baseMenus = stockCommonBussiness.getBaseMenu();
		model.addObject("mainMenus", baseMenus);
		return baseMenus;
	}

	@RequestMapping("/stockMenu")
	public ModelAndView stockMenu() {
		ModelAndView model = new ModelAndView("stock/mainPage");
		addMainMenus(model);
		return model;
	}

	// @ResponseBody
	// @RequestParam(required=true) String hotelId
	@RequestMapping("/openMenus")
	public ModelAndView openMenus() {
		ModelAndView model = new ModelAndView("opendata/open-menus");
		/**
		 * 获得所有数据
		 */
		List<BaseStockMenu> menus = addMainMenus(model);
		List<StockMenuBean> stockMenuBeans = new ArrayList<StockMenuBean>();
		/**
		 * 获得所有分类数据
		 */
		for (BaseStockMenu menu : menus) {
			StockMenuBean bean = stockCommonBussiness
					.getSingleStockMenuBean(menu.getCode());
			if (ICloudUtils.isNotNull(bean)) {
				stockMenuBeans.add(bean);
			}
		}
		model.addObject("stockMenuBeans", stockMenuBeans);
		return model;
	}

	@RequestMapping("/openStockList")
	public ModelAndView openStockList(
			@RequestParam(required = true) String cateId, String pageNo) {
		ModelAndView model = new ModelAndView("opendata/open-stock-list");
		addMainMenus(model);

		BaseStockMenu baseStockMenu = this.stockCommonBussiness
				.getBaseStockMenu(cateId);

		if (ICloudUtils.isNotNull(baseStockMenu)) {
			Category category = stockCommonBussiness
					.getCategoryFromCateId(cateId);
			if (ICloudUtils.isNotNull(category)) {
				StockMenuBean bean = stockCommonBussiness
						.getSingleStockMenuBean(category
								.getCategoryCategoryType());
				model.addObject("stockMenuBean", bean);
			}
		}
		Pagination<Stock> pagination = this.stockListBussiness.getStockList(
				cateId, pageNo, 30);
		PageView pageView = PageView.convertPage(pagination);
		model.addObject("baseStockMenu", baseStockMenu);
		model.addObject("pagination", pagination);
		model.addObject("pageView", pageView);
		model.addObject("cateId", cateId);
		return model;
	}

	@RequestMapping("/listStockView")
	public ModelAndView stockListMenu(
			@RequestParam(required = true) String cateId, String pageNo) {
		ModelAndView model = new ModelAndView("stock/stock-list-view");
		BaseStockMenu baseStockMenu = this.stockCommonBussiness
				.getBaseStockMenu(cateId);
		Pagination<Stock> pagination = this.stockListBussiness.getStockList(
				cateId, pageNo, 30);
		PageView pageView = PageView.convertPage(pagination);
		model.addObject("pagination", pagination);
		model.addObject("pageView", pageView);
		model.addObject("baseStockMenu", baseStockMenu);
		model.addObject("cateId", cateId);
		return model;
	}

	@RequestMapping("/getStockMenu")
	public ModelAndView getStockMenu(String id) {
		ModelAndView model = new ModelAndView("stock/menus");
		if (id == null)
			id = BaseCategory.BASE.getType();
		List<StockMenuBean> menuList = stockCommonBussiness
				.getStockMenuBean(id);
		model.addObject("menuList", menuList);
		return model;
	}

	@RequestMapping("/stockBaseDetail")
	public ModelAndView getStockDetail(String stockCode, String type) {
		ModelAndView model = new ModelAndView("stock/stock-base-detail");
		if (ICloudUtils.isNotNull(stockCode)) {
			Stock stock = this.stockDetailBussiness
					.getStockByStockCode(stockCode);
			/**
			 * if stock 为空的时候,做其他的处理
			 */
			if (!ICloudUtils.isNotNull(stock)) {
				int index = stockCode.indexOf("(");
				if (index != -1) {
					stockCode = stockCode.substring(0, index);
				}
				/**
				 * 进行搜索
				 */
				List<StockBean> list = this.stockNameSearcher.search(stockCode,
						10);
				if (!ICloudUtils.isEmpty(list)) {
					StockBean bean = list.get(0);
					stockCode = bean.getStockCode();
					stock = this.stockDetailBussiness
							.getStockByStockCode(stockCode);
				}
			}

			StockDetail detail = this.stockDetailBussiness
					.getStockDetailByStockCode(stockCode);
			if (ICloudUtils.isNotNull(stock)) {
				model.addObject("stock", stock);
				this.buuyuuSeoBussiness.setSeoInStockDetail(stock, model);
			}
			if (ICloudUtils.isNotNull(detail)) {
				model.addObject("stockDetail", detail);
			}
		}
		return model;
	}

	@RequestMapping("/stockBaseHistory")
	public ModelAndView getStockDetailHistory(String stockCode, String pageNo) {
		ModelAndView model = new ModelAndView("stock/stock-base-history");
		if (ICloudUtils.isNotNull(stockCode)) {
			Stock stock = this.stockDetailBussiness
					.getStockByStockCode(stockCode);
			if (ICloudUtils.isNotNull(stock)) {
				model.addObject("stock", stock);
				this.buuyuuSeoBussiness.setSeoInStockDetail(stock, model);

				/**
				 * 获得历史
				 */
				Pagination<StockDateHistory> pagination = this.stockDetailBussiness
						.getStockDateHistoryList(stock.getId(), pageNo, 30);
				PageView pageView = PageView.convertPage(pagination);
				model.addObject("pagination", pagination);
				model.addObject("pageView", pageView);
			}
		}
		return model;
	}

	@RequestMapping("/stockSearch")
	@ResponseBody
	public String stockSearch(@RequestParam(required = true) String keyword) {
		List<StockBean> list = this.stockNameSearcher.search(keyword, 10);

		return gson.toJson(StockCompleteResult
				.convertToStockCompleteResult(list));
	}

	@RequestMapping("/getDateHistory")
	@ResponseBody
	public String getDateHistory(@RequestParam(required = true) String stockCode) {
		Stock stock = this.stockDetailBussiness.getStockByStockCode(stockCode);
		if (!ICloudUtils.isNotNull(stock)) {
			stock = this.stockDetailBussiness.getRadomStock();
		}
		List<StockDateHistory> list = this.stockDetailBussiness
				.getStockDateHistoryList(stock.getId(), 0, 100);
		StockDateHistoryResult result = StockDateHistoryResult
				.convertToStockDateHistoryResult(stock.getStockName(), list);
		return gson.toJson(result);
	}
}
