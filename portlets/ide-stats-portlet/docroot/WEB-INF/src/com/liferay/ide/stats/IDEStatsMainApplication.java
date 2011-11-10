package com.liferay.ide.stats;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class IDEStatsMainApplication  {

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
    		"1.3.0/updatesite/features/com.liferay.ide.eclipse.tools_1.3.0.v201107292029.jar",
    		
    		"1.3.1/liferay-ide-eclipse-updatesite-1.3.1.zip",
    		"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-macosx.tar.gz",
    		"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-macosx-x86_64.tar.gz",
    		"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-linux.tar.gz",
    		"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-linux-x86_64.tar.gz",
    		"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-win32.zip",
    		"1.3.1/eclipse_Liferay_IDE_1.3.1_v201108302303-win32-x86_64.zip",
    		"1.3.1/updatesite/features/com.liferay.ide.eclipse.tools_1.3.1.v201108310601.jar",
    		
    		"1.4.0/liferay-ide-eclipse-updatesite-1.4.0.zip",
    		"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-macosx.tar.gz",
    		"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-macosx-x86_64.tar.gz",
    		"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-linux.tar.gz",
    		"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-linux-x86_64.tar.gz",
    		"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-win32.zip",
    		"1.4.0/eclipse_Liferay_IDE_1.4.0_v201110020220-win32-x86_64.zip",
    		"1.4.0/updatesite/features/com.liferay.ide.eclipse.tools_1.4.0.v201110020918.jar",
    };
    
    private final String aprildaterange = "?start_date=2011-04-01&end_date=2011-04-30";
    private final String maydaterange = "?start_date=2011-05-01&end_date=2011-05-31";
    private final String junedaterange = "?start_date=2011-06-01&end_date=2011-06-30";
    private final String julydaterange = "?start_date=2011-07-01&end_date=2011-07-31";
    private final String augustdaterange = "?start_date=2011-08-01&end_date=2011-08-31";
    
    private final String septdaterange = "?start_date=2011-09-01&end_date=2011-09-30";
    private final String octdaterange = "?start_date=2011-10-01&end_date=2011-10-31";
    private final String novdaterange = "?start_date=2011-11-01&end_date=2011-11-30";
    
    private final String fullrange = "?start_date=2010-02-01&end_date=2011-11-30";
    
    public IDEStatsMainApplication() {
    }

    public static void main(String[] args) {
    	IDEStatsMainApplication app = new IDEStatsMainApplication();
       String stats = app.getstats();
       System.out.println(stats);
    }
    
    public String getstats() {
    	
    	
    	int april = count(aprildaterange);
    	int may = count(maydaterange);
    	int june = count(junedaterange);
    	int july = count(julydaterange);
    	int august = count(augustdaterange);
    	int sept = count(septdaterange);
    	int oct = count(octdaterange);
    	int nov = count(novdaterange);
    	int full = count(fullrange);
    	
    	return 
			"april = " + april + "\n" +
	    	"may = " + may + "\n" +
	    	"june = " + june + "\n" +
	    	"july = " + july + "\n" +
	    	"august = " + august + "\n" +
	    	"september = " + sept + "\n" +
	    	"october = " + oct + "\n" +
	    	"november = " + nov + "\n" +
	    	"full = " + full;
    	
    }
    
    private int count(String daterange) {
    	int retval = 0;
    	
    	for (String fileurl : fileurls) {
    		HttpClient client = new HttpClient();
        	
        	GetMethod get = new GetMethod(prefix + fileurl + suffix + daterange);
    		try {
				int result = client.executeMethod(get);
				if (result != HttpStatus.SC_OK) {
					retval = -1;
				}
				else {
					byte[] body = get.getResponseBody();

					String response = new String( body );
					JSONObject jsonObject = new JSONObject( response );
					int total = jsonObject.getInt("total");
					
					if (total < 0) {
						retval = -1;
						break;
					}
					else {
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
