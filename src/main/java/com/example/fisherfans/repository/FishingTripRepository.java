package com.example.fisherfans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fisherfans.entity.FishingTrip;

@Repository
public interface FishingTripRepository extends JpaRepository<FishingTrip,
Long> {

}
