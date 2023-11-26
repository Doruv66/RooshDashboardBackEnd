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

    @Query("SELECT b FROM BookingEntity b WHERE DATE(b.startDate) = DATE(:givenDate)")
    List<BookingEntity> findAllByStartDate(@Param("givenDate") LocalDate givenDate);

    @Query("SELECT b FROM BookingEntity b WHERE DATE(b.endDate) = DATE(:givenDate)")
    List<BookingEntity> findAllByEndDate(@Param("givenDate") LocalDate givenDate);

    @Query("SELECT b FROM BookingEntity b WHERE b.startDate >= :startDateTime AND b.startDate < :endDateTime")
    List<BookingEntity> findAllByStartTimeInterval(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );

    @Query("SELECT b FROM BookingEntity b WHERE b.endDate >= :startDateTime AND b.endDate < :endDateTime")
    List<BookingEntity> findAllByEndTimeInterval(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
}
