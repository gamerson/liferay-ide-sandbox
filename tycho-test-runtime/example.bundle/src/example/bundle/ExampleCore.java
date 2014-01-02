package example.bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

public class ExampleCore extends Plugin
{

    // The shared instance
    private static ExampleCore plugin;

    // The plugin ID
    public static final String PLUGIN_ID = "example.bundle"; //$NON-NLS-1$

    /**
     * Returns the shared instance
     *
     * @return the shared instance
     */
    public static ExampleCore getDefault()
    {
        return plugin;
    }

    /**
     * The constructor
     */
    public ExampleCore()
    {
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext context ) throws Exception
    {
        super.start( context );
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception
    {
        plugin = null;
        super.stop( context );
    }

    public static String getReadmeTxt() throws IOException
    {
        final Bundle exampleBundle = getDefault().getBundle();

        final Enumeration<URL> readmeEntries = exampleBundle.findEntries( "/", "readme.txt", false );

        final InputStream in = readmeEntries.nextElement().openStream();

        return readStreamToString( in );
    }

    public static String readStreamToString( InputStream contents ) throws IOException
    {
        if( contents == null )
        {
            return null;
        }

        final char[] buffer = new char[0x10000];

        StringBuilder out = new StringBuilder();

        Reader in = new InputStreamReader( contents, "UTF-8" ); //$NON-NLS-1$

        int read;
        do
        {
            read = in.read( buffer, 0, buffer.length );

            if( read > 0 )
            {
                out.append( buffer, 0, read );
            }
        }
        while( read >= 0 );

        contents.close();

        return out.toString();
    }
}
