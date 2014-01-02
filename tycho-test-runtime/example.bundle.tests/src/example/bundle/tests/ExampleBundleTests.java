
package example.bundle.tests;

import static org.junit.Assert.assertEquals;
import example.bundle.ExampleCore;

import org.eclipse.core.runtime.Platform;
import org.junit.Test;

/**
 * @author Gregory Amerson
 */
public class ExampleBundleTests
{

    @Test
    public void testFragments() throws Exception
    {
        String readmeTxt = ExampleCore.getReadmeTxt();

        assertEquals( "Can you read me on " + Platform.getOS() + "?", readmeTxt );
    }

}
