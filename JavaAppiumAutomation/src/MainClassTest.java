import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetClassNumber(){
        MainClass number = new MainClass();
        Assert.assertTrue("Returned value is less or equal 45", number.getClassNumber() > 45);
    }
}
