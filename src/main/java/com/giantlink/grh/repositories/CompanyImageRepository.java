package com.giantlink.grh.repositories;

import com.giantlink.grh.entities.CompanyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyImageRepository extends JpaRepository<CompanyImage, String> {

}
