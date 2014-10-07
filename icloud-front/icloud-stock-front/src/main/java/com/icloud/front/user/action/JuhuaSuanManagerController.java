package com.icloud.front.user.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.JuhuasuanSearchBean;
import com.icloud.front.stock.pojo.JuhuasuanUrlBean;
import com.icloud.stock.model.JuhuasuanUrl;

@Controller
@RequestMapping("/usertb")
public class JuhuaSuanManagerController extends BaseStockController {

	@RequestMapping("/tbList")
	public ModelAndView tbList(JuhuasuanSearchBean searchBean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/tblist");
		JuhuasuanUrl urlBean = JuhuasuanSearchBean.convert(searchBean);
		if (!ICloudUtils.isNotNull(urlBean)) {
			urlBean = new JuhuasuanUrl();
		}
		urlBean.setUserId(this.getUserId());
		Pagination<JuhuasuanUrl> pagination = this.juhuasuanBussiness
				.searchJuhuasuanUrl(urlBean, searchBean.getPageNo(),
						searchBean.getLimit());
		PageView pageView = PageView.convertPage(pagination);

		modelAndView.addObject("pagination", pagination);
		modelAndView.addObject("pageView", pageView);
		modelAndView.addObject("urlBean", urlBean);
		return modelAndView;
	}

	@RequestMapping("/addJuhuasuanUrlView")
	public ModelAndView addJuhuasuanUrlView() {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/addJuhuasuanUrlView");
		return modelAndView;
	}

	@RequestMapping("/doAddJuhuasuanUrl")
	public ModelAndView doAddJuhuasuanUrl(JuhuasuanUrlBean bean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/juhuasuanUrlView");
		if (ICloudUtils.isNotNull(bean)) {
			JuhuasuanUrl urlBean = JuhuasuanUrlBean
					.convertJuhuasuanUrlBean(bean);
			urlBean.setUserId(this.getUserId());
			urlBean = this.juhuasuanBussiness.saveJuhuasuanUrl(urlBean);
			modelAndView.addObject("tip", "添加成功");
			modelAndView.addObject("urlBean", urlBean);
		}
		return modelAndView;
	}

	private void addmodelAndViewByCode(String code, String key,
			ModelAndView modelAndView) {
		if (ICloudUtils.isNotNull(code)) {
			JuhuasuanUrl urlBean = this.juhuasuanBussiness
					.getJuhuasuanUrlByCode(code);
			modelAndView.addObject(key, urlBean);
		}
	}

	@RequestMapping("/juhuasuanUrlView")
	public ModelAndView juhuasuanUrlView(String code) {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/juhuasuanUrlView");
		addmodelAndViewByCode(code, "urlBean", modelAndView);
		return modelAndView;
	}

	@RequestMapping("/modifyJuhusuanUrlView")
	public ModelAndView modifyJuhusuanView(String code) {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/modifyJuhuasuanUrlView");
		addmodelAndViewByCode(code, "urlBean", modelAndView);
		return modelAndView;
	}

	@RequestMapping("/doModifyJuhusuanUrl")
	public ModelAndView doModifyJuhusuan(JuhuasuanUrlBean bean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/operation/juhuasuanUrlView");
		if (ICloudUtils.isNotNull(bean)) {
			JuhuasuanUrl urlBean = JuhuasuanUrlBean
					.convertJuhuasuanUrlBean(bean);
			JuhuasuanUrl originJuhuasuanUrl = juhuasuanBussiness
					.getJuhuasuanUrlById(urlBean.getId());
			if (ICloudUtils.isNotNull(originJuhuasuanUrl)
					&& originJuhuasuanUrl.getUserId() == this.getUserId()) {
				urlBean.setUserId(this.getUserId());
				originJuhuasuanUrl = this.juhuasuanBussiness
						.updateJuhuasuanUrl(originJuhuasuanUrl, urlBean);
			}
			modelAndView.addObject("tip", "链接修改成功");
			modelAndView.addObject("urlBean", originJuhuasuanUrl);
		}
		return modelAndView;
	}

	
}
