import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class SimpleSoapTest {

    @Test
    public void testSoapSample(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("194.77.4.94");
        assertEquals(geoIP.getCountryCode(),"DEU");
    }
}
