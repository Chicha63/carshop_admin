package com.chicha.carshop_admin.data.repos;

import com.chicha.carshop_admin.data.enities.Color;
import com.chicha.carshop_admin.data.enities.Model;
import com.chicha.carshop_admin.data.enities.PossibleColor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PossbleColorRepository extends JpaRepository<PossibleColor, Integer> {
    public List<PossibleColor> findAllByModelId(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM PossibleColor pc WHERE pc.model = :model AND pc.color = :color")
    void deleteByColorIdAndModelId(@Param("model") Model model, @Param("color") Color color);

}
