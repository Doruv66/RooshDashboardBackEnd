package com.rooshdashboard.rooshdashboard.persistance;

import com.rooshdashboard.rooshdashboard.persistance.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface BookingRepository extends JpaRepository<BookingEntity, Long>, JpaSpecificationExecutor<BookingEntity> {

    @Query("SELECT b FROM BookingEntity b WHERE DATE(b.startDate) = DATE(:givenDate) AND b.garage.id = :garageId")
    List<BookingEntity> findAllByStartDate(
            @Param("givenDate") LocalDate givenDate,
            @Param("garageId") Long garageId
    );

    @Query("SELECT b FROM BookingEntity b WHERE DATE(b.endDate) = DATE(:givenDate) AND b.garage.id = :garageId")
    List<BookingEntity> findAllByEndDate(
            @Param("givenDate") LocalDate givenDate,
            @Param("garageId") Long garageId
    );

    @Query("SELECT b FROM BookingEntity b WHERE b.startDate >= :startDateTime AND b.startDate < :endDateTime AND b.garage.id = :garageId")
    List<BookingEntity> findAllByStartTimeInterval(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("garageId") Long garageId
    );

    @Query("SELECT b FROM BookingEntity b WHERE b.endDate >= :startDateTime AND b.endDate < :endDateTime AND b.garage.id = :garageId")
    List<BookingEntity> findAllByEndTimeInterval(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("garageId") Long garageId
    );
}
