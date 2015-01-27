package com.cninfo.shtb.member.action;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cninfo.shtb.mongo.entity.Member;
import com.cninfo.shtb.mongo.service.IMemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static Logger log = LoggerFactory.getLogger(MemberController.class);

	@Resource(name = "shtb_MemberService")
	protected IMemberService memberService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView getAll() {

		List<Member> members = this.memberService.getAll();
		ModelAndView model = new ModelAndView("member/listMembers");
		model.addObject("members", members);
		return model;
		// return "nihao";
	}

}