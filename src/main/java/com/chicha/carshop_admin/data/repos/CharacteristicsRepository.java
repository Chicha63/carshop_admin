package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristics, Integer> {

    @Query("SELECT COUNT(c) > 0 FROM Characteristics c WHERE c.engine = :engine " +
            "AND c.enginePlacement = :enginePlacement AND c.bodyType = :bodyType " +
            "AND c.placesCount = :placesCount AND c.doorsCount = :doorsCount ")
    boolean existsWithSimilarFieldsExcludingId(
            @Param("engine") Engine engine,
            @Param("enginePlacement") EnginePlacement enginePlacement,
            @Param("bodyType") BodyType bodyType,
            @Param("placesCount") int placesCount,
            @Param("doorsCount") int doorsCount
    );

    Characteristics findByEngineAndEnginePlacementAndBodyTypeAndPlacesCountAndDoorsCount(Engine engine, EnginePlacement enginePlacement, BodyType bodyType, Integer placesCount, Integer doorsCount);
}
