package com.example.calculator.service;

import com.example.calculator.dto.DoMathRequest;
import com.example.calculator.utils.InvalidOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MathServiceTest {

    @InjectMocks
    private MathService mathService;

    @Test
    public void doMathSuccess() throws InvalidOperationException {
        DoMathRequest data = new DoMathRequest(
                6,
                4,
                "+"
        );
        double result = mathService.doMath(data.getOperand1(), data.getOperand2(), data.getOperation());
        assertEquals(10.0, result);
    }

    @Test
    public void doMathSuccess2() throws InvalidOperationException {
        DoMathRequest data = new DoMathRequest(
                6,
                4,
                "log"
        );
        double result = mathService.doMath(data.getOperand1(), data.getOperand2(), data.getOperation());
        assertEquals(3.612359947967774, result);
    }

    @Test
    public void doMathInvalidOperation() throws InvalidOperationException {
        DoMathRequest data = new DoMathRequest(
                6,
                0,
                "/"
        );
        double result = mathService.doMath(data.getOperand1(), data.getOperand2(), data.getOperation());
        assertEquals( new InvalidOperationException("Cannot divide by 0"), result);
    }
}
