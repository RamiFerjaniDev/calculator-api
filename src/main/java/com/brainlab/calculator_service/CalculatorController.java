package com.brainlab.calculator_service;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for calculator operations.
 * 
 * Currently supports addition of comma-separated numbers via a GET endpoint.
 * Example: /calculator/add?operands=1,2
 * Response: { "sum": 3.0 }
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {


    /** 
    * Adds a list of numbers provided as a comma-separated string in the 'operands' query parameter.
    * 
    * @param operands a comma-separated list of numeric values (e.g., "1,2,3")
    * @return JSON object containing the sum: { "sum": total( 0.0 if empty operands) }
    * @throws 400 Bad Request if any value is invalid (e.g., non-numeric)
    */
    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestParam(required = false) String operands) {
        try {
        if (operands == null) 
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Missing 'operands' parameter."));
        if(operands.isEmpty())
            return ResponseEntity.ok(new SumResponse(0));
        double sum = Arrays.stream(operands.split(","))
                           .map(String::trim)
                           .mapToDouble(Double::parseDouble)
                           .sum();
            return ResponseEntity.ok(new SumResponse(sum));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Invalid input: all operands must be numbers."));
        }
    }

    // class to represent JSON response
   public class SumResponse {
    private final double sum;

    public SumResponse(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }
}
// class to represent JSON error response
public class ErrorResponse {
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
}
