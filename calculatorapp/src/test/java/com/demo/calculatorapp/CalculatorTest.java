package calculatorapp.src.test.java.com.demo.calculatorapp;

import org.junit.jupiter.api.Test;

import calculatorapp.src.main.java.com.demo.calculatorapp.Calculator;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator cal = new Calculator();

    // @Test
    // void testAdd() {
    //     assertEquals(15, cal.add(10,5));
    // }
     @Test
    void testAdd() {
        assertEquals(15, cal.add(10,5));
        System.out.println("failed");
    }

    @Test
    void testSub() {
        assertEquals(5, cal.sub(10,5));
    }
}