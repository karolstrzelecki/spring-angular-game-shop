package com.karolstrzelecki.gameshop.repository;

import com.karolstrzelecki.gameshop.models.Copy.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {


}
