package com.example.fisherfans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.FishingLogDto;
import com.example.fisherfans.entity.FishingLog;
import com.example.fisherfans.mapper.FishingLogMapper;
import com.example.fisherfans.repository.FishingLogRepository;

@Service
public class FishingLogService {

    @Autowired
    FishingLogRepository fishingLogRepository;

    FishingLogMapper fishingLogMapper;

    public List<FishingLog> getFishingLogs() {
        return fishingLogRepository.findAll();
    }

    public FishingLog findFishingLogById(Long id) {
        return fishingLogRepository.findById(id).orElse(null);
    }

    public FishingLog createFishingLog(FishingLogDto fishingLogDTO) {
        FishingLog fishingLog = FishingLogMapper.INSTANCE.dtoToEntity(fishingLogDTO);
        return fishingLogRepository.save(fishingLog);
    }

    public FishingLog updateFishingLog(FishingLogDto fishingLogDto) {
        FishingLog fishingLog = FishingLogMapper.INSTANCE.dtoToEntity(fishingLogDto);
        return fishingLogRepository.save(fishingLog);
    }

    public void deleteFishingLog(Long id) {
        fishingLogRepository.deleteById(id);
    }

}
