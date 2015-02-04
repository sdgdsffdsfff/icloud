package com.cninfo.shtb.member.action;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cninfo.shtb.member.domain.AccountAggregate;
import com.cninfo.shtb.member.vo.AccountVO;
import com.cninfo.shtb.mongo.entity.Member;
import com.cninfo.shtb.mongo.service.IAccountService;
import com.cninfo.shtb.mongo.service.IMemberService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static Logger log = LoggerFactory.getLogger(MemberController.class);

	@Resource(name = "shtb_MemberService")
	protected IMemberService memberService;

	@Resource(name = "shtb_AccountService")
	protected IAccountService accountService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView getAll() {

		List<Member> members = this.memberService.getAll();
		ModelAndView model = new ModelAndView("member/listMembers");
		model.addObject("members", members);
		return model;
		// return "nihao";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String getAll2() {
		List<Member> members = this.memberService.getAll();

		return new Gson().toJson(members);
		// ModelAndView model = new ModelAndView("member/listMembers");
		// model.addObject("members", members);
		// return model;
		// return "nihao";
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView getAll3() {
		// List<Member> members = this.memberService.getAll();
		//
		// return new Gson().toJson(members);
		ModelAndView model = new ModelAndView("member/w2");
		// model.addObject("members", members);
		return model;
		// return "nihao";
	}

	@RequestMapping(value = "/getAccountForUserId", method = RequestMethod.GET)
	@ResponseBody
	public String getAccountService(int userId) {
		AccountAggregate account = accountService.getAccount(userId);
		AccountVO accountVO = AccountVO.convertAccountVO(account);
		return new Gson().toJson(accountVO);
	}
}