package com.rooshdashboard.rooshdashboard.configuration;

import com.rooshdashboard.rooshdashboard.domain.Account.CreateAccountRequest;
import com.rooshdashboard.rooshdashboard.persistance.AccountRepository;
import com.rooshdashboard.rooshdashboard.persistance.BookingRepository;
import com.rooshdashboard.rooshdashboard.persistance.CarRepository;
import com.rooshdashboard.rooshdashboard.persistance.ParkingGarageRepository;
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

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        CarEntity savedCar = CarEntity.builder().id(1L).licensePlate("XYZ123").brand("Toyota").model("Camry").electric(false).build();
        RoleEntity savedRole = RoleEntity.builder().roleName("f").build();
        AccountEntity savedAccount = AccountEntity.builder().email("jane@example.com").password("pass456").name("Jane").role(savedRole).build();
        ServiceEntity savedService = ServiceEntity.builder().serviceType(ServiceType.Valet).build();
        ParkingGarageUtilityEntity savedParkingGarageUtitlity = ParkingGarageUtilityEntity.builder().parkingSpaces(1L).parkingGarage()
        ParkingGarageEntity savedParkingGarage = ParkingGarageEntity.builder().location("123 Main St").airport("s").name("s").phoneNumber("123").travelDistance(12L).travelTime(12L).account(savedAccount).parkingGarageUtility();
        if (bookingRepository.count() == 0) {
            bookingRepository.save(BookingEntity.builder().car(savedCar).garage(savedParkingGarage).service(savedService).endDate(LocalDateTime.now().plusDays(1L)).startDate(LocalDateTime.now()).flightNumberArrival(1L).flightNumberDeparture(1L).build());
        }
    }}
