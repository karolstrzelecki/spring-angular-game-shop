package com.karolstrzelecki.gameshop.repository;

import com.karolstrzelecki.gameshop.models.ComputerGame;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.models.Role;
import com.karolstrzelecki.gameshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ComputerGameRepository extends JpaRepository<ComputerGame, Long> {

    Optional<List<ComputerGame>> findAllByCategoriesIn (Set<GameCategory> categories);
}
