import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {


    @Test
    public void testGetClassString(){
        MainClass stringFromMain = new MainClass();

        String str = stringFromMain.getClassString();
        String sub1 = "hello";
        String sub2 = "Hello";
        Assert.assertTrue(String.format("String '%s' doesn't contain '%s' or '%s'", str, sub1, sub2),
                str.contains(sub1) || str.contains(sub2));
    }
}

