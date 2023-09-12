package com.whtdo.pet.utils.etc;

import com.whtdo.pet.controllers.UserController;
import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.BrandRepository;
import com.whtdo.pet.repositories.ModelRepository;
import com.whtdo.pet.repositories.UserRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.exeptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Dictionary;
import java.util.Hashtable;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final UserController userController;
    private final Random random = new Random();
    public static Integer amountOfUsers = 40;
    public static Integer amountOfVehicles = 40;
    public Integer amountOfRelationships = 60;
    public List<String> vins = new ArrayList<>();
    public List<String> passportNumbers = new ArrayList<>();

    @Transactional
    public void run(ApplicationArguments args) throws NotFoundException {
        // brands & models
        Map<String, List<String>> vehicles = new HashMap<String, List<String>>();
        vehicles.put("Toyota", Arrays.asList("Land Cruiser", "Corolla"));
        vehicles.put("Lada", Arrays.asList("Granta", "Vesta", "Niva"));
        vehicles.put("BMW", Arrays.asList("M3", "X6"));
        vehicles.put("Audi", Arrays.asList("A3", "Q7"));
        vehicles.put("Changan", Arrays.asList("UNI-V", "UNI-K"));

        // names
        List<String> names = Arrays.asList("Roma", "Igor", "Oleg", "Andrey", "Damir", "Vasiliy", "Maxim",
                "Ivan", "Iliya");
        // surnames
        List<String> surnames = Arrays.asList("Ivanov", "Petrov", "Sidorov", "Melnikov", "Egorov", "Rybenkov",
                "Kovalchuk", "Li", "Samko", "Rakitin", "Merzlyakov", "Necheparenko", "Muhametov", "Bolshakov",
                "Lobanov", "Grigoryan", "Krohin", "Kornienko", "Rutov", "Menshikov", "Antonov");
        // patronymics
        List<String> patronymics = Arrays.asList("Ivanovich", "Ruslanovich", "Ilich", "Maximovich", "Aleksandovich",
                "Artemovich", "Romanovich", "Alekseevich", "Vyacheslavovich", "Valerievich", "Vladimirivich");

        // brands loading
        for (String brandName : vehicles.keySet()) {
            Brand brand = new Brand();
            brand.setName(brandName);
            brandRepository.save(brand);
        }

        // models loading
        for (String brandName : vehicles.keySet()) {
            Brand brand = brandRepository
                    .findByName(brandName)
                    .orElseThrow(() -> new NotFoundException("Производитель не найден"));
            for (String modelName :vehicles.get(brandName)) {
                Model model = new Model();
                model.setName(modelName);
                model.setBrand(brand);
                modelRepository.save(model);
            }
        }

        // vehicles loading
        for (int i = 0; i < amountOfVehicles; i++) {
            List<String> modelsList = vehicles
                    .get(List.copyOf(vehicles.keySet())
                    .get(random.nextInt(List.copyOf(vehicles.keySet()).size()))); // list of all models
            String modelName = modelsList.get(random.nextInt(modelsList.size())); // get the random name from it
            Model model = modelRepository
                    .findByName(modelName)
                    .orElseThrow(() -> new NotFoundException("Модель не найдена")); // find it in db

            String vin = generateCode("", 17); // generating of vin
            vins.add(vin); // saving it

            Vehicle vehicle = new Vehicle();
            vehicle.setVin(vin);
            vehicle.setModel(model);
            vehicleRepository.save(vehicle);
        }

        // users loading
        for (int i = 0; i < amountOfUsers; i++) {
            String passportNumber = generateCode("4620", 6); // generation of passport
            passportNumbers.add(passportNumber); // ans saving it

            User user = new User();
            user.setPassportNumber(passportNumber);
            user.setSurname(surnames.get(random.nextInt(surnames.size())));
            user.setName(names.get(random.nextInt(names.size())));
            user.setPatronymic(patronymics.get(random.nextInt(patronymics.size())));
            userRepository.save(user);
        }

        // relationships generating
        for (int i = 0; i < amountOfRelationships; i++) {
            userController.addVehicleToUser(
                    vins.get(random.nextInt(vins.size())),
                    passportNumbers.get(random.nextInt(passportNumbers.size()))
            );
        }
    }

    // generator of vin & passport
    public String generateCode(String start, int length) {
        String line = start;
        for (int i = 0; i < length; i++) {
            line = line.concat(String.valueOf(random.nextInt(10)));
        }
        return line;
    }

}
