package com.example.fisherfans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fisherfans.entity.FishingLog;

@Repository
public interface FishingLogRepository extends JpaRepository<FishingLog, Long>
{

}
