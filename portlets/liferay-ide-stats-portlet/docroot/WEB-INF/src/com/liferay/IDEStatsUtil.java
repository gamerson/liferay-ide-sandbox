package com.liferay;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.util.portlet.PortletProps;

public class IDEStatsUtil {

	private static final String DOWNLOAD_COUNT = "DownloadCount";
	
	public static String[] getStatsUrls() {
		return PortletProps.getArray("ide.stats.urls");
	}

	public static Object getCachedDownloadCount(String statsUrl) {
		return MultiVMPoolUtil.get(IDEStatsUtil.class.getName(), statsUrl + DOWNLOAD_COUNT);
	}
	
	public static void putCachedDownloadCount(String statsUrl, int downloadCount) {
		MultiVMPoolUtil.put(IDEStatsUtil.class.getName(), statsUrl + DOWNLOAD_COUNT, downloadCount);
	}

}
