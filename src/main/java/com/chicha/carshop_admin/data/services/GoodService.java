package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.DTO.CharacteristicDTO;
import com.chicha.carshop_admin.data.enities.*;
import com.chicha.carshop_admin.data.repos.CharacteristicsRepository;
import com.chicha.carshop_admin.data.repos.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private MiscService miscService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private CharacteristicsRepository characteristicsRepository;

    public Good assembleGood(Good good) {
        good.setPrice(
                good.getModel().getPrice().multiply(
                        good.getColor().getCoeficient()
                        ).subtract(good.getModel().getPrice())
                .add(
                        good.getModel().getPrice().multiply(
                                good.getCountry().getCoeficient()
                        ).subtract(good.getModel().getPrice())).add(good.getModel().getPrice()));
        return goodRepository.save(good);
    }

    public List<Good> findAll() {
        return goodRepository.findAll();
    }

    public Good findById(int id) {
        return goodRepository.findById(id).get();
    }

    public Characteristics addCharacteristics(Characteristics characteristics) {
        if (characteristicsRepository.existsWithSimilarFieldsExcludingId(
                characteristics.getEngine(),
                characteristics.getEnginePlacement(),
                characteristics.getBodyType(),
                characteristics.getPlacesCount(),
                characteristics.getDoorsCount()
        )){
            return characteristicsRepository.findByEngineAndEnginePlacementAndBodyTypeAndPlacesCountAndDoorsCount(characteristics.getEngine(),
                    characteristics.getEnginePlacement(),
                    characteristics.getBodyType(),
                    characteristics.getPlacesCount(),
                    characteristics.getDoorsCount());
        } else {
            return characteristicsRepository.save(characteristics);
        }

    }

    public void deleteGood(int id) {
        goodRepository.deleteById(id);
    }

    public List<Characteristics> getAllCharacteristics() {
        return characteristicsRepository.findAll();
    }
}
