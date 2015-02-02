package com.icloud.paper.bean;

import java.util.Date;

import com.icloud.front.Paper.bussiness.PaperBussiness;
import com.icloud.stock.ctx.BeansUtil;
import com.icloud.stock.model.Paper;
import com.icloud.stock.model.constant.PaperEnum.ChannelEnum;

public class PaperImporter {
	private PaperBussiness paperBussiness = BeansUtil.getPaperBussiness();

	String content = "腾讯科技讯 苏宁云商1月30日晚间发布业绩修正预告称，此前预计的2014年亏损10亿元，修正为“盈利7.81亿元至8.36亿元”。<br/>苏宁云商公告中称，2014年10月31日，在《苏宁云商集团股份有限公司2014年第三季度报告》中预计2014年度归属于上市公司股东的净亏损为10.4亿元至11.9亿元，该业绩预计并未考虑其以部分门店物业为标的资产开展创新型资产运作这一交易完成的影响。<p>为此，苏宁云商大幅上调该业绩至盈利7.81亿元至8.36亿元，修正后较上年同期3.72亿元同比增长110%至125%。<p>2014年12月，苏宁云商实施完成以11个门店物业为标的资产开展的相关创新型资产运作模式，以11个自有门店物业房产权及对应的土地使用权分别出资设立11家全资子公司，并将11家全资子公司的全部权益转让给中信华夏苏宁云创资产支持专项计划。<p>此次交易转让价款根据资产评估值协商确定为43.42亿元，交易后实现税后净利润约为19.74亿元，对2014年苏宁云商的经营业绩产生影响。";

	public PaperImporter() {

	}

	public void importPaper() {
		Paper paper = new Paper();
		paper.setTitle("苏宁云商大幅上调全年业绩");
		paper.setContent(content);
		paper.setOriginTitle("苏宁云商大幅上调全年业绩");
		paper.setOriginUrl("http://tech.qq.com/a/20150130/079081.htm");
		paper.setChannel(ChannelEnum.TECH.getChannelName());
		paper.setCount(0);
		paper.setCreateTime(new Date());
		paper.setUpdateTime(paper.getCreateTime());
		paperBussiness.addPaper(paper);
	}
}
