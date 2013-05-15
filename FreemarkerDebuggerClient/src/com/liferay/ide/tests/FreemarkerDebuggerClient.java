package com.liferay.ide.tests;

import freemarker.debug.Breakpoint;
import freemarker.debug.DebugModel;
import freemarker.debug.DebuggedEnvironment;
import freemarker.debug.Debugger;
import freemarker.debug.DebuggerClient;
import freemarker.debug.DebuggerListener;
import freemarker.debug.EnvironmentSuspendedEvent;
import freemarker.template.TemplateModelException;

import java.net.Inet4Address;
import java.rmi.RemoteException;


public class FreemarkerDebuggerClient
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        DebuggerListener listener = new DebuggerListener()
        {
            public void environmentSuspended( EnvironmentSuspendedEvent e ) throws RemoteException
            {
                handleBreakpointHit( e );
            }
        };

        try
        {
            final Debugger client = DebuggerClient.getDebugger( Inet4Address.getByName( "localhost" ), 7600, "foo" );

            Breakpoint bp = new Breakpoint( "10153#10193#10712", 1 );
            client.addBreakpoint( bp );

            client.addDebuggerListener( listener );
            System.out.println(client.getBreakpoints());

            Thread t = new Thread(new Runnable()
            {

                public void run()
                {
                    for(;;)
                    {
                        try
                        {
                            System.out.println(client.getBreakpoints());
                            Thread.sleep(1000);
                        }
                        catch( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
        }
    }

    protected static void handleBreakpointHit( EnvironmentSuspendedEvent e )
    {
        int line = e.getLine();
        Object source = e.getSource();
        final DebuggedEnvironment env = e.getEnvironment();
        try
        {
            String[] keys = env.keys();
            DebugModel dataModel = env.get( "dataModel" );

            new Thread(new Runnable()
            {

                public void run()
                {
                    try
                    {
                        Thread.sleep( 1000 );
                    }
                    catch( InterruptedException e )
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try
                    {
                        env.resume();
                    }
                    catch( RemoteException e )
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        catch( TemplateModelException e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        catch( RemoteException e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

}
