package net.mamoe.exampleJava;


import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

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

    }

    @Test
    public void testIS() throws FileNotFoundException {
        URL resource = AppTestJava.class.getResource("/6ae5b66fddc451dacf74cd38bdfd5266d1163210.jpg");
        new FileInputStream(resource.getFile());
    }
}
