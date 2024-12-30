package com.chicha.carshop_admin.data.controllers;

import com.chicha.carshop_admin.data.DTO.CharacteristicDTO;
import com.chicha.carshop_admin.data.DTO.EmployeeDTO;
import com.chicha.carshop_admin.data.enities.*;
import com.chicha.carshop_admin.data.repos.ModelColorRepository;
import com.chicha.carshop_admin.data.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5000"})
@RequiredArgsConstructor
public class AdminPanelController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private ModelColorRepository modelColorRepository;
    @Autowired
    private MiscService miscService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DealService dealService;

    @PutMapping("/authed/employee")
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDTO employee) {
        try {
            return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/employees")
    public ResponseEntity<Object> getAllEmployees() {
        try {
            return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/employee")
    public ResponseEntity<String> deleteEmployee(@RequestBody EmployeeDTO employee) {
        try {
            employeeService.deleteEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/models")
    public ResponseEntity<Object> getAllModels() {
        try {
            return new ResponseEntity<>(modelService.getAllModels(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/model:{id}")
    public ResponseEntity<Object> getModel(@PathVariable int id) {
        try {
            return new ResponseEntity<>(modelService.getModelById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/models:{name}")
    public ResponseEntity<Object> getModelsByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(modelService.getModelsByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/model")
    public ResponseEntity<Object> addModel(@RequestBody Model model) {
        try {
            return new ResponseEntity<>(modelService.saveModel(model), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/modelname")
    public ResponseEntity<Object> addModelName(@RequestBody ModelName name) {
        try {
            return new ResponseEntity<>(modelService.addModelName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/modelname:{id}")
    public ResponseEntity<String> deleteModelName(@PathVariable int id) {
        try {
            return new ResponseEntity<>(modelService.deleteModelName(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/modelnames:manu/{manufacturer}")
    public ResponseEntity<Object> getModelNamesByManufacturer(@PathVariable int manufacturer) {
        try {
            return new ResponseEntity<>(modelService.getModelNamesByManufacturer(manufacturer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/model")
    public ResponseEntity<String> deleteModel(@RequestBody Model model) {
        try {
            modelService.deleteModelById(model.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/authed/model")
    public ResponseEntity<String> updateModel(@RequestBody Model model) {
        try {
            modelService.updateModel(model);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/colors")
    public ResponseEntity<Object> getAllColors() {
        try {
            return new ResponseEntity<>(colorService.getAllColors(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/color")
    public ResponseEntity<Object> addColor(@RequestBody Color color) {
        try {
            return new ResponseEntity<>(colorService.saveColor(color), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/authed/color")
    public ResponseEntity<Object> updateColor(@RequestBody Color color) {
        try {
            return new ResponseEntity<>(colorService.updateColor(color), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/color")
    public ResponseEntity<String> deleteColor(@RequestBody Color color) {
        try {
            colorService.deleteColor(color);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/model/{id}/color")
    public ResponseEntity<String> appendColor(@RequestBody Color color, @PathVariable int id) {
        try {
            Model model = modelService.getModelById(id);
            PossibleColor possibleColor = PossibleColor.builder()
                    .color(color)
                    .model(model)
                    .build();
            modelColorRepository.save(possibleColor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/modelcolors:{id}")
    public ResponseEntity<Object> getAllModelColors(@PathVariable int id) {
        try {
            return new ResponseEntity<>(modelService.getPossibleColors(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/modelcolors:{id}/{colorId}")
    public ResponseEntity<Object> deleteModelColors(@PathVariable int id, @PathVariable int colorId) {
        try {
            modelService.deleteModelColor(colorId, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/countries")
    public ResponseEntity<Object> getAllCountries() {
        try {
            return new ResponseEntity<>(miscService.getAllCountry(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/country")
    public ResponseEntity<Object> addCountry(@RequestBody Country country) {
        try {
            return new ResponseEntity<>(miscService.addCountry(country), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/manufacturers")
    public ResponseEntity<Object> getAllManufacturers() {
        try {
            return new ResponseEntity<>(miscService.getAllManufacturers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/manufacturer")
    public ResponseEntity<Object> addManufacturer(@RequestBody Manufacturer manufacturer) {
        try {
            return new ResponseEntity<>(miscService.addManufacturer(manufacturer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/availabilities")
    public ResponseEntity<Object> getAllAvailability() {
        try {
            return new ResponseEntity<>(miscService.getAllAvailability(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/availability")
    public ResponseEntity<Object> addAvailability(@RequestBody Availability availability) {
        try {
            return new ResponseEntity<>(miscService.addAvailability(availability), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/good")
    public ResponseEntity<Object> addGood(@RequestBody Good good) {
        try {
            return new ResponseEntity<>(goodService.assembleGood(good), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/good:{id}")
    public ResponseEntity<String> deleteGood(@PathVariable int id) {
        try {
            goodService.deleteGood(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/goods")
    public ResponseEntity<Object> getAllGoods() {
        try {
            return new ResponseEntity<>(goodService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/authed/good")
    public ResponseEntity<Object> updateGood(@RequestBody Good good) {
        try {
            return new ResponseEntity<>(goodService.assembleGood(good), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/good:{id}")
    public ResponseEntity<Object> getGoodById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(goodService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/bodytype")
    public ResponseEntity<Object> addBodyType(@RequestBody BodyType bodyType) {
        try {
            return new ResponseEntity<>(miscService.addBodyType(bodyType), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bodytypes")
    public ResponseEntity<Object> getAllBodyTypes() {
        try {
            return new ResponseEntity<>(miscService.getAllBodyTypes(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/engines")
    public ResponseEntity<Object> getAllEngines() {
        try {
            return new ResponseEntity<>(miscService.getAllEngines(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/engine")
    public ResponseEntity<Object> addEngine(@RequestBody Engine engine) {
        try {
            return new ResponseEntity<>(miscService.addEngine(engine), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/engineplacements")
    public ResponseEntity<Object> getAllEnginePlacements() {
        try {
            return new ResponseEntity<>(miscService.getAllEnginePlacements(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/engineplacement")
    public ResponseEntity<Object> addEnginePlacement(@RequestBody EnginePlacement enginePlacement) {
        try {
            return new ResponseEntity<>(miscService.addEnginePlacement(enginePlacement), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/characteristic")
    public ResponseEntity<Object> addCharacteristics(@RequestBody Characteristics characteristic) {
        try {
            return new ResponseEntity<>(goodService.addCharacteristics(characteristic), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/characteristics")
    public ResponseEntity<Object> getAllCharacteristics() {
        try {
            return new ResponseEntity<>(goodService.getAllCharacteristics(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/characteristic:{id}")
    public ResponseEntity<Object> getCharacteristicById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(modelService.getCharacteristicById(id), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/clients")
    public ResponseEntity<Object> getAllClients() {
        try {
            return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/client:id/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/client:email/{email}")
    public ResponseEntity<Object> getClientByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(clientService.findByEmail(email), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/client:passport/{passport}")
    public ResponseEntity<Object> getClientByPassport(@PathVariable String passport) {
        try {
            return new ResponseEntity<>(clientService.findByPassport(passport), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/client:phone/{phone}")
    public ResponseEntity<Object> getClientByPhone(@PathVariable String phone) {
        try {
            return new ResponseEntity<>(clientService.findByPhone(phone), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/client")
    public ResponseEntity<Object> getClientByName(@RequestParam String name) {
        try {
            return new ResponseEntity<>(clientService.findByName(name), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/authed/client:{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable int id) {
        try {
            clientService.deleteById(id);
            return new ResponseEntity<>("Done!",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/authed/deals")
    public ResponseEntity<Object> getAllDeals() {
        try {
            return new ResponseEntity<>(dealService.getAllDeals(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/canceldeal:{id}")
    public ResponseEntity<Object> cancelDeal(@PathVariable int id) {
        try {
            Status cancel = Status.builder().id(3).name("Canceled").build();
            dealService.changeStatus(id,cancel);
            Deal deal = dealService.getDeal(id);
            return new ResponseEntity<>(deal, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/getstats")
    public ResponseEntity<Object> getAllStats() {
        try {
            return new ResponseEntity<>(dealService.getDealsStats(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/finishdeal:{id}")
    public ResponseEntity<Object> completelDeal(@PathVariable int id) {
        try {
            Status complete = Status.builder().id(4).name("Complete").build();
            dealService.changeStatus(id,complete);
            Deal deal = dealService.getDeal(id);
            return new ResponseEntity<>(deal, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/clientDeals:{id}")
    public ResponseEntity<Object> getClientDeals(@PathVariable int id) {
        try {
            return new ResponseEntity<>(dealService.getClientDeals(id), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/employeeDeals:{id}")
    public ResponseEntity<Object> getEmployeeDeals(@PathVariable int id) {
        try {
            return new ResponseEntity<>(dealService.getEmployeeDeals(id), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/authed/deals:accepted")
    public ResponseEntity<Object> getAcceptedDeals() {
        try {
            return new ResponseEntity<>(dealService.getAcceptedDeals(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authed/changestatus:{id}")
    public ResponseEntity<Object> changeStatus(@PathVariable int id, @RequestBody Status status) {
        try {
            dealService.changeStatus(id, status);
            return new ResponseEntity<>("Done!",HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/authed/dealemployee:{dealId}")
    public ResponseEntity<Object> dealEmployee(@PathVariable int dealId) {
        try {
            Employee employee = employeeService.getEmployeeByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            dealService.changeEmployee(dealId, employee);
            return new ResponseEntity<>("Done!",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/authed/deals:pending")
    public ResponseEntity<Object> getPendingDeals() {
        try {
            return new ResponseEntity<>(dealService.getPendingDeals(), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/deal:{id}")
    public ResponseEntity<Object> getDeal(@PathVariable int id) {
        try {
            return new ResponseEntity<>(dealService.getDeal(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/authed/me")
    public ResponseEntity<Object> getMe() {
        try {
            return new ResponseEntity<> (employeeService.getEmployeeByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private static final String UPLOAD_DIR = "C:/Users/gupal/IdeaProjects/carshop_admin/img/";

    @PostMapping("/authed/{id}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            // Define the upload directory
            String uploadDirectory = "C:/Users/gupal/IdeaProjects/carshop_admin/img/";
            Path path = Paths.get(uploadDirectory + id + ".jpg"); // Save with product id as filename

            // Ensure the directory exists
            Files.createDirectories(path.getParent());

            // Save the image file
            file.transferTo(path);

            // Return a success message as JSON response
            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }
}
