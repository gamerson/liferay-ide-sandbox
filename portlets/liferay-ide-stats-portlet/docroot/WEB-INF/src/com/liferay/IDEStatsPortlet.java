package com.liferay;

import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Portlet implementation class IDEStatsPortlet
 */
public class IDEStatsPortlet extends MVCPortlet {

	private static final String TOTAL_DOWNLOAD_COUNT = "totalDownloadCount";
	
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		
		int totalDownloadCount = 0;
		
		String[] statsUrls = IDEStatsUtil.getStatsUrls();
		
		if (statsUrls != null && statsUrls.length > 0) {
			for (String statsUrl : statsUrls) {
				int downloadCount = getDownloadCount(statsUrl);
				
				if (downloadCount < 0) {
					totalDownloadCount = -1;
				}
				else if (totalDownloadCount >= 0) {
					totalDownloadCount += downloadCount;
				} 
			}
		}
		
		renderRequest.setAttribute(TOTAL_DOWNLOAD_COUNT, totalDownloadCount);
		
		super.doView(renderRequest, renderResponse);
	}

	protected int getDownloadCount(final String statsUrl) {
		Object downloadCount = IDEStatsUtil.getCachedDownloadCount(statsUrl);
		
		if (downloadCount == null) {			
			return -1;
		}
		
		if (downloadCount instanceof Integer) {
			return (Integer)downloadCount;
		}
		else {
			return Integer.parseInt(downloadCount.toString());
		}
		
	}

}
