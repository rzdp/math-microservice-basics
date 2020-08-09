package com.rzdp.mathoperationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MathOperationController {

    private static final Map<Integer, String> MATH_OPERATIONS = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MathOperationController.class);

    static {
        MATH_OPERATIONS.put(1, "+");
        MATH_OPERATIONS.put(2, "-");
        MATH_OPERATIONS.put(3, "*");
        MATH_OPERATIONS.put(4, "%");
    }

    @GetMapping("/operations/{id}")
    public String getOperations(@PathVariable("id") Integer operationId) {
        LOGGER.info("Getting Math Operation with ID '{}'", operationId);
        return MATH_OPERATIONS.get(operationId);
    }
}