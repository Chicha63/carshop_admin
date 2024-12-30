package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.enities.*;
import com.chicha.carshop_admin.data.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiscService {
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    CountryReposity countryReposity;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private BodyTypeRepository bodyTypeRepository;
    @Autowired
    private EnginePlacementRepository enginePlacementRepository;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer addManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    public Availability addAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public List<Country> getAllCountry() {
        return countryReposity.findAll();
    }

    public Country addCountry(Country country) {
        return countryReposity.save(country);
    }

    public Engine addEngine(Engine engine) {
        Engine found = engineRepository.findByNameAndVolume(engine.getName(), engine.getVolume());
        if(found != null) {
            return found;
        } else if (engine.getName().isBlank() || engine.getName().isEmpty()) {
            throw new IllegalArgumentException("Invalid engine name");
        }
        return engineRepository.save(engine);
    }

    public List<Engine> getAllEngines() {
        return engineRepository.findAll();
    }

    public List<BodyType> getAllBodyTypes() {
        return bodyTypeRepository.findAll();
    }

    public BodyType addBodyType(BodyType bodyType) {
        BodyType found = bodyTypeRepository.findByName(bodyType.getName());
        if(found != null) {
            return found;
        } else if (bodyType.getName().isEmpty() || bodyType.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid body type");
        }
        return bodyTypeRepository.save(bodyType);
    }

    public List<EnginePlacement> getAllEnginePlacements() {
        return enginePlacementRepository.findAll();
    }

    public EnginePlacement addEnginePlacement(EnginePlacement enginePlacement) {
        EnginePlacement found = enginePlacementRepository.findByName(enginePlacement.getName());
        if(found != null) {
            return found;
        } else if (enginePlacement.getName().isEmpty() || enginePlacement.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid engine placement");
        }
        return enginePlacementRepository.save(enginePlacement);
    }
}
