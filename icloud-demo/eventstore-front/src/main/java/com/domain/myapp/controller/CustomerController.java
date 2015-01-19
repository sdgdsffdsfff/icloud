package com.domain.myapp.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import no.ks.eventstore2.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.domain.myapp.model.CustomerAggregate;
import com.domain.myapp.model.CustomerID;
import com.domain.myapp.service.CustomerService;
import com.icloud.framework.util.ICloudUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	private static Logger log = LoggerFactory
			.getLogger(CustomerController.class);

	private static final int SERVICE_UNAVAILABLE = 503;

	@Resource(name = "customerService")
	private CustomerService customerService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String doSelfTest(HttpServletResponse response) {
		log.info("Do self test");
		List<CustomerID> list = customerService.getAllIDs();
		for (CustomerID id : list) {
			System.out.println(id);
		}
		return "nihao";
		// return ""
	}

	@RequestMapping("/listCustomers")
	public ModelAndView listCustomers() {
		ModelAndView model = new ModelAndView("event/list-customers");
		List<CustomerID> list = customerService.getAllIDs();
		model.addObject("customers", list);
		return model;
	}

	@RequestMapping("/viewCustomer")
	public ModelAndView viewCustomer(String aid) {
		ModelAndView model = new ModelAndView("event/view-customer");
		CustomerAggregate customer = customerService.getCustomer(aid);
		List<Event> events = customerService.getAllEventByAid(aid);
		model.addObject("customer", customer);
		model.addObject("events", events);
		return model;
	}

	@RequestMapping("/changeAttr")
	public String changeAttr(String aid, String changeKey, String changeValue) {
		if (ICloudUtils.isNotNull(changeValue)
				&& ICloudUtils.isNotNull(changeKey)) {
			if (changeKey.equalsIgnoreCase("0")) {
				if (ICloudUtils.isNotNull(changeValue)) {
					this.customerService.changeCustomerName(aid, changeValue);
				}
			} else if (changeKey.equalsIgnoreCase("1")) {
				int value = ICloudUtils.parseInt(changeValue);
				if (value != -1) {
					this.customerService.changeCustomerYear(aid, value);
				}
			}
		}
		return "redirect:/customer/viewCustomer?aid=" + aid;
	}

	@RequestMapping("/addCustomer")
	public String addCustomer() {
		customerService.addIDs();
		return "redirect:/customer/listCustomers";
	}

	@RequestMapping("/contract-us")
	public ModelAndView contractUs() {
		ModelAndView model = new ModelAndView("icloud-base/icloud-contractus");
		return model;
	}

}