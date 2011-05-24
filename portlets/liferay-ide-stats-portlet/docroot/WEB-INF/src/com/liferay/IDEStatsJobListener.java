package com.liferay;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IDEStatsJobListener implements MessageListener {

	
	@Override
	public void receive(Message arg0)  {
		String[] statsUrls = IDEStatsUtil.getStatsUrls();
		
		for (String statsUrl : statsUrls) {
			int downloadCount = parseDownloadCount(statsUrl);
			
			if (downloadCount > -1) {
				IDEStatsUtil.putCachedDownloadCount(statsUrl, downloadCount);
			}
		}
	}
	
	protected int parseDownloadCount(final String statsUrl) {
		DOMParser domParser = new DOMParser();
		
		try {
			domParser.parse(statsUrl);
			
			Document doc = domParser.getDocument();
			
			Element statsTable = doc.getElementById("files_list");
			
			Element tbody = (Element) statsTable.getElementsByTagName("tbody").item(0);
			
			NodeList imgs = tbody.getElementsByTagName("img");
			
			int downloadCount = 0;
			
			for (int i = 0; i < imgs.getLength(); i++) {
				try {
					Element img = (Element) imgs.item(i);
					
					if (img.getAttribute("class").contains("file")) {
						String title = img.getAttribute("title");
						
						title = title.replaceAll(",", "");
						
						String count = title.substring(0, title.indexOf(' '));
						
						_log.info("Parsed new downloadCount of " + count + "for :" + statsUrl);
						
						downloadCount += Integer.parseInt(count);
					}
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return downloadCount;
		} catch (Exception e) {
			_log.debug("Failed to parse: " + statsUrl, e);
			
			return -1;
		}
	} 
	
	 Log _log = LogFactoryUtil.getLog(IDEStatsJobListener.class);

}
