package com.liferay.ide;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;

public class Main
{
	private final String PREFIX = "http://sourceforge.net/projects/lportal/files/Liferay%20IDE/";
	private final String SUFFIX = "/stats/json";

	private final String[] URLS =
	{
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

		"1.6.1/liferay-ide-eclipse-updatesite-1.6.1.zip",
		"1.6.1/eclipse_Liferay_IDE_1.6.1.v201211020947-macosx.tar.gz",
		"1.6.1/eclipse_Liferay_IDE_1.6.1.v201211020947-macosx-x86_64.tar.gz",
		"1.6.1/eclipse_Liferay_IDE_1.6.1.v201211020947-linux.tar.gz",
		"1.6.1/eclipse_Liferay_IDE_1.6.1.v201211020947-linux-x86_64.tar.gz",
		"1.6.1/eclipse_Liferay_IDE_1.6.1.v201211020947-win32.zip",
		"1.6.1/eclipse_Liferay_IDE_1.6.1.v201211020947-win32-x86_64.zip",
		"1.6.1/updatesite/plugins/com.liferay.ide.eclipse.core_1.6.1.v201211020947.jar",
	};

	private final String dec2012 = "?start_date=2012-12-01&end_date=2012-12-31";
	private final String jan2013 = "?start_date=2013-01-01&end_date=2013-01-31";
	private final String feb2013 = "?start_date=2013-02-01&end_date=2013-02-28";

	private final String fullrange = "?start_date=2010-02-01&end_date=2015-01-01";

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Main app = new Main();
		String stats = app.getstats();
		System.out.println(stats);
	}

	public String getstats() {

		int dec2012count = count(dec2012);
		int jan2013count = count(jan2013);
		int feb2013count = count(feb2013);
		int full = count(fullrange);

		return
			"dec 2012 = " + dec2012count + "\n" +
			"jan 2013 = " + jan2013count + "\n" +
			"feb 2013 = " + feb2013count + "\n" +

			"all time = " + full;
	}

	private int count(String daterange)
	{
		int retval = 0;

		for (String fileurl : URLS)
		{
			HttpClient client = new HttpClient();

			GetMethod get = new GetMethod(PREFIX + fileurl + SUFFIX + daterange);

			try
			{
				// System.out.println(get.getURI());
				System.out.println(fileurl + SUFFIX + daterange);
				int result = client.executeMethod(get);
				if (result != HttpStatus.SC_OK)
				{
					retval = -1;
				}
				else
				{
					byte[] body = get.getResponseBody();

					String response = new String(body);
					JSONObject jsonObject = new JSONObject(response);
					int total = jsonObject.getInt("total");

					if (total < 0)
					{
						retval = -1;
						break;
					}
					else
					{
						System.out.println("got total = " + total);
						retval += total;
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retval = -1;
			}
		}

		return retval;
	}

}
