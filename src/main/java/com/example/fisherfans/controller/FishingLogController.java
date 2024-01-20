package com.example.fisherfans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.fisherfans.dto.FishingLogDto;
import com.example.fisherfans.entity.FishingLog;
import com.example.fisherfans.service.FishingLogService;

@RestController
@CrossOrigin
@RequestMapping("/fishing-log")
public class FishingLogController {

    @Autowired
    FishingLogService fishingLogService;

    @GetMapping("")
    public List<FishingLog> getFishingLogs() {
        return fishingLogService.getFishingLogs();
    }

    @GetMapping("/{id}")
    public FishingLog getFishingLog(@PathVariable("id") Long id) {
        return fishingLogService.findFishingLogById(id);
    }

    @PostMapping("")
    public FishingLog createFishingLog(@RequestBody FishingLogDto fishingLogDto) {
        return fishingLogService.createFishingLog(fishingLogDto);
    }

    @PutMapping("/{id}")
    public FishingLog updateFishingLog(@RequestBody FishingLogDto fishingLogDto) {
        return fishingLogService.updateFishingLog(fishingLogDto);
    }

    @PatchMapping("/{id}")
    public FishingLog patchFishingLog(@RequestBody FishingLogDto fishingLogDto) {
        return fishingLogService.updateFishingLog(fishingLogDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFishingLog(@RequestParam Long id) {
        fishingLogService.deleteFishingLog(id);
    }

}
