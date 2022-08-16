package com.giantlink.grh.repositories;

import com.giantlink.grh.entities.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {
    Occupation findByOccupationName(String occupationName);
}
