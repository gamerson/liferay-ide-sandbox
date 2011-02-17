package com.liferay;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class IDEStatsJobListener implements MessageListener {

	
	@Override
	public void receive(Message arg0) throws MessageListenerException {
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
			
			Element statsTable = doc.getElementById("stats-table");
			
			Node footer = statsTable.getElementsByTagName("tfoot").item(0);
			
			String count = footer.getTextContent().trim();
			
			_log.info("Parsed new downloadCount of " + count + "for :" + statsUrl);
			
			return Integer.parseInt(count);
		} catch (Exception e) {
			_log.debug("Failed to parse: " + statsUrl, e);
			
			return -1;
		}
	} 
	
	 Log _log = LogFactoryUtil.getLog(IDEStatsJobListener.class);

}
