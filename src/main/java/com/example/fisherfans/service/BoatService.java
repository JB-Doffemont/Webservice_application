package com.example.fisherfans.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;

@Service
public interface BoatService {

    public List<Boat> getBoats();

    public Boat findBoatById(Long id);

    public Boat createBoat(BoatDto boatDTO);

    public Boat updateBoat(BoatDto boatDto, Long id);

    public void deleteBoat(Long id);

    public List<Boat> getBoatsByNumberOfBerths(Long numberOfBerths);

    public List<Boat> getBoatsByLocalisation(Double minLatitude, Double minLongitude, Double maxLatitude,
            Double maxLongitude);

    public Page<Boat> getBoatsByPage(int pageNumber, int pageSize);

}
