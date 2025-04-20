package com.yassine.yassine_hachguer.repository;

import com.yassine.yassine_hachguer.entities.Ressource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    Page<Ressource> findByTitreContains(String keyword, Pageable pageable);
}
