package com.karolstrzelecki.gameshop.repository;

import com.karolstrzelecki.gameshop.models.ComputerGame;
import com.karolstrzelecki.gameshop.models.Copy.Copy;
import com.karolstrzelecki.gameshop.models.Copy.Platform;
import com.karolstrzelecki.gameshop.models.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {

Optional<List<Copy>> findAllByShoppingCartIsNull();

Optional<List<Copy>> findAllByShoppingCartIsNullAndPlatform(Platform platform);

Optional<List<Copy>> findAllByShoppingCartIsNullAndComputerGame_CategoriesInAndPlatformIs(Set<GameCategory> categories, Platform platform);


}
