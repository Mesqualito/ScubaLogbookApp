package com.andreahowes.dive_db.presentation.api;

import com.andreahowes.dive_db.logic.dive.DiveCalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class DiveCalculatorController {

    @Resource
    private DiveCalculatorService diveCalculatorService;

    @GetMapping("/diveCalculator")
    public Double planDive(@RequestParam(value = "firstDiveDepth", defaultValue = "18") Double firstDiveDepth,
                           @RequestParam(value = "firstDiveTime", defaultValue = "18") Double firstDiveTime,
                           @RequestParam(value = "secondDiveDepth", defaultValue = "18") Double secondDiveDepth,
                           @RequestParam(value = "secondDiveTime", defaultValue = "18") Double secondDiveTime) {
        return diveCalculatorService.planDive(firstDiveDepth, firstDiveTime, secondDiveDepth, secondDiveTime);
    }
}