package com.rzdp.mathcomputationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class MathComputationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathComputationController.class);
    private final RestTemplate restTemplate;

    @Autowired
    public MathComputationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/operations/{id}/computation")
    public BigDecimal getComputedValue(@PathVariable("id") Integer operationId,
                                       @RequestParam("num1") BigDecimal num1,
                                       @RequestParam("num2") BigDecimal num2) {
        LOGGER.info("Getting Math Operation using Operation ID '{}'", operationId);
        String operation = restTemplate
                .getForObject("http://localhost:8080/api/operations/{id}", String.class, operationId);

        LOGGER.info("Operation '{}' fetched from Math Computation Service", operation);

        LOGGER.info("Computing '{} {} {}' ...", num1, operation, num2);
        BigDecimal total;

        if (operation.equals("+"))
            total = num1.add(num2);
        else if (operation.equals("-"))
            total = num1.subtract(num2);
        else if (operation.equals("*"))
            total = num1.multiply(num2);
        else if (operation.equals("%"))
            total = num1.divide(num2);
        else
            throw new IllegalArgumentException("Invalid operation '" + operation + "'");

        LOGGER.info("Numbers computed successfully!");
        LOGGER.info("Result '{}'", total);
        return total;
    }
}
