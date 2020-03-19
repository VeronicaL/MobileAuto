import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        MainClass func = new MainClass();
        Assert.assertTrue("Result of getLocalNumber() function is not 14!", func.getLocalNumber() == 14);
    }
}
