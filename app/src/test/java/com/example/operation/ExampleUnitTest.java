package com.example.operation;

import com.example.apt_api.operation.Operators;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Operators.init();
        Operators.start("{\n" +
                "\"path\":\"test\",\n" +
                "\"message\":\"it is test message\"\n" +
                "}");


        assertEquals(4, 2 + 2);
    }


}