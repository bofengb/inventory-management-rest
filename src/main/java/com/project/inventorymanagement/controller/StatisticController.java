package com.project.inventorymanagement.controller;

import com.project.inventorymanagement.dto.StatisticDTO;
import com.project.inventorymanagement.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Statistic", description = "Operations related to monthly statistics")
public class StatisticController {

    private final StatisticService statisticService;

    @Operation(
            summary = "Get monthly statistic",
            description = "Generate and retrieve monthly statistics including sales, purchases, expenses, customer growth, and pending amounts."
    )
    @GetMapping
    public ResponseEntity<StatisticDTO> getMonthlyStatistic() {
        return ResponseEntity.ok().body(statisticService.generateMonthlyStatistic());
    }

}
