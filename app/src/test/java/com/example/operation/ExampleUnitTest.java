package com.example.operation;

import com.example.apt_api.operation.ORouter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ORouter.init();
        ORouter.start("{\n" +
                "\"path\":\"test\",\n" +
                "\"message\":\"it is test message\"\n" +
                "}");


        assertEquals(4, 2 + 2);
    }


}