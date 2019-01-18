package com.spaceRangers.repository;

import com.spaceRangers.entities.FractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FractionRepository extends JpaRepository<FractionEntity, Integer> {

    @Query(value = "select fraction from FractionEntity fraction where fraction.nameFraction = :name")
    FractionEntity getFractionByNameFraction(@Param("name") String name);

}
