package com.liferay.ide.stats;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;

public class IDEStatsMainApplication {

	private final String prefix = "http://sourceforge.net/projects/lportal/files/Liferay%20IDE/";
	private final String suffix = "/stats/json";

	private final String[] fileurls = {
			"1.0.0/liferay-ide-eclipse-updatesite-1.0.0.zip",

			"1.0.1/liferay-ide-eclipse-updatesite-1.0.1.zip",

			"1.1.0/liferay-ide-eclipse-updatesite-1.1.0.zip",

			"1.2.0/liferay-ide-eclipse-updatesite-1.2.0.zip",
			"1.2.0/eclipse_Liferay_IDE_v201102100936-linux.tar.gz",
			"1.2.0/eclipse_Liferay_IDE_v201102100936-linux-x86_64.tar.gz",
			"1.2.0/eclipse_Liferay_IDE_v201102100936-macosx.tar.gz",
			"1.2.0/eclipse_Liferay_IDE_v201102100936-macosx-x86_64.tar.gz",
			"1.2.0/eclipse_Liferay_IDE_v201102100936-win32.zip",
			"1.2.0/eclipse_Liferay_IDE_v201102100936-win32-x86_64.zip",
			"1.2.0/updatesite/plugins/com.liferay.ide.eclipse.core_1.2.0.v201102100926.jar",

			"1.2.1/liferay-ide-eclipse-updatesite-1.2.1.zip",
			"1.2.1/eclipse_Liferay_IDE_v201102181659-win32.zip",
			"1.2.1/eclipse_Liferay_IDE_v201102181659-win32-x86_64.zip",
			"1.2.1/eclipse_Liferay_IDE_v201102181659-macosx.tar.gz",
			"1.2.1/eclipse_Liferay_IDE_v201102181659-macosx-x86_64.tar.gz",
			"1.2.1/eclipse_Liferay_IDE_v201102181659-linux.tar.gz",
			"1.2.1/eclipse_Liferay_IDE_v201102181659-linux-x86_64.tar.gz",
			"1.2.1/updatesite/features/com.liferay.ide.eclipse.tools_1.2.1.v201102181651.jar",

			"1.2.2/liferay-ide-eclipse-updatesite-1.2.2.zip",
			"1.2.2/eclipse_Liferay_IDE_v201102281651-win32.zip",
			"1.2.2/eclipse_Liferay_IDE_v201102281651-win32-x86_64.zip",
			"1.2.2/eclipse_Liferay_IDE_v201102281651-macosx.tar.gz",
			"1.2.2/eclipse_Liferay_IDE_v201102281651-macosx-x86_64.tar.gz",
			"1.2.2/eclipse_Liferay_IDE_v201102281651-linux.tar.gz",
			"1.2.2/eclipse_Liferay_IDE_v201102281651-linux-x86_64.tar.gz",
			"1.2.2/updatesite/features/com.liferay.ide.eclipse.tools_1.2.2.v201102281644.jar",

			"1.2.3/liferay-ide-eclipse-updatesite-1.2.3.zip",
			"1.2.3/eclipse_Liferay_IDE_v201103310222-macosx.tar.gz",
			"1.2.3/eclipse_Liferay_IDE_v201103310222-macosx-x86_64.tar.gz",
			"1.2.3/eclipse_Liferay_IDE_v201103310222-linux.tar.gz",
			"1.2.3/eclipse_Liferay_IDE_v201103310222-linux-x86_64.tar.gz",
			"1.2.3/eclipse_Liferay_IDE_v201103310222-win32.zip",
			"1.2.3/eclipse_Liferay_IDE_v201103310222-win32-x86_64.zip",
			"1.2.3/updatesite/features/com.liferay.ide.eclipse.tools_1.2.3.v201103310212.jar",

			"1.3.0/liferay-ide-eclipse-updatesite-1.3.0.zip",
			"1.3.0/eclipse_Liferay_IDE_1.3.0_v201107291331-macosx.tar.gz",
			"1.3.0/eclipse_Liferay_IDE_1.3.0_v201107291331-macosx-x86_64.tar.gz",
			"1.3.0/eclipse_Liferay_IDE_1.3.0_v201107291331-linux.tar.gz",
			"1.3.0/eclipse_Liferay_IDE_1.3.0_v201107291331-linux-x86_64.tar.gz",
			"1.3.0/eclipse_Liferay_IDE_1.3.0_v201107291331-win32.zip",
			"1.3.0/eclipse_Liferay_IDE_1.3.0_v201107291331-win32-x86_64.zip",
			"1.3.0/updatesite/plugins/com.liferay.ide.eclipse.core_1.3.0.v201107292029.jar",

			"1.3.1/liferay-ide-eclipse-updatesite-1.3.1.zip",
			"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-macosx.tar.gz",
			"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-macosx-x86_64.tar.gz",
			"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-linux.tar.gz",
			"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-linux-x86_64.tar.gz",
			"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-win32.zip",
			"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-win32-x86_64.zip",
			"1.3.1/updatesite/plugins/com.liferay.ide.eclipse.core_1.3.1.v201108310601.jar",

			"1.4.0/liferay-ide-eclipse-updatesite-1.4.0.zip",
			"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-macosx.tar.gz",
			"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-macosx-x86_64.tar.gz",
			"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-linux.tar.gz",
			"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-linux-x86_64.tar.gz",
			"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-win32.zip",
			"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-win32-x86_64.zip",
			"1.4.0/updatesite/plugins/com.liferay.ide.eclipse.core_1.4.0.v201110020918.jar",

			"1.5.0/liferay-ide-eclipse-updatesite-1.5.0.zip",
			"1.5.0/eclipse_Liferay_IDE_1.5.0_v201201102224-macosx.tar.gz",
			"1.5.0/eclipse_Liferay_IDE_1.5.0_v201201102224-macosx-x86_64.tar.gz",
			"1.5.0/eclipse_Liferay_IDE_1.5.0_v201201102224-linux.tar.gz",
			"1.5.0/eclipse_Liferay_IDE_1.5.0_v201201102224-linux-x86_64.tar.gz",
			"1.5.0/eclipse_Liferay_IDE_1.5.0_v201201102224-win32.zip",
			"1.5.0/eclipse_Liferay_IDE_1.5.0_v201201102224-win32-x86_64.zip",
			"1.5.0/updatesite/plugins/com.liferay.ide.eclipse.core_1.5.0.v201201110622.jar",

			"1.5.1/liferay-ide-eclipse-updatesite-1.5.1.zip",
			"1.5.1/eclipse_Liferay_IDE_1.5.1_v201202280246-macosx.tar.gz",
			"1.5.1/eclipse_Liferay_IDE_1.5.1_v201202280246-macosx-x86_64.tar.gz",
			"1.5.1/eclipse_Liferay_IDE_1.5.1_v201202280246-linux.tar.gz",
			"1.5.1/eclipse_Liferay_IDE_1.5.1_v201202280246-linux-x86_64.tar.gz",
			"1.5.1/eclipse_Liferay_IDE_1.5.1_v201202280246-win32.zip",
			"1.5.1/eclipse_Liferay_IDE_1.5.1_v201202280246-win32-x86_64.zip",
			"1.5.1/updatesite/plugins/com.liferay.ide.eclipse.core_1.5.1.v201202281044.jar",

			"1.5.2/liferay-ide-eclipse-updatesite-1.5.2.zip",
			"1.5.2/eclipse_Liferay_IDE_1.5.2_v201203040240-macosx.tar.gz",
			"1.5.2/eclipse_Liferay_IDE_1.5.2_v201203040240-macosx-x86_64.tar.gz",
			"1.5.2/eclipse_Liferay_IDE_1.5.2_v201203040240-linux.tar.gz",
			"1.5.2/eclipse_Liferay_IDE_1.5.2_v201203040240-linux-x86_64.tar.gz",
			"1.5.2/eclipse_Liferay_IDE_1.5.2_v201203040240-win32.zip",
			"1.5.2/eclipse_Liferay_IDE_1.5.2_v201203040240-win32-x86_64.zip",
			"1.5.2/updatesite/plugins/com.liferay.ide.eclipse.core_1.5.2.v201203041037.jar",

			"1.5.3/liferay-ide-eclipse-updatesite-1.5.3.zip",
			"1.5.3/eclipse_Liferay_IDE_1.5.3.v201205280937-macosx.tar.gz",
			"1.5.3/eclipse_Liferay_IDE_1.5.3.v201205280937-macosx-x86_64.tar.gz",
			"1.5.3/eclipse_Liferay_IDE_1.5.3.v201205280937-linux.tar.gz",
			"1.5.3/eclipse_Liferay_IDE_1.5.3.v201205280937-linux-x86_64.tar.gz",
			"1.5.3/eclipse_Liferay_IDE_1.5.3.v201205280937-win32.zip",
			"1.5.3/eclipse_Liferay_IDE_1.5.3.v201205280937-win32-x86_64.zip",
			"1.5.3/updatesite/plugins/com.liferay.ide.eclipse.core_1.5.3.v201205280937.jar",

			"1.6.0/liferay-ide-eclipse-updatesite-1.6.0.zip",
			"1.6.0/eclipse_Liferay_IDE_1.6.0.v201207162359-macosx.tar.gz",
			"1.6.0/eclipse_Liferay_IDE_1.6.0.v201207162359-macosx-x86_64.tar.gz",
			"1.6.0/eclipse_Liferay_IDE_1.6.0.v201207162359-linux.tar.gz",
			"1.6.0/eclipse_Liferay_IDE_1.6.0.v201207162359-linux-x86_64.tar.gz",
			"1.6.0/eclipse_Liferay_IDE_1.6.0.v201207162359-win32.zip",
			"1.6.0/eclipse_Liferay_IDE_1.6.0.v201207162359-win32-x86_64.zip",
			"1.6.0/updatesite/plugins/com.liferay.ide.eclipse.core_1.6.0.v201207162359.jar",

	};

	private final String dec2010 = "?start_date=2010-12-01&end_date=2010-12-31";
	private final String jan2011 = "?start_date=2011-01-01&end_date=2011-01-31";
	private final String feb2011 = "?start_date=2011-02-01&end_date=2011-02-28";
	private final String mar2011 = "?start_date=2011-03-01&end_date=2011-03-31";
	private final String apr2011 = "?start_date=2011-04-01&end_date=2011-04-30";
	private final String may2011 = "?start_date=2011-05-01&end_date=2011-05-31";
	private final String jun2011 = "?start_date=2011-06-01&end_date=2011-06-30";
	private final String jul2011 = "?start_date=2011-07-01&end_date=2011-07-31";
	private final String aug2011 = "?start_date=2011-08-01&end_date=2011-08-31";
	private final String sep2011 = "?start_date=2011-09-01&end_date=2011-09-30";
	private final String oct2011 = "?start_date=2011-10-01&end_date=2011-10-31";
	private final String nov2011 = "?start_date=2011-11-01&end_date=2011-11-30";
	private final String dec2011 = "?start_date=2011-12-01&end_date=2011-12-31";
	private final String jan2012 = "?start_date=2012-01-01&end_date=2012-01-31";
	private final String feb2012 = "?start_date=2012-02-01&end_date=2012-02-29";
	private final String mar2012 = "?start_date=2012-03-01&end_date=2012-03-31";
	private final String apr2012 = "?start_date=2012-04-01&end_date=2012-04-30";
	private final String may2012 = "?start_date=2012-05-01&end_date=2012-05-31";
	private final String jun2012 = "?start_date=2012-06-01&end_date=2012-06-30";
	private final String jul2012 = "?start_date=2012-07-01&end_date=2012-07-31";
	private final String aug2012 = "?start_date=2012-08-01&end_date=2012-08-31";
	private final String sep2012 = "?start_date=2012-09-01&end_date=2012-09-30";

	private final String fullrange = "?start_date=2010-02-01&end_date=2015-01-01";

	public IDEStatsMainApplication() {
	}

	public static void main(String[] args) {
		IDEStatsMainApplication app = new IDEStatsMainApplication();
		String stats = app.getstats();
		System.out.println(stats);
	}

	public String getstats() {
//		int dec2010count = count(dec2010);
//		int jan2011count = count(jan2011);
//		int feb2011count = count(feb2011);
//		int mar2011count = count(mar2011);
//		int apr2011count = count(apr2011);
//		int may2011count = count(may2011);
//		int jun2011count = count(jun2011);
//		int jul2011count = count(jul2011);
//		int aug2011count = count(aug2011);
//		int sep2011count = count(sep2011);
//		int oct2011count = count(oct2011);
//		int nov2011count = count(nov2011);
//		int dec2011count = count(dec2011);
//		int jan2012count = count(jan2012);
//		int feb2012count = count(feb2012);
//		int mar2012count = count(mar2012);
//		int apr2012count = count(apr2012);
//		int may2012count = count(may2012);
//		int jun2012count = count(jun2012);
		int jul2012count = count(jul2012);
		int aug2012count = count(aug2012);
		int sep2012count = count(sep2012);
		int full = count(fullrange);

		return
//		"dec 2010 = " + dec2010count + "\n" + 
				
//		"jan 2011 = " + jan2011count + "\n" + 
//		"feb 2011 = " + feb2011count + "\n" + 
//		"mar 2011 = " + mar2011count + "\n" + 
//		"april 2011 = " + apr2011count + "\n" + 
//		"may 2011 = " + may2011count + "\n" + 
//		"june 2011 = " + jun2011count + "\n" + 
//		"july 2011 = " + jul2011count + "\n" + 
//		"aug 2011 = " + aug2011count + "\n" + 
//		"sept 2011 = " + sep2011count + "\n" + 
//		"oct 2011 = " + oct2011count + "\n" + 
//		"nov 2011 = " + nov2011count + "\n" + 
//		"dec 2011 = " + dec2011count + "\n" + 
		
//		"jan 2012 = " + jan2012count + "\n" + 
//		"feb 2012 = " + feb2012count + "\n" + 
//		"mar 2012 = " + mar2012count + "\n" + 
//		"april 2012 = " + apr2012count + "\n" + 
//		"may 2012 = " + may2012count + "\n" + 
//		"june 2012 = " + jun2012count + "\n" + 
		"july 2012 = " + jul2012count + "\n" + 
		"aug 2012 = " + aug2012count + "\n" + 
		"sep 2012 = " + sep2012count + "\n" + 
		
		"all time = " + full;
	}

	private int count(String daterange) {
		int retval = 0;

		for (String fileurl : fileurls) {
			HttpClient client = new HttpClient();

			GetMethod get = new GetMethod(prefix + fileurl + suffix + daterange);
			try {
				// System.out.println(get.getURI());
				System.out.println(fileurl + suffix + daterange);
				int result = client.executeMethod(get);
				if (result != HttpStatus.SC_OK) {
					retval = -1;
				} else {
					byte[] body = get.getResponseBody();

					String response = new String(body);
					JSONObject jsonObject = new JSONObject(response);
					int total = jsonObject.getInt("total");

					if (total < 0) {
						retval = -1;
						break;
					} else {
						System.out.println("got total = " + total);
						retval += total;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				retval = -1;
			}
		}

		return retval;
	}

}
