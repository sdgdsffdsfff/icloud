package com.icloud.stock.model.constant;

import com.icloud.framework.core.common.ReturnCode;
import com.icloud.framework.core.exception.ICloudException;
import com.icloud.framework.util.ICloudUtils;

public final class PaperEnum {
	public enum ChannelEnum {
		TECH("keji"); //
		private String channelName;

		private ChannelEnum(String channelName) {
			this.channelName = channelName;
		}

		public String getChannelName() {
			return channelName;
		}

		public static ChannelEnum getChannelEnum(String channelName) {
			if (!ICloudUtils.isNotNull(channelName)) {
				throw ICloudException.instance(
						ReturnCode.E_STOCK_LOCATION_ERROR, "channelName不能为空,"
								+ channelName);
			}
			if (channelName.equalsIgnoreCase(TECH.getChannelName()))
				return TECH;
			throw ICloudException.instance(ReturnCode.E_STOCK_LOCATION_ERROR,
					"channelName不全," + channelName);
		}
	}

}
