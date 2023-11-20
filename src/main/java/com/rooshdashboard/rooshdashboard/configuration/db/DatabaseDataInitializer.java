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
