package com.publicdatabatchimporter.publicdatabatchimporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.publicdatabatchimporter.publicdatabatchimporter.model.PublicData;

public interface PublicDataRepository extends JpaRepository<PublicData, Long> {

}
