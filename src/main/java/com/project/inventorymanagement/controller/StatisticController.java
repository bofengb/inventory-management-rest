package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.StatisticDTO;
import com.project.inventorymanagement.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/statistic",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<StatisticDTO> getMonthlyStatistic() {
        return ResponseEntity.ok().body(statisticService.generateMonthlyStatistic());
    }
}
