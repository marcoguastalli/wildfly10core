package utils;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class DateUtilsTest {

    @Test
    public void testGetDateFromTime() {
        long time = 145756440000L;
        Date result = DateUtils.getDateFromTime(time);
        Assert.assertEquals(result.getTime(), time);
    }
}
