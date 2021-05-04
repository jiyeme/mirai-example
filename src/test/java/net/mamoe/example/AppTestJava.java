package net.mamoe.example;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTestJava
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testEnv()
    {
        // QQID=1;QQPASS=2

        String qqid = System.getenv("QQID");
        String qqpass = System.getenv("QQPASS");

        Assert.assertEquals("1", qqid);
        Assert.assertEquals("2", qqpass);
    }
}
