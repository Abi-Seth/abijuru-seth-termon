package com.example.calculator.controller;

import com.example.calculator.dto.DoMathRequest;
import com.example.calculator.service.MathService;
import com.example.calculator.utils.APIResponse;
import com.example.calculator.utils.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MathController {
    @Autowired
    public static MathService mathService;

    public MathController(MathService mathService) {
        MathController.mathService = mathService;
    }

    @PostMapping("/doMath")
    public ResponseEntity<?> doMath(@RequestBody DoMathRequest body) throws InvalidOperationException {
        return ResponseEntity.ok(new APIResponse(mathService.doMath(body.getOperand1(), body.getOperand2(), body.getOperation())));
    }
}
