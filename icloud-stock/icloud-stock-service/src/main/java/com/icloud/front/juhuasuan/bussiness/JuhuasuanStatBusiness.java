package com.icloud.front.juhuasuan.bussiness;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.User;
import com.icloud.user.bussiness.po.AllUserPo;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午2:42:51
 */
@Service("juhuasuanStatBusiness")
public class JuhuasuanStatBusiness extends BaseAction {
	public AllUserPo getAllUserPo() {
		AllUserPo allUserPo = new AllUserPo(this.userService, 20);
		return allUserPo;
	}

	public void updateUserUrlAccessCountDaily() {
		/**
		 * 获得所有用户进行更新
		 */
		AllUserPo userPo = this.getAllUserPo();
		User nextUser = userPo.next();
		while (ICloudUtils.isNotNull(nextUser)) {
			nextUser = userPo.next();
		}
	}
}
