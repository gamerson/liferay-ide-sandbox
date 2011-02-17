package com.liferay;

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
			
			IDEStatsUtil.putCachedDownloadCount(statsUrl, downloadCount);
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
			
			return Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
			
			return -1;
		}
	} 

}
