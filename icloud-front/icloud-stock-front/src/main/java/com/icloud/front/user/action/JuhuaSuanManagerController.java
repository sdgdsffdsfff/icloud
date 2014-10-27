package com.icloud.front.user.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ExcelIEUtil;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.vo.KeyValue;
import com.icloud.front.juhusuan.pojo.JuhuasuanFrontSession;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.JuhuasuanSearchBean;
import com.icloud.front.stock.pojo.JuhuasuanUrlBean;
import com.icloud.front.stock.pojo.UploadFileRequest;
import com.icloud.stock.model.JuhuasuanDetail;
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

	@RequestMapping("trafficCurrentDay")
	public ModelAndView trafficCurrentDay(JuhuasuanSearchBean searhBean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/trafficCurrentDayView");
		if (!ICloudUtils.isNotNull(searhBean)) {
			searhBean = new JuhuasuanSearchBean();
		}
		long currentDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInCurrentDay(this.getUserId());
		long lastDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInLastDay(this.getUserId());
		long totalCount = this.juhuasuanBussiness.getCountOfJuhusuanDetail(
				this.getUserId(), null, null);
		Pagination<JuhuasuanDetail> pagination = this.juhuasuanBussiness
				.getDurrentDayJuhuasuanDetailByUserId(this.getUserId(),
						searhBean.getPageNo(), searhBean.getLimit());
		modelAndView.addObject("pagination", pagination);
		PageView pageView = PageView.convertPage(pagination);
		modelAndView.addObject("pageView", pageView);
		modelAndView.addObject("currentDayCount", currentDayCount);
		modelAndView.addObject("lastDayCount", lastDayCount);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("url_name", "当天访问量");
		return modelAndView;
	}

	@RequestMapping("traffic30Day")
	public ModelAndView traffic30Days(JuhuasuanSearchBean searhBean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/traffic30DayView");
		if (!ICloudUtils.isNotNull(searhBean)) {
			searhBean = new JuhuasuanSearchBean();
		}
		long currentDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInCurrentDay(this.getUserId());
		long lastDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInLastDay(this.getUserId());
		long totalCount = this.juhuasuanBussiness.getCountOfJuhusuanDetail(
				this.getUserId(), null, null);
		Pagination<JuhuasuanDetail> pagination = this.juhuasuanBussiness
				.get30DaysJuhuasuanDetailByUserId(this.getUserId(),
						searhBean.getPageNo(), searhBean.getLimit());
		modelAndView.addObject("pagination", pagination);
		PageView pageView = PageView.convertPage(pagination);
		modelAndView.addObject("pageView", pageView);
		modelAndView.addObject("currentDayCount", currentDayCount);
		modelAndView.addObject("lastDayCount", lastDayCount);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("url_name", "当月访问量");
		return modelAndView;
	}

	@RequestMapping("allUrlStatistics")
	public ModelAndView allUrlStatistics(JuhuasuanSearchBean searhBean) {
		// searhBean.setLimit(4);
		ModelAndView modelAndView = getModelAndView("user/taobao/all-url-statistics-view");
		if (!ICloudUtils.isNotNull(searhBean)) {
			searhBean = new JuhuasuanSearchBean();
		}
		long currentDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInCurrentDay(this.getUserId());
		long lastDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInLastDay(this.getUserId());
		long totalCount = this.juhuasuanBussiness.getCountOfJuhusuanDetail(
				this.getUserId(), null, null);

		Pagination<JuhuasuanFrontSession> pagination = this.juhuasuanBussiness
				.getJuhuaSessionByUserId(this.getUserId(),
						searhBean.getPageNo(), searhBean.getLimit());
		modelAndView.addObject("pagination", pagination);
		PageView pageView = PageView.convertPage(pagination);
		modelAndView.addObject("pageView", pageView);
		modelAndView.addObject("currentDayCount", currentDayCount);
		modelAndView.addObject("lastDayCount", lastDayCount);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("url_name", "总体统计");
		return modelAndView;
	}

	@RequestMapping("trafficUserView")
	public ModelAndView trafficUserView(JuhuasuanSearchBean searhBean) {
		// searhBean.setLimit(4);
		ModelAndView modelAndView = getModelAndView("user/taobao/trafficUserView");
		return modelAndView;
	}

	@RequestMapping("downloadMyUrls")
	public void downloadMyUrls(HttpServletResponse response) throws IOException {
		JuhuasuanUrl urlBean = new JuhuasuanUrl();
		urlBean.setUserId(this.getUserId());
		List<JuhuasuanUrl> urls = this.juhuasuanBussiness
				.searchAllJuhuasuanUrl(urlBean);
		logger.info("----------start to 生成xls----------");
		List<KeyValue<String, String>> list = new ArrayList<KeyValue<String, String>>();
		list.add(new KeyValue<String, String>("icloudUrl", "本地代码"));
		list.add(new KeyValue<String, String>("name", "链接名"));
		list.add(new KeyValue<String, String>("taobaoUrl", "淘宝url"));
		list.add(new KeyValue<String, String>("desText", "描述"));
		list.add(new KeyValue<String, String>("originUrl", "原始链接"));

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename=taobao_"
				+ this.getUserId() + ".xls");
		ExcelIEUtil.exportBytes(os, list, urls);
		logger.info("----------end to 生成xls----------");
	}

	@RequestMapping("uploadUrlView")
	public ModelAndView uploadUrlView(HttpServletResponse response)
			throws IOException {
		ModelAndView modelAndView = getModelAndView("user/taobao/upload-url-view");
		return modelAndView;
	}

	@RequestMapping(value = "/uploadXls", method = RequestMethod.POST)
	@ResponseBody
	public String uploadXls(UploadFileRequest request) throws Exception {
		logger.debug(request.getName());
		logger.debug("uerId is {}", this.getUserId());
		MultipartFile file = request.getFiledata();
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			logger.error("上传文件出错！", e);
			is = null;
		}
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("本地代码", "icloudUrl");
		fieldMap.put("链接名", "name");
		fieldMap.put("淘宝url", "taobaoUrl");
		fieldMap.put("描述", "desText");
		fieldMap.put("原始链接", "originUrl");

		List<JuhuasuanUrl> list = ExcelIEUtil.importFromInputStream(
				JuhuasuanUrl.class, fieldMap, is);
		String result = this.juhuasuanBussiness.batchUpdateUrl(list,
				this.getUserId());
		return result;
	}
}
