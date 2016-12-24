package servlet.cds;

import model.Cd;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.CdsService;

public class CdsHandlerTest {

    private CdsHandler cdsHandler;
    @Mock
    private CdsRequest cdsRequest;
    @Mock
    private CdsService cdsService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(cdsRequest.getYear()).thenReturn(1975);

        cdsHandler = new CdsHandler(cdsRequest, cdsService);
    }

    @Test
    public void createCdTest() {
        Cd cd = new Cd();
        cd.setYear(1975);
        Assert.assertEquals(cdsHandler.createCd().getYear(), cd.getYear());
    }

}
