package com.rooshdashboard.rooshdashboard.configuration.db;

import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountRequest;
import com.rooshdashboard.rooshdashboard.persistance.*;
import com.rooshdashboard.rooshdashboard.persistance.entity.*;
import lombok.AllArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@AllArgsConstructor

public class DatabaseDataInitializer {
    private AccountRepository accountRepository;
    private BookingRepository bookingRepository;
    private ParkingGarageRepository parkingGarageRepository;
    private CarRepository carRepository;
    private ServiceRepository serviceRepository;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        CustomerEntity savedCustomer = CustomerEntity.builder().name("Michael Brown").phoneNumber("555-111-2222").email("michael@example.com").build();
        CarEntity savedCar = CarEntity.builder().id(1L).licensePlate("XYZ123").brand("Toyota").model("Camry").electric(false).customer(savedCustomer).build();
        RoleEntity savedRole = RoleEntity.builder().roleName("f").build();
        AccountEntity savedAccount = AccountEntity.builder().email("jane@example.com").password("pass456").name("Jane").role(savedRole).build();
        ServiceEntity savedService = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity savedParkingGarageUtility = ParkingGarageUtilityEntity.builder().parkingSpaces(1L).parkingSpacesElectric(1L).electricChargePoint(false).floors(1L).toilet(false).build();
        ParkingGarageEntity savedParkingGarage = ParkingGarageEntity.builder().location("123 Main St").airport("PQR Airport").name("Cedar Road Parking").phoneNumber("123").travelDistance(12L).parkingGarageUtility(savedParkingGarageUtility).travelTime(12L).build();
        if (userRepository.count() == 0)
        {
            insertAdminUser();
        }
        if (bookingRepository.count() == 0) {
            customerRepository.save(savedCustomer);
            carRepository.save(savedCar);
            parkingGarageRepository.save(savedParkingGarage);
            accountRepository.save(savedAccount);
            serviceRepository.save(savedService);
            bookingRepository.save(BookingEntity.builder().customer(savedCustomer).car(savedCar).garage(savedParkingGarage).service(savedService).endDate(LocalDateTime.now().plusDays(1L)).startDate(LocalDateTime.now()).flightNumberArrival(1L).flightNumberDeparture(1L).build());
        }

        CustomerEntity customer1 = CustomerEntity.builder().name("Alice Johnson").phoneNumber("555-123-4567").email("alice@example.com").build();
        CarEntity car1 = CarEntity.builder().id(2L).licensePlate("ABC456").brand("Honda").model("Civic").electric(false).customer(customer1).build();
        RoleEntity role1 = RoleEntity.builder().roleName("User").build();
        AccountEntity account1 = AccountEntity.builder().email("alice@example.com").password("pass123").name("Alice").role(role1).build();
        ServiceEntity service1 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity garageUtility1 = ParkingGarageUtilityEntity.builder().parkingSpaces(50L).parkingSpacesElectric(10L).electricChargePoint(true).floors(4L).toilet(true).build();
        ParkingGarageEntity parkingGarage1 = ParkingGarageEntity.builder().location("456 Elm St").airport("XYZ International Airport").name("Elm Street Parking").phoneNumber("555-789-1234").travelDistance(5L).parkingGarageUtility(garageUtility1).travelTime(15L).build();
        if (bookingRepository.count() == 1) {
            customerRepository.save(customer1);
            carRepository.save(car1);
            parkingGarageRepository.save(parkingGarage1);
            accountRepository.save(account1);
            serviceRepository.save(service1);
            bookingRepository.save(BookingEntity.builder().customer(customer1).car(car1).garage(parkingGarage1).service(service1).endDate(LocalDateTime.now().plusDays(2L)).startDate(LocalDateTime.now().plusDays(1L)).flightNumberArrival(12345L).flightNumberDeparture(54321L).build());
        }

        CustomerEntity customer2 = CustomerEntity.builder().name("Bob Smith").phoneNumber("555-987-6543").email("bob@example.com").build();
        CarEntity car2 = CarEntity.builder().id(3L).licensePlate("XYZ789").brand("Ford").model("Escape").electric(true).customer(customer2).build();
        RoleEntity role2 = RoleEntity.builder().roleName("Admin").build();
        AccountEntity account2 = AccountEntity.builder().email("bob@example.com").password("pass789").name("Bob").role(role2).build();
        ServiceEntity service2 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity garageUtility2 = ParkingGarageUtilityEntity.builder().parkingSpaces(100L).parkingSpacesElectric(20L).electricChargePoint(true).floors(6L).toilet(true).build();
        ParkingGarageEntity parkingGarage2 = ParkingGarageEntity.builder().location("789 Oak Ave").airport("ABC Airport").name("Oak Avenue Parking").phoneNumber("555-222-3333").travelDistance(10L).parkingGarageUtility(garageUtility2).travelTime(20L).build();
        if (bookingRepository.count() == 2) {
            customerRepository.save(customer2);
            carRepository.save(car2);
            parkingGarageRepository.save(parkingGarage2);
            accountRepository.save(account2);
            serviceRepository.save(service2);
            bookingRepository.save(BookingEntity.builder().customer(customer2).car(car2).garage(parkingGarage2).service(service2).endDate(LocalDateTime.now().plusDays(3L)).startDate(LocalDateTime.now().plusDays(2L)).flightNumberArrival(67890L).flightNumberDeparture(98765L).build());
        }

        CustomerEntity customer3 = CustomerEntity.builder().name("David Miller").phoneNumber("555-444-1234").email("david@example.com").build();
        CarEntity car3 = CarEntity.builder().id(4L).licensePlate("DEF789").brand("Chevrolet").model("Malibu").electric(false).customer(customer3).build();
        RoleEntity role3 = RoleEntity.builder().roleName("User").build();
        AccountEntity account3 = AccountEntity.builder().email("david@example.com").password("pass345").name("David").role(role3).build();
        ServiceEntity service3 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity garageUtility3 = ParkingGarageUtilityEntity.builder().parkingSpaces(75L).parkingSpacesElectric(5L).electricChargePoint(true).floors(3L).toilet(true).build();
        ParkingGarageEntity parkingGarage3 = ParkingGarageEntity.builder().location("789 Maple Ln").airport("MNO International Airport").name("Maple Lane Parking").phoneNumber("555-987-6543").travelDistance(7L).parkingGarageUtility(garageUtility3).travelTime(18L).build();
        if (bookingRepository.count() == 3) {
            customerRepository.save(customer3);
            carRepository.save(car3);
            parkingGarageRepository.save(parkingGarage3);
            accountRepository.save(account3);
            serviceRepository.save(service3);
            bookingRepository.save(BookingEntity.builder().customer(customer3).car(car3).garage(parkingGarage3).service(service3).endDate(LocalDateTime.now().plusDays(4L)).startDate(LocalDateTime.now().plusDays(3L)).flightNumberArrival(23456L).flightNumberDeparture(65432L).build());
        }


        CustomerEntity customer4 = CustomerEntity.builder().name("Emily Davis").phoneNumber("555-876-5432").email("emily@example.com").build();
        CarEntity car4 = CarEntity.builder().id(5L).licensePlate("GHI123").brand("Nissan").model("Altima").electric(false).customer(customer4).build();
        RoleEntity role4 = RoleEntity.builder().roleName("User").build();
        AccountEntity account4 = AccountEntity.builder().email("emily@example.com").password("pass567").name("Emily").role(role4).build();
        ServiceEntity service4 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity garageUtility4 = ParkingGarageUtilityEntity.builder().parkingSpaces(60L).parkingSpacesElectric(10L).electricChargePoint(true).floors(4L).toilet(true).build();
        ParkingGarageEntity parkingGarage4 = ParkingGarageEntity.builder().location("456 Cedar Rd").airport("PQR Airport").name("Cedar Road Parking").phoneNumber("555-333-2222").travelDistance(9L).parkingGarageUtility(garageUtility4).travelTime(22L).build();
        if (bookingRepository.count() == 4) {
            customerRepository.save(customer4);
            carRepository.save(car4);
            parkingGarageRepository.save(parkingGarage4);
            accountRepository.save(account4);
            serviceRepository.save(service4);
            bookingRepository.save(BookingEntity.builder().customer(customer4).car(car4).garage(parkingGarage4).service(service4).endDate(LocalDateTime.now().plusDays(5L)).startDate(LocalDateTime.now().plusDays(4L)).flightNumberArrival(34567L).flightNumberDeparture(76543L).build());
        }


        CustomerEntity customer5 = CustomerEntity.builder().name("Grace Wilson").phoneNumber("555-222-1111").email("grace@example.com").build();
        CarEntity car5 = CarEntity.builder().id(6L).licensePlate("JKL456").brand("Hyundai").model("Sonata").electric(false).customer(customer5).build();
        RoleEntity role5 = RoleEntity.builder().roleName("User").build();
        AccountEntity account5 = AccountEntity.builder().email("grace@example.com").password("pass678").name("Grace").role(role5).build();
        ServiceEntity service5 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity garageUtility5 = ParkingGarageUtilityEntity.builder().parkingSpaces(80L).parkingSpacesElectric(15L).electricChargePoint(true).floors(5L).toilet(true).build();
        ParkingGarageEntity parkingGarage5 = ParkingGarageEntity.builder().location("123 Pine Ave").airport("STU International Airport").name("Pine Avenue Parking").phoneNumber("555-111-9999").travelDistance(8L).parkingGarageUtility(garageUtility5).travelTime(20L).build();
        if (bookingRepository.count() == 5) {
            customerRepository.save(customer5);
            carRepository.save(car5);
            parkingGarageRepository.save(parkingGarage5);
            accountRepository.save(account5);
            serviceRepository.save(service5);
            bookingRepository.save(BookingEntity.builder().customer(customer5).car(car5).garage(parkingGarage5).service(service5).endDate(LocalDateTime.now().plusDays(6L)).startDate(LocalDateTime.now().plusDays(5L)).flightNumberArrival(45678L).flightNumberDeparture(87654L).build());
        }


        if (bookingRepository.count() == 6) {
            CustomerEntity customer6 = CustomerEntity.builder().name("Michael Brown").phoneNumber("555-111-2222").email("michael@example.com").build();
            CarEntity car6 = CarEntity.builder().id(7L).licensePlate("MNO789").brand("Tesla").model("Model 3").electric(true).customer(customer6).build();
            RoleEntity role6 = RoleEntity.builder().roleName("User").build();
            AccountEntity account6 = AccountEntity.builder().email("michael@example.com").password("pass789").name("Michael").role(role6).build();
            ServiceEntity service6 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
            ParkingGarageUtilityEntity garageUtility6 = ParkingGarageUtilityEntity.builder().parkingSpaces(50L).parkingSpacesElectric(10L).electricChargePoint(true).floors(4L).toilet(true).build();
            ParkingGarageEntity parkingGarage6 = ParkingGarageEntity.builder().location("789 Oak Ave").airport("ABC Airport").name("Oak Avenue Parking").phoneNumber("555-222-3333").travelDistance(10L).parkingGarageUtility(garageUtility6).travelTime(20L).build();

            customerRepository.save(customer6);
            carRepository.save(car6);
            parkingGarageRepository.save(parkingGarage6);
            accountRepository.save(account6);
            serviceRepository.save(service6);
            bookingRepository.save(BookingEntity.builder().customer(customer6).car(car6).garage(parkingGarage6).service(service6).endDate(LocalDateTime.now().plusDays(7L)).startDate(LocalDateTime.now().plusDays(6L)).flightNumberArrival(123456L).flightNumberDeparture(654321L).build());
        }

        if (bookingRepository.count() == 7) {
            CustomerEntity customer7 = CustomerEntity.builder().name("Alice Johnson").phoneNumber("555-123-4567").email("alice@example.com").build();
            CarEntity car7 = CarEntity.builder().id(8L).licensePlate("PQR123").brand("Chevrolet").model("Equinox").electric(false).customer(customer7).build();
            RoleEntity role7 = RoleEntity.builder().roleName("Admin").build();
            AccountEntity account7 = AccountEntity.builder().email("alice@example.com").password("pass456").name("Alice").role(role7).build();
            ServiceEntity service7 = ServiceEntity.builder().serviceType(ServiceType.Shuttle).build();
            ParkingGarageUtilityEntity garageUtility7 = ParkingGarageUtilityEntity.builder().parkingSpaces(75L).parkingSpacesElectric(5L).electricChargePoint(true).floors(3L).toilet(true).build();
            ParkingGarageEntity parkingGarage7 = ParkingGarageEntity.builder().location("789 Maple Ln").airport("MNO International Airport").name("Maple Lane Parking").phoneNumber("555-987-6543").travelDistance(7L).parkingGarageUtility(garageUtility7).travelTime(18L).build();

            customerRepository.save(customer7);
            carRepository.save(car7);
            parkingGarageRepository.save(parkingGarage7);
            accountRepository.save(account7);
            serviceRepository.save(service7);
            bookingRepository.save(BookingEntity.builder().customer(customer7).car(car7).garage(parkingGarage7).service(service7).endDate(LocalDateTime.now().plusDays(8L)).startDate(LocalDateTime.now().plusDays(7L)).flightNumberArrival(234567L).flightNumberDeparture(765432L).build());
        }


        if (bookingRepository.count() == 8) {
            CustomerEntity customer8 = CustomerEntity.builder().name("Bob Smith").phoneNumber("555-987-6543").email("bob@example.com").build();
            CarEntity car8 = CarEntity.builder().id(9L).licensePlate("XYZ456").brand("Ford").model("Explorer").electric(true).customer(customer8).build();
            RoleEntity role8 = RoleEntity.builder().roleName("User").build();
            AccountEntity account8 = AccountEntity.builder().email("bob@example.com").password("pass123").name("Bob").role(role8).build();
            ServiceEntity service8 = ServiceEntity.builder().serviceType(ServiceType.Shuttle).build();
            ParkingGarageUtilityEntity garageUtility8 = ParkingGarageUtilityEntity.builder().parkingSpaces(60L).parkingSpacesElectric(10L).electricChargePoint(true).floors(4L).toilet(true).build();
            ParkingGarageEntity parkingGarage8 = ParkingGarageEntity.builder().location("456 Cedar Rd").airport("PQR Airport").name("Cedar Road Parking").phoneNumber("555-333-2222").travelDistance(9L).parkingGarageUtility(garageUtility8).travelTime(22L).build();

            customerRepository.save(customer8);
            carRepository.save(car8);
            parkingGarageRepository.save(parkingGarage8);
            accountRepository.save(account8);
            serviceRepository.save(service8);
            bookingRepository.save(BookingEntity.builder().customer(customer8).car(car8).garage(parkingGarage8).service(service8).endDate(LocalDateTime.now().plusDays(9L)).startDate(LocalDateTime.now().plusDays(8L)).flightNumberArrival(345678L).flightNumberDeparture(876543L).build());
        }


        if (bookingRepository.count() == 9) {
            CustomerEntity customer9 = CustomerEntity.builder().name("David Miller").phoneNumber("555-444-1234").email("david@example.com").build();
            CarEntity car9 = CarEntity.builder().id(10L).licensePlate("ABC789").brand("Toyota").model("Rav4").electric(false).customer(customer9).build();
            RoleEntity role9 = RoleEntity.builder().roleName("User").build();
            AccountEntity account9 = AccountEntity.builder().email("david@example.com").password("pass789").name("David").role(role9).build();
            ServiceEntity service9 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
            ParkingGarageUtilityEntity garageUtility9 = ParkingGarageUtilityEntity.builder().parkingSpaces(80L).parkingSpacesElectric(15L).electricChargePoint(true).floors(5L).toilet(true).build();
            ParkingGarageEntity parkingGarage9 = ParkingGarageEntity.builder().location("123 Pine Ave").airport("STU International Airport").name("Pine Avenue Parking").phoneNumber("555-111-9999").travelDistance(8L).parkingGarageUtility(garageUtility9).travelTime(20L).build();

            customerRepository.save(customer9);
            carRepository.save(car9);
            parkingGarageRepository.save(parkingGarage9);
            accountRepository.save(account9);
            serviceRepository.save(service9);
            bookingRepository.save(BookingEntity.builder().customer(customer9).car(car9).garage(parkingGarage9).service(service9).endDate(LocalDateTime.now().plusDays(10L)).startDate(LocalDateTime.now().plusDays(9L)).flightNumberArrival(456789L).flightNumberDeparture(987654L).build());
        }

        if (bookingRepository.count() == 10) {
            CustomerEntity customer10 = CustomerEntity.builder().name("Emily Davis").phoneNumber("555-876-5432").email("emily@example.com").build();
            CarEntity car10 = CarEntity.builder().id(11L).licensePlate("GHI456").brand("Honda").model("Accord").electric(false).customer(customer10).build();
            RoleEntity role10 = RoleEntity.builder().roleName("Admin").build();
            AccountEntity account10 = AccountEntity.builder().email("emily@example.com").password("pass567").name("Emily").role(role10).build();
            ServiceEntity service10 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
            ParkingGarageUtilityEntity garageUtility10 = ParkingGarageUtilityEntity.builder().parkingSpaces(70L).parkingSpacesElectric(8L).electricChargePoint(true).floors(3L).toilet(true).build();
            ParkingGarageEntity parkingGarage10 = ParkingGarageEntity.builder().location("789 Oak St").airport("XYZ Airport").name("Oak Street Parking").phoneNumber("555-333-4444").travelDistance(10L).parkingGarageUtility(garageUtility10).travelTime(20L).build();

            customerRepository.save(customer10);
            carRepository.save(car10);
            parkingGarageRepository.save(parkingGarage10);
            accountRepository.save(account10);
            serviceRepository.save(service10);
            bookingRepository.save(BookingEntity.builder().customer(customer10).car(car10).garage(parkingGarage10).service(service10).endDate(LocalDateTime.now().plusDays(11L)).startDate(LocalDateTime.now().plusDays(10L)).flightNumberArrival(567890L).flightNumberDeparture(109876L).build());
        }

        if (bookingRepository.count() == 11) {
            CustomerEntity customer11 = CustomerEntity.builder().name("Grace Wilson").phoneNumber("555-222-1111").email("grace@example.com").build();
            CarEntity car11 = CarEntity.builder().id(12L).licensePlate("JKL789").brand("Subaru").model("Forester").electric(false).customer(customer11).build();
            RoleEntity role11 = RoleEntity.builder().roleName("User").build();
            AccountEntity account11 = AccountEntity.builder().email("grace@example.com").password("pass678").name("Grace").role(role11).build();
            ServiceEntity service11 = ServiceEntity.builder().serviceType(ServiceType.Shuttle).build();
            ParkingGarageUtilityEntity garageUtility11 = ParkingGarageUtilityEntity.builder().parkingSpaces(90L).parkingSpacesElectric(12L).electricChargePoint(true).floors(4L).toilet(true).build();
            ParkingGarageEntity parkingGarage11 = ParkingGarageEntity.builder().location("123 Oak Ln").airport("MNO Airport").name("Oak Lane Parking").phoneNumber("555-444-5555").travelDistance(12L).parkingGarageUtility(garageUtility11).travelTime(25L).build();

            customerRepository.save(customer11);
            carRepository.save(car11);
            parkingGarageRepository.save(parkingGarage11);
            accountRepository.save(account11);
            serviceRepository.save(service11);
            bookingRepository.save(BookingEntity.builder().customer(customer11).car(car11).garage(parkingGarage11).service(service11).endDate(LocalDateTime.now().plusDays(12L)).startDate(LocalDateTime.now().plusDays(11L)).flightNumberArrival(678901L).flightNumberDeparture(210987L).build());
        }

        if (bookingRepository.count() == 12) {
            CustomerEntity customer12 = CustomerEntity.builder().name("Michael Brown").phoneNumber("555-111-2222").email("michael@example.com").build();
            CarEntity car12 = CarEntity.builder().id(13L).licensePlate("MNO123").brand("Tesla").model("Model 3").electric(true).customer(customer12).build();
            RoleEntity role12 = RoleEntity.builder().roleName("Admin").build();
            AccountEntity account12 = AccountEntity.builder().email("michael@example.com").password("pass789").name("Michael").role(role12).build();
            ServiceEntity service12 = ServiceEntity.builder().serviceType(ServiceType.Shuttle).build();
            ParkingGarageUtilityEntity garageUtility12 = ParkingGarageUtilityEntity.builder().parkingSpaces(110L).parkingSpacesElectric(25L).electricChargePoint(true).floors(7L).toilet(true).build();
            ParkingGarageEntity parkingGarage12 = ParkingGarageEntity.builder().location("789 Maple St").airport("PQR Airport").name("Maple Street Parking").phoneNumber("555-777-8888").travelDistance(15L).parkingGarageUtility(garageUtility12).travelTime(30L).build();

            customerRepository.save(customer12);
            carRepository.save(car12);
            parkingGarageRepository.save(parkingGarage12);
            accountRepository.save(account12);
            serviceRepository.save(service12);
            bookingRepository.save(BookingEntity.builder().customer(customer12).car(car12).garage(parkingGarage12).service(service12).endDate(LocalDateTime.now().plusDays(13L)).startDate(LocalDateTime.now().plusDays(12L)).flightNumberArrival(789012L).flightNumberDeparture(321098L).build());
        }

        if (bookingRepository.count() == 13) {
            CustomerEntity customer14 = CustomerEntity.builder().name("Bob Smith").phoneNumber("555-987-6543").email("bob@example.com").build();
            CarEntity car14 = CarEntity.builder().id(15L).licensePlate("BOB789").brand("Chevrolet").model("Equinox").electric(false).customer(customer14).build();
            RoleEntity role14 = RoleEntity.builder().roleName("User").build();
            AccountEntity account14 = AccountEntity.builder().email("bob@example.com").password("pass234").name("Bob").role(role14).build();
            ServiceEntity service14 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
            ParkingGarageUtilityEntity garageUtility14 = ParkingGarageUtilityEntity.builder().parkingSpaces(80L).parkingSpacesElectric(10L).electricChargePoint(true).floors(5L).toilet(true).build();
            ParkingGarageEntity parkingGarage14 = ParkingGarageEntity.builder().location("789 Cedar Ave").airport("PQR Airport").name("Cedar Avenue Parking").phoneNumber("555-444-3333").travelDistance(12L).parkingGarageUtility(garageUtility14).travelTime(22L).build();

            customerRepository.save(customer14);
            carRepository.save(car14);
            parkingGarageRepository.save(parkingGarage14);
            accountRepository.save(account14);
            serviceRepository.save(service14);
            bookingRepository.save(BookingEntity.builder().customer(customer14).car(car14).garage(parkingGarage14).service(service14).endDate(LocalDateTime.now().plusDays(15L)).startDate(LocalDateTime.now().plusDays(14L)).flightNumberArrival(901234L).flightNumberDeparture(543210L).build());
        }


        if (bookingRepository.count() == 14) {
            CustomerEntity customer15 = CustomerEntity.builder().name("David Miller").phoneNumber("555-444-1234").email("david@example.com").build();
            CarEntity car15 = CarEntity.builder().id(16L).licensePlate("DAV123").brand("Volkswagen").model("Jetta").electric(false).customer(customer15).build();
            RoleEntity role15 = RoleEntity.builder().roleName("User").build();
            AccountEntity account15 = AccountEntity.builder().email("david@example.com").password("pass678").name("David").role(role15).build();
            ServiceEntity service15 = ServiceEntity.builder().serviceType(ServiceType.Shuttle).build();
            ParkingGarageUtilityEntity garageUtility15 = ParkingGarageUtilityEntity.builder().parkingSpaces(85L).parkingSpacesElectric(15L).electricChargePoint(true).floors(4L).toilet(true).build();
            ParkingGarageEntity parkingGarage15 = ParkingGarageEntity.builder().location("456 Oak Ln").airport("MNO International Airport").name("Oak Lane Parking").phoneNumber("555-888-9999").travelDistance(8L).parkingGarageUtility(garageUtility15).travelTime(20L).build();

            customerRepository.save(customer15);
            carRepository.save(car15);
            parkingGarageRepository.save(parkingGarage15);
            accountRepository.save(account15);
            serviceRepository.save(service15);
            bookingRepository.save(BookingEntity.builder().customer(customer15).car(car15).garage(parkingGarage15).service(service15).endDate(LocalDateTime.now().plusDays(16L)).startDate(LocalDateTime.now().plusDays(15L)).flightNumberArrival(123456L).flightNumberDeparture(654321L).build());
        }


        if (bookingRepository.count() == 15) {
            CustomerEntity customer16 = CustomerEntity.builder().name("Emily Davis").phoneNumber("555-876-5432").email("emily@example.com").build();
            CarEntity car16 = CarEntity.builder().id(17L).licensePlate("EMI456").brand("Ford").model("Explorer").electric(true).customer(customer16).build();
            RoleEntity role16 = RoleEntity.builder().roleName("Admin").build();
            AccountEntity account16 = AccountEntity.builder().email("emily@example.com").password("pass456").name("Emily").role(role16).build();
            ServiceEntity service16 = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
            ParkingGarageUtilityEntity garageUtility16 = ParkingGarageUtilityEntity.builder().parkingSpaces(120L).parkingSpacesElectric(30L).electricChargePoint(true).floors(8L).toilet(true).build();
            ParkingGarageEntity parkingGarage16 = ParkingGarageEntity.builder().location("789 Maple St").airport("ABC Airport").name("Maple Street Parking").phoneNumber("555-666-7777").travelDistance(15L).parkingGarageUtility(garageUtility16).travelTime(28L).build();

            customerRepository.save(customer16);
            carRepository.save(car16);
            parkingGarageRepository.save(parkingGarage16);
            accountRepository.save(account16);
            serviceRepository.save(service16);
            bookingRepository.save(BookingEntity.builder().customer(customer16).car(car16).garage(parkingGarage16).service(service16).endDate(LocalDateTime.now().plusDays(17L)).startDate(LocalDateTime.now().plusDays(16L)).flightNumberArrival(234567L).flightNumberDeparture(765432L).build());
        }








    }
    private void insertAdminUser() {
        UserEntity adminUser = UserEntity.builder()
                .username("admin@gmail.com")
                .password(passwordEncoder.encode("123"))
                .build();
        UserRoleEntity adminRole = UserRoleEntity.builder().role(RoleEnum.ADMIN).user(adminUser).build();
        adminUser.setUserRoles(Set.of(adminRole));
        userRepository.save(adminUser);
    }
}
