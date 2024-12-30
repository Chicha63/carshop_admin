package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.enities.*;
import com.chicha.carshop_admin.data.repos.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private CharacteristicsRepository characteristicsRepository;
    @Autowired
    private PossbleColorRepository possbleColorRepository;
    @Autowired
    private ModelNameRepository modelNameRepository;
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private ColorService colorService;

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(int id) {
        return modelRepository.findById(id).orElse(null);
    }

    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    public Model updateModel(Model model) {
        return modelRepository.save(model);
    }

    public Characteristics getCharacteristicById(int id) {
        Good good = goodRepository.findById(id).orElse(null);
        Model model = good.getModel();
        return(model.getCharacteristics());
    }

    public List<Color> getPossibleColors(int id) {
        List<Color> colors = new ArrayList<>();
        for(PossibleColor c : possbleColorRepository.findAllByModelId(id)){
            colors.add(c.getColor());
        }
        return colors;
    }

    public void deleteModelById(int id) {
        modelRepository.deleteById(id);
    }

    public ModelName addModelName(ModelName name) {
        if (!modelNameRepository.existsByNameAndManufacturer(name.getName(), name.getManufacturer())) {
            return modelNameRepository.save(name);
        } else if (!name.getName().isEmpty() && !name.getName().isBlank() ) {
            return modelNameRepository.save(ModelName.builder().id(modelNameRepository.findByNameAndManufacturer(name.getName(), name.getManufacturer()).getId()).name(name.getName()).manufacturer(name.getManufacturer()).build());
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public List<Model> getModelsByName(String name) {
        return modelRepository.findByModelNameId(modelNameRepository.findByName(name).getId());
    }

    public List<ModelName> getModelNamesByManufacturer(int manufacturer) {
        return modelNameRepository.findByManufacturerId(manufacturer);
    }

    public String deleteModelName(int id) {
        modelNameRepository.deleteById(id);
        return "Done";
    }

    @Transactional
    public void deleteModelColor(int colorId, int modelId) {
        Color color = colorService.getColorById(colorId);
        Model model = modelRepository.findById(modelId).orElseThrow(() ->
                new IllegalArgumentException("Model not found with ID: " + modelId));
        possbleColorRepository.deleteByColorIdAndModelId(model, color);
    }
}
