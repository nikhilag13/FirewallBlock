package test;

import me.nikhila.interview.Firewall;
import junit.framework.TestCase;
import org.junit.Test;

public class FirewallTest extends TestCase {

    private Firewall f;
    @Override
    protected void setUp() throws Exception{

        super.setUp();
        ClassLoader classLoader = getClass().getClassLoader();

        f = new Firewall(classLoader.getResource("rules.csv").getFile());
    }

    @Test
    public  void testFireWall(){

        assertTrue("Should Pass First Rule", f.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
        assertTrue("Should Pass Third Rule", f.accept_packet("inbound", "udp", 53, "192.168.2.1"));
        assertTrue("Should pass Second Rule", f.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));


        assertFalse("Should Fail", f.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
        assertFalse("Should Fail", f.accept_packet("inbound", "udp", 24, "52.12.48.92"));
    }

}
