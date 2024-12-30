package com.chicha.carshop_admin.data.services;

import com.chicha.carshop_admin.data.enities.Color;
import com.chicha.carshop_admin.data.repos.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color getColorByName(String name) {
        return colorRepository.findByName(name);
    }

    public Color getColorById(int id) {
        return colorRepository.findById(id).get();
    }
    public Color saveColor(Color color) {
        if (colorRepository.existsByName(color.getName()))
            throw new RuntimeException("Color already exists");
        return colorRepository.save(color);
    }
    public void deleteColor(Color color) {
        if (colorRepository.existsById(color.getId())) {
            colorRepository.deleteById(color.getId());
        } else {
            colorRepository.deleteByName(color.getName());
            return;
        }
        throw new RuntimeException("Color not found");
    }
    public Color updateColor(Color color) {
        Color updatedColor = getColorById(color.getId());
        updatedColor.setName(color.getName());
        return colorRepository.save(updatedColor);
    }


}
