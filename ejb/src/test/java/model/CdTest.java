package model;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CdTest {

    @Mock
    private Cd mockCd;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(mockCd.getId()).thenReturn(27);
    }

    @Test
    public void cdTest() {
        Cd cd = new Cd();
        cd.setId(27);
        Assert.assertEquals(cd.getId(), mockCd.getId());
    }

}
