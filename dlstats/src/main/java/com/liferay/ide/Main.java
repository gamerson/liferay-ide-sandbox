/*******************************************************************************
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/

package com.liferay.ide;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;

/**
 * @author Gregory Amerson
 */
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

        "1.6.2/liferay-ide-eclipse-updatesite-1.6.2.zip",
        "1.6.2/eclipse_Liferay_IDE_1.6.2.v201303111029-macosx.tar.gz",
        "1.6.2/eclipse_Liferay_IDE_1.6.2.v201303111029-macosx-x86_64.tar.gz",
        "1.6.2/eclipse_Liferay_IDE_1.6.2.v201303111029-linux.tar.gz",
        "1.6.2/eclipse_Liferay_IDE_1.6.2.v201303111029-linux-x86_64.tar.gz",
        "1.6.2/eclipse_Liferay_IDE_1.6.2.v201303111029-win32.zip",
        "1.6.2/eclipse_Liferay_IDE_1.6.2.v201303111029-win32-x86_64.zip",
        "1.6.2/updatesite/plugins/com.liferay.ide.eclipse.core_1.6.2.v201303111029.jar",

        "2.0.0%20M1/liferay-ide-updatesite-2.0.0-m1-201308200800.zip",
        "2.0.0%20M1/liferay-ide-eclipse-windows-x64-2.0.0-m1-201308200800.zip",
        "2.0.0%20M1/liferay-ide-eclipse-windows-x32-2.0.0-m1-201308200800.zip",
        "2.0.0%20M1/liferay-ide-eclipse-macosx-x64-2.0.0-m1-201308200800.tar.gz",
        "2.0.0%20M1/liferay-ide-eclipse-macosx-x32-2.0.0-m1-201308200800.tar.gz",
        "2.0.0%20M1/liferay-ide-eclipse-linux-x64-2.0.0-m1-201308200800.tar.gz",
        "2.0.0%20M1/liferay-ide-eclipse-linux-x32-2.0.0-m1-201308200800.tar.gz",
        "2.0.0%20M1/updatesite/tools/plugins/com.liferay.ide.core_2.0.0.20130820-0800-M1.jar",

        "2.0.0%20M2/liferay-ide-updatesite-2.0.0-m2-201311051611.zip",
        "2.0.0%20M2/liferay-ide-eclipse-windows-x64-2.0.0-m2-201311051611.zip",
        "2.0.0%20M2/liferay-ide-eclipse-windows-x32-2.0.0-m2-201311051611.zip",
        "2.0.0%20M2/liferay-ide-eclipse-macosx-x64-2.0.0-m2-201311051611.tar.gz",
        "2.0.0%20M2/liferay-ide-eclipse-macosx-x32-2.0.0-m2-201311051611.tar.gz",
        "2.0.0%20M2/liferay-ide-eclipse-linux-x64-2.0.0-m2-201311051611.tar.gz",
        "2.0.0%20M2/liferay-ide-eclipse-linux-x32-2.0.0-m2-201311051611.tar.gz",
        "2.0.0%20M2/updatesite/tools/plugins/com.liferay.ide.core_2.0.0.201311051611-m2.jar",

        "2.0.0%20M3/liferay-ide-updatesite-2.0.0-m3-201311271605.zip",
        "2.0.0%20M3/liferay-ide-eclipse-windows-x64-2.0.0-m3-201311271605.zip",
        "2.0.0%20M3/liferay-ide-eclipse-windows-x32-2.0.0-m3-201311271605.zip",
        "2.0.0%20M3/liferay-ide-eclipse-macosx-x64-2.0.0-m3-201311271605.tar.gz",
        "2.0.0%20M3/liferay-ide-eclipse-macosx-x32-2.0.0-m3-201311271605.tar.gz",
        "2.0.0%20M3/liferay-ide-eclipse-linux-x64-2.0.0-m3-201311271605.tar.gz",
        "2.0.0%20M3/liferay-ide-eclipse-linux-x32-2.0.0-m3-201311271605.tar.gz",
        "2.0.0%20M3/updatesite/tools/plugins/com.liferay.ide.core_2.0.0.201311271605-m3.jar",

        "2.0.0%20GA1/liferay-ide-updatesite-2.0.0-ga1-201312200205.zip",
        "2.0.0%20GA1/liferay-ide-eclipse-windows-x64-2.0.0-ga1-201312200205.zip",
        "2.0.0%20GA1/liferay-ide-eclipse-windows-x32-2.0.0-ga1-201312200205.zip",
        "2.0.0%20GA1/liferay-ide-eclipse-macosx-x64-2.0.0-ga1-201312200205.tar.gz",
        "2.0.0%20GA1/liferay-ide-eclipse-macosx-x32-2.0.0-ga1-201312200205.tar.gz",
        "2.0.0%20GA1/liferay-ide-eclipse-linux-x64-2.0.0-ga1-201312200205.tar.gz",
        "2.0.0%20GA1/liferay-ide-eclipse-linux-x32-2.0.0-ga1-201312200205.tar.gz",
        "2.0.0%20GA1/updatesite/tools/plugins/com.liferay.ide.core_2.0.0.201312200205-ga1.jar",
        "2.0.0%20GA1/updatesite/maven/plugins/com.liferay.ide.maven.core_2.0.0.201312200205-ga1.jar",

        "2.0.1%20GA2/liferay-ide-updatesite-2.0.1-ga2-201401270944.zip",
        "2.0.1%20GA2/liferay-ide-eclipse-windows-x64-2.0.1-ga2-201401270944.zip",
        "2.0.1%20GA2/liferay-ide-eclipse-windows-x32-2.0.1-ga2-201401270944.zip",
        "2.0.1%20GA2/liferay-ide-eclipse-macosx-x64-2.0.1-ga2-201401270944.tar.gz",
        "2.0.1%20GA2/liferay-ide-eclipse-macosx-x32-2.0.1-ga2-201401270944.tar.gz",
        "2.0.1%20GA2/liferay-ide-eclipse-linux-x64-2.0.1-ga2-201401270944.tar.gz",
        "2.0.1%20GA2/liferay-ide-eclipse-linux-x32-2.0.1-ga2-201401270944.tar.gz",
        "2.0.1%20GA2/updatesite/tools/plugins/com.liferay.ide.core_2.0.1.201401270944-ga2.jar",
        "2.0.1%20GA2/updatesite/maven/plugins/com.liferay.ide.maven.core_2.0.1.201401270944-ga2.jar",

        "2.1.0%20GA1/liferay-ide-updatesite-2.1.0-ga1-201403281241.zip",
        "2.1.0%20GA1/liferay-ide-eclipse-windows-x64-2.1.0-ga1-201403281241.zip",
        "2.1.0%20GA1/liferay-ide-eclipse-windows-x32-2.1.0-ga1-201403281241.zip",
        "2.1.0%20GA1/liferay-ide-eclipse-macosx-x64-2.1.0-ga1-201403281241.tar.gz",
        "2.1.0%20GA1/liferay-ide-eclipse-macosx-x32-2.1.0-ga1-201403281241.tar.gz",
        "2.1.0%20GA1/liferay-ide-eclipse-linux-x64-2.1.0-ga1-201403281241.tar.gz",
        "2.1.0%20GA1/liferay-ide-eclipse-linux-x32-2.1.0-ga1-201403281241.tar.gz",
        "2.1.0%20GA1/updatesite/tools/plugins/com.liferay.ide.core_2.1.0.201403281241-ga1.jar",
        "2.1.0%20GA1/updatesite/maven/plugins/com.liferay.ide.maven.core_2.1.0.201403281241-ga1.jar",

        "2.1.1%20GA2/liferay-ide-updatesite-2.1.1-ga2-201406300312.zip",
        "2.1.1%20GA2/liferay-ide-eclipse-windows-x64-2.1.1-ga2-201406300312.zip",
        "2.1.1%20GA2/liferay-ide-eclipse-windows-x32-2.1.1-ga2-201406300312.zip",
        "2.1.1%20GA2/liferay-ide-eclipse-macosx-x64-2.1.1-ga2-201406300312.tar.gz",
        "2.1.1%20GA2/liferay-ide-eclipse-linux-x64-2.1.1-ga2-201406300312.tar.gz",
        "2.1.1%20GA2/liferay-ide-eclipse-linux-x32-2.1.1-ga2-201406300312.tar.gz",
        "2.1.1%20GA2/updatesite/tools/plugins/com.liferay.ide.core_2.1.1.201406300312-ga2.jar",
        "2.1.1%20GA2/updatesite/maven/plugins/com.liferay.ide.maven.core_2.1.1.201406300312-ga2.jar",

        "2.2.0%20GA1/liferay-ide-updatesite-2.2.0-ga1-201410291007.zip",
        "2.2.0%20GA1/liferay-ide-eclipse-windows-x64-2.2.0-ga1-201410291007.zip",
        "2.2.0%20GA1/liferay-ide-eclipse-windows-x32-2.2.0-ga1-201410291007.zip",
        "2.2.0%20GA1/liferay-ide-eclipse-macosx-x64-2.2.0-ga1-201410291007.tar.gz",
        "2.2.0%20GA1/liferay-ide-eclipse-linux-x64-2.2.0-ga1-201410291007.tar.gz",
        "2.2.0%20GA1/liferay-ide-eclipse-linux-x32-2.2.0-ga1-201410291007.tar.gz",
        "2.2.0%20GA1/updatesite/tools/plugins/com.liferay.ide.core_2.2.0.201410291007-ga1.jar",
        "2.2.0%20GA1/updatesite/tools/plugins/com.liferay.ide.alloy.core_2.2.0.201410291007-ga1.jar",
        "2.2.0%20GA1/updatesite/maven/plugins/com.liferay.ide.maven.core_2.2.0.201410291007-ga1.jar",

        "2.2.1%20GA2/liferay-ide-updatesite-2.2.1-ga2-201411200728.zip",
        "2.2.1%20GA2/liferay-ide-eclipse-windows-x64-2.2.1-ga2-201411200728.zip",
        "2.2.1%20GA2/liferay-ide-eclipse-windows-x32-2.2.1-ga2-201411200728.zip",
        "2.2.1%20GA2/liferay-ide-eclipse-macosx-x64-2.2.1-ga2-201411200728.tar.gz",
        "2.2.1%20GA2/liferay-ide-eclipse-linux-x64-2.2.1-ga2-201411200728.tar.gz",
        "2.2.1%20GA2/liferay-ide-eclipse-linux-x32-2.2.1-ga2-201411200728.tar.gz",
        "2.2.1%20GA2/updatesite/tools/plugins/com.liferay.ide.core_2.2.1.201411200728-ga2.jar",
        "2.2.1%20GA2/updatesite/tools/plugins/com.liferay.ide.alloy.core_2.2.1.201411200728-ga2.jar",
        "2.2.1%20GA2/updatesite/maven/plugins/com.liferay.ide.maven.core_2.2.1.201411200728-ga2.jar",

    };

    private final String jun2014 = "?start_date=2014-06-01&end_date=2014-06-30";
    private final String jul2014 = "?start_date=2014-07-01&end_date=2014-07-31";
    private final String aug2014 = "?start_date=2014-08-01&end_date=2014-08-31";
    private final String sep2014 = "?start_date=2014-09-01&end_date=2014-09-30";
    private final String oct2014 = "?start_date=2014-10-01&end_date=2014-10-31";
    private final String nov2014 = "?start_date=2014-11-01&end_date=2014-11-30";

    private final String fullrange = "?start_date=2010-02-01&end_date=2015-01-01";

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        System.out.println( new Main().getstats() );
    }

    public String getstats()
    {
        int jun2014count = count( jun2014 );
        int jul2014count = count( jul2014 );
        int aug2014count = count( aug2014 );
        int sep2014count = count( sep2014 );
        int oct2014count = count( oct2014 );
        int nov2014count = count( nov2014 );

        int full = count( fullrange );

        return
               "jun 2014 = " + jun2014count + "\n" +
               "jul 2014 = " + jul2014count + "\n" +
               "aug 2014 = " + aug2014count + "\n" +
               "sep 2014 = " + sep2014count + "\n" +
               "oct 2014 = " + oct2014count + "\n" +
               "nov 2014 = " + nov2014count + "\n" +
               "all time = " + full;
    }

    private int count( String daterange )
    {
        int retval = 0;

        for( String fileurl : URLS )
        {
            final HttpClient client = new HttpClient();

            final GetMethod get = new GetMethod( PREFIX + fileurl + SUFFIX + daterange );

            try
            {
                System.out.println(daterange);
                System.out.println(get.getURI());

                int result = client.executeMethod( get );

                if( result != HttpStatus.SC_OK )
                {
                    retval = -1;
                }
                else
                {
                    byte[] body = get.getResponseBody();
                    String response = new String( body );
                    JSONObject jsonObject = new JSONObject( response );

                    int total = jsonObject.getInt( "total" );

                    if( total < 0 )
                    {
                        retval = -1;
                        System.out.println("API failed");
                        System.exit(1);
                        break;
                    }
                    else
                    {
                        System.out.println( "got total = " + total + "\n");
                        retval += total;
                    }
                }
            }
            catch( Exception e )
            {
                e.printStackTrace();
                retval = -1;
            }
        }

        return retval;
    }

}
