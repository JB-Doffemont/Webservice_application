package com.example.fisherfans.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.fisherfans.dto.BoatDto;
import com.example.fisherfans.entity.Boat;

@Service
public interface BoatService {

    public List<BoatDto> getBoats();

    public BoatDto findBoatById(Long id);

    public BoatDto createBoat(BoatDto boatDTO);

    public BoatDto updateBoat(BoatDto boatDto, Long id);

    public void deleteBoat(Long id);

    public List<BoatDto> getBoatsByNumberOfBerths(Long numberOfBerths);

    public List<BoatDto> getBoatsByLocalisation(Double minLatitude, Double minLongitude, Double maxLatitude,
            Double maxLongitude);

    public Page<BoatDto> getBoatsByPage(int pageNumber, int pageSize);

}
