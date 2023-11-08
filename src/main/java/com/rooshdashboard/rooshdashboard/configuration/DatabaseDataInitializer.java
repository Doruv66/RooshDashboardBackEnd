package com.rooshdashboard.rooshdashboard.configuration;

import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountRequest;
import com.rooshdashboard.rooshdashboard.persistance.*;
import com.rooshdashboard.rooshdashboard.persistance.entity.*;
import lombok.AllArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor

public class DatabaseDataInitializer {
    private AccountRepository accountRepository;
    private BookingRepository bookingRepository;
    private ParkingGarageRepository parkingGarageRepository;
    private CarRepository carRepository;
    private ServiceRepository serviceRepository;
    private CustomerRepository customerRepository;
    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        CustomerEntity savedCustomer = CustomerEntity.builder().name("asdfs").phoneNumber("0623").email("geuj@isdfe").build();
        CarEntity savedCar = CarEntity.builder().id(1L).licensePlate("XYZ123").brand("Toyota").model("Camry").electric(false).customer(savedCustomer).build();
        RoleEntity savedRole = RoleEntity.builder().roleName("f").build();
        AccountEntity savedAccount = AccountEntity.builder().email("jane@example.com").password("pass456").name("Jane").role(savedRole).build();
        ServiceEntity savedService = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity savedParkingGarageUtility = ParkingGarageUtilityEntity.builder().parkingSpaces(1L).parkingSpacesElectric(1L).electricChargePoint(false).floors(1L).toilet(false).build();
        ParkingGarageEntity savedParkingGarage = ParkingGarageEntity.builder().location("123 Main St").airport("s").name("s").phoneNumber("123").travelDistance(12L).parkingGarageUtility(savedParkingGarageUtility).travelTime(12L).account(savedAccount).build();
        if (bookingRepository.count() == 0) {
            customerRepository.save(savedCustomer);
            carRepository.save(savedCar);
            parkingGarageRepository.save(savedParkingGarage);
            accountRepository.save(savedAccount);
            serviceRepository.save(savedService);
            bookingRepository.save(BookingEntity.builder().customer(savedCustomer).car(savedCar).garage(savedParkingGarage).service(savedService).endDate(LocalDateTime.now().plusDays(1L)).startDate(LocalDateTime.now()).flightNumberArrival(1L).flightNumberDeparture(1L).build());
        }
    }}
