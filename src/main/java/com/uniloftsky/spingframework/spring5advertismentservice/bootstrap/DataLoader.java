package com.uniloftsky.spingframework.spring5advertismentservice.bootstrap;

import com.uniloftsky.spingframework.spring5advertismentservice.model.*;
import com.uniloftsky.spingframework.spring5advertismentservice.repositories.*;
import com.uniloftsky.spingframework.spring5advertismentservice.services.RegionService;
import com.uniloftsky.spingframework.spring5advertismentservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final RegionService regionService;
    private final CityRepository cityRepository;
    private final AdvertisementRepository advertisementRepository;
    private final RoleRepository roleRepository;

    public DataLoader(UserService userService, CategoryRepository categoryRepository, RegionRepository regionRepository, RegionService regionService, CityRepository cityRepository, AdvertisementRepository advertisementRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.regionRepository = regionRepository;
        this.regionService = regionService;
        this.cityRepository = cityRepository;
        this.advertisementRepository = advertisementRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = loadUsers();
        List<Category> categories = loadCategories();
        List<Region> regions = loadRegions();
        regionRepository.saveAll(regions);
        List<City> cities = loadCities();
        cityRepository.saveAll(cities);
        categoryRepository.saveAll(categories);

        List<Advertisement> ads = new ArrayList<>();
        ads.add(new Advertisement("PHP Developer", cities.get(0), categories.get(0), "На роботу PHP Developer", "Писати код", null, "пропозиція наша наступна", LocalDate.now(), new BigDecimal("10000.0"), "8 годин", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Front-End Developer", cities.get(0), categories.get(0), "На роботу Front-End Developer", "Робити фронт енд", "кваліфікований", "пропозиція наша наступна", LocalDate.now(), new BigDecimal("20000.0"), "віддалена робота", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Back-End Developer", cities.get(0), categories.get(0), "На роботу Back-End Developer", null, "Кваліфікований", null, LocalDate.now(), null, null, Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Unity Game Developer", cities.get(1), categories.get(0), "На роботу Unity Developer", null, "кваліфікований", null, LocalDate.now(), null, null, Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("HTML, CSS Developer", cities.get(1), categories.get(0), "Front", null, "qual", "пропозиція наша наступна", LocalDate.now(), new BigDecimal("24000.0"), "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("QA Engineer", cities.get(2), categories.get(0), "На роботу QA Developer", null, null, null, LocalDate.now(), new BigDecimal("50000.0"), null, Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Java Developer", cities.get(2), categories.get(0), "На роботу Java Developer", "Робити роботу", null, null, LocalDate.now(), new BigDecimal("1000.0"), "job natural", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Android Developer", cities.get(3), categories.get(0), "На роботу Android Developer", "Працювати", "кваліфікований", null, LocalDate.now(), null, "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Laravel Developer", cities.get(3), categories.get(0), "На роботу Laravel Developer", "Писати щось", "кваліфікований", null, LocalDate.now(), null, "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Manager", cities.get(4), categories.get(7), "На роботу Manager", "Менеджувати", "кваліфікований", null, LocalDate.now(), new BigDecimal("500000.0"), "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Дизайнер", cities.get(4), categories.get(6), "На роботу Дизайнер", "Дизайнити", null, "offer", LocalDate.now(), new BigDecimal("1000000000.0"), "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("UX/UI Дизайнер", cities.get(5), categories.get(6), "На роботу UX/UI дизайнер", null, "qual", "пропозиція наша наступна", LocalDate.now(), null, "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Бухгалтер", cities.get(5), categories.get(7), "На роботу бухгалтер", null, "кваліфікований", "пропозиція наша наступна", LocalDate.now(), null, "повний робочий день", Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Водій", cities.get(6), categories.get(3), "На роботу водій", "Працювати", "кваліфікований", "пропозиція наша наступна", LocalDate.now(), null, null, Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Будівник", cities.get(6), categories.get(2), "На роботу будівник", "Працювати", "кваліфікований", "пропозиція наша наступна", LocalDate.now(), null, null, Status.ACTIVE, users.get(0)));
        ads.add(new Advertisement("Медсестра", cities.get(7), categories.get(4), "На роботу медсесетра", "Працювати", "кваліфікований", "пропозиція наша наступна", LocalDate.now(), null, null, Status.ACTIVE, users.get(0)));
        advertisementRepository.saveAll(ads);
    }

    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        roles.add(new Role("ADMIN"));
        roleRepository.saveAll(roles);
        User user = new User("user", "123456", "test@mail.com", "Google Inc.", "desc", "website", "location", "380977777777", "profile_images/ca7b612d-f45b-4aa5-9c6a-863f0fe28d2b-google.png");
        user.getRoles().add(roles.get(0));
        user.getRoles().add(roles.get(1));
        userService.save(user);
        users.add(user);
        User user1 = new User("user1", "123456", "test@mail.com", "Google Inc.", "desc", "website", "location", "380977777777", "profile_images/ca7b612d-f45b-4aa5-9c6a-863f0fe28d2b-google.png");
        user1.getRoles().add(roles.get(0));
        userService.save(user1);
        users.add(user1);

        return users;
    }

    private List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("IT"));
        categories.add(new Category("Логістика"));
        categories.add(new Category("Будівництво"));
        categories.add(new Category("Транспорт"));
        categories.add(new Category("Медицина"));
        categories.add(new Category("Сфера послуг"));
        categories.add(new Category("Дизайн"));
        categories.add(new Category("Фінанси"));
        return categories;
    }

    private List<Region> loadRegions() {
        List<Region> regions = new ArrayList<>();
        regions.add(new Region("Житомирська"));
        regions.add(new Region("Одеська"));
        regions.add(new Region("Вінницька"));
        regions.add(new Region("Київська"));
        regions.add(new Region("Львівська"));
        return regions;
    }

    private List<City> loadCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City("Бердичів", regionService.findById(1L)));
        cities.add(new City("Житомир", regionService.findById(1L)));
        cities.add(new City("Андрушівка", regionService.findById(1L)));
        cities.add(new City("Скраглівка", regionService.findById(1L)));
        cities.add(new City("Київ", regionService.findById(4L)));
        cities.add(new City("Обухів", regionService.findById(4L)));
        cities.add(new City("Бориспіль", regionService.findById(4L)));
        cities.add(new City("Буча", regionService.findById(4L)));
        cities.add(new City("Ірпінь", regionService.findById(4L)));
        cities.add(new City("Львів", regionService.findById(5L)));
        cities.add(new City("Дрогобич", regionService.findById(5L)));
        cities.add(new City("Моршин", regionService.findById(5L)));
        cities.add(new City("Яворів", regionService.findById(5L)));
        return cities;
    }

}
