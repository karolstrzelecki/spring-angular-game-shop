package com.karolstrzelecki.gameshop.repository;

import com.karolstrzelecki.gameshop.models.ComputerGame;
import com.karolstrzelecki.gameshop.models.Role;
import com.karolstrzelecki.gameshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerGameRepository extends JpaRepository<ComputerGame, Long> {

}
