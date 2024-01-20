package com.example.fisherfans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fisherfans.entity.Boat;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

    @Query("SELECT b FROM Boat b WHERE b.numberOfBerths = :numberOfBerths")
    List<Boat> findBoatsByNumberOfBerths(@Param("numberOfBerths") Long numberOfBerths);

    @Query("SELECT b FROM Boat b WHERE b.portLatitude > :minLatitude AND b.portLatitude < :maxLatitude AND b.portLongitude > :minLongitude AND b.portLongitude < :maxLongitude")
    List<Boat> findBoatsByLocalisation(@Param("minLatitude") Double minLatitude,
            @Param("minLongitude") Double minLongitude, @Param("maxLatitude") Double maxLatitude,
            @Param("maxLongitude") Double maxLongitude);

}
