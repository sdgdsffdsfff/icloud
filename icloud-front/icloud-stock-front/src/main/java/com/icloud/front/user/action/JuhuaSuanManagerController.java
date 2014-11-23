package com.icloud.front.user.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ExcelIEUtil;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.vo.KeyValue;
import com.icloud.front.juhuasuan.bussiness.po.UserUrlAccessCountPo;
import com.icloud.front.juhuasuan.pojo.UserUrlAccessCountResult;
import com.icloud.front.juhusuan.pojo.JuhuasuanFrontSession;
import com.icloud.front.stock.baseaction.BaseStockController;
import com.icloud.front.stock.pojo.JsonResponseResult;
import com.icloud.front.stock.pojo.JuhuasuanSearchBean;
import com.icloud.front.stock.pojo.JuhuasuanUrlBean;
import com.icloud.front.stock.pojo.UploadFileRequest;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.front.utils.ModelAndViewUtils;
import com.icloud.stock.model.JuhuasuanDetail;
import com.icloud.stock.model.JuhuasuanUrl;
import com.icloud.stock.model.User;

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
		ModelAndViewUtils.addPageView(modelAndView, pagination);
		modelAndView.addObject("urlBean", urlBean);
		return modelAndView;
	}

	@RequestMapping("/deleteTUrl")
	@ResponseBody
	public String operateUser(String code, HttpServletResponse response) {
		User user = this.getUser();
		JuhuasuanUrl url = this.juhuasuanBussiness.getJuhuasuanUrlByCode(code);
		JsonResponseResult result = new JsonResponseResult();
		result.setResult(0);
		result.setTip("对不起，你没有权限进行此操作");
		if (ICloudUtils.isNotNull(user) && ICloudUtils.isNotNull(url)
				&& url.getUserId() == user.getId().intValue()) {
			this.juhuasuanBussiness.deleteUrl(url);
			result.setResult(1);
			result.setTip("成功删除该链接");
		}

		Gson gson = new Gson();
		return gson.toJson(result);
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
			List<String> list = juhuasuanBussiness.getMoreUrl(urlBean);
			if (!ICloudUtils.isEmpty(list)) {
				modelAndView.addObject(key + "_moreUrls", list);
			}
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
			addmodelAndViewByCode(originJuhuasuanUrl.getIcloudUrl(), "urlBean",
					modelAndView);
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
		long currentDayValidCount = this.juhuasuanBussiness
				.getValidCountOfJuhusuanDetailInCurrentDay(this.getUserId());
		long lastDayCount = this.juhuasuanBussiness
				.getCountOfJuhusuanDetailInLastDay(this.getUserId());
		long lasyDayValidCount = this.juhuasuanBussiness
				.getValidCountOfJuhusuanDetailInLastDay(this.getUserId());
		long totalCount = this.juhuasuanBussiness.getCountOfJuhusuanDetail(
				this.getUserId(), null, null);
		long totalValidCount = this.juhuasuanBussiness
				.getValidCountOfJuhusuanDetail(this.getUserId(), null, null);
		Pagination<JuhuasuanDetail> pagination = this.juhuasuanBussiness
				.getDurrentDayJuhuasuanDetailByUserId(this.getUserId(),
						searhBean.getPageNo(), searhBean.getLimit());
		modelAndView.addObject("pagination", pagination);
		PageView pageView = PageView.convertPage(pagination);
		modelAndView.addObject("pageView", pageView);
		modelAndView.addObject("currentDayCount", currentDayCount);
		modelAndView.addObject("lastDayCount", lastDayCount);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("currentDayValidCount", currentDayValidCount);
		modelAndView.addObject("lasyDayValidCount", lasyDayValidCount);
		modelAndView.addObject("totalValidCount", totalValidCount);
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

	private User getStandardUser(JuhuasuanSearchBean searchBean, User user) {
		User tmpUser = user;
		int memberId = ICloudUtils.parseInt(searchBean.getMemberId());
		if (memberId != -1 && user.getId() != memberId) {
			UserInfo info = new UserInfo();
			info.setUserId(memberId);
			User u = this.userAdminBusiness.getUserByUserInfo(info);
			if (ICloudUtils.isNotNull(tmpUser)) {
				tmpUser = u;
			}
		}
		return tmpUser;
	}

	@RequestMapping("/tbMemberList")
	public ModelAndView tbMemberList(JuhuasuanSearchBean searchBean) {
		// searchBean.setLimit(1);
		ModelAndView modelAndView = getModelAndView("user/taobao/tb-member-url-list");
		User user = this.getUser();
		User tmpUser = getStandardUser(searchBean, user);

		JuhuasuanUrl urlBean = JuhuasuanSearchBean.convert(searchBean);
		if (!ICloudUtils.isNotNull(urlBean)) {
			urlBean = new JuhuasuanUrl();
		}
		urlBean.setUserId(tmpUser.getId());
		Pagination<JuhuasuanUrl> pagination = this.juhuasuanBussiness
				.searchJuhuasuanUrl(urlBean, searchBean.getPageNo(),
						searchBean.getLimit());
		ModelAndViewUtils.addPageView(modelAndView, pagination);

		List<User> parentsUsers = this.userAdminBusiness.findParentsUsers(user,
				tmpUser);
		if (!ICloudUtils.isEmpty(parentsUsers)) {
			modelAndView.addObject("parentsUsers", parentsUsers);
		}
		modelAndView.addObject("tmpUser", tmpUser);
		return modelAndView;
	}

	@RequestMapping("trafficUserView")
	public ModelAndView trafficUserView(JuhuasuanSearchBean searchBean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/trafficUserView");
		User user = this.getUser();
		User tmpUser = getStandardUser(searchBean, user);

		List<User> parentsUsers = this.userAdminBusiness.findParentsUsers(user,
				tmpUser);

		Pagination<UserUrlAccessCountPo> pagination = this.juhuasuanBussiness
				.getJuhuaSuanUserAccessCountByUserId(tmpUser,
						searchBean.getPageNo(), searchBean.getLimit());
		modelAndView.addObject("tmpUser", tmpUser);
		if (!ICloudUtils.isEmpty(parentsUsers)) {
			modelAndView.addObject("parentsUsers", parentsUsers);
		}
		ModelAndViewUtils.addPageView(modelAndView, pagination);
		return modelAndView;
	}

	@RequestMapping("/getJuhuasuanUseTraffic")
	@ResponseBody
	public String getJuhuasuanUseTraffic(
			@RequestParam(required = true) int userId) {
		User tmpUser = this.userAdminBusiness.getUser(userId);
		Pagination<UserUrlAccessCountPo> pagination = this.juhuasuanBussiness
				.getJuhuaSuanUserAccessCountByUserId(tmpUser, 0, 90);
		List<UserUrlAccessCountPo> data = (List<UserUrlAccessCountPo>) pagination
				.getData();
		UserUrlAccessCountResult result = UserUrlAccessCountResult
				.convertToUserUrlAccessCountResult(tmpUser.getUserName(), data);
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	@RequestMapping("trafficUserDetailView")
	public ModelAndView trafficUserDetailView(JuhuasuanSearchBean searchBean) {
		ModelAndView modelAndView = getModelAndView("user/taobao/trafficUserDetailView");
		User user = this.getUser();
		User tmpUser = getStandardUser(searchBean, user);
		Date date = DateUtils.getJustDate(searchBean.getDate());
		Pagination<UserUrlAccessCountPo> pagination = this.juhuasuanBussiness
				.getJuhuaSuanUserAccessCountDetaiByUserIdAndDate(tmpUser, date,
						searchBean.getPageNo(), searchBean.getLimit());
		List<User> parentsUsers = this.userAdminBusiness.findParentsUsers(user,
				tmpUser);
		if (!ICloudUtils.isEmpty(parentsUsers)) {
			modelAndView.addObject("parentsUsers", parentsUsers);
		}
		modelAndView.addObject("tmpUser", tmpUser);
		modelAndView.addObject("date", searchBean.getDate());
		ModelAndViewUtils.addPageView(modelAndView, pagination);
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
