package com.istiaq.daycare.repository;

import com.istiaq.daycare.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IApplyRepo extends JpaRepository<Apply, Long> {

    // Get all applications by caregiver
    List<Apply> findByCaregiver_Id(Long caregiverId);


    // Optional: find by parent and specific job
    @Query("SELECT a FROM Apply a WHERE a.parent.id = :parentId AND a.job.id = :jobId")
    List<Apply> findAllByParentAndJob(@Param("parentId") Long parentId, @Param("jobId") Long jobId);

    // ✅ Get single application by caregiver ID (first one if multiple exist)
    Optional<Apply> findFirstByCaregiver_Id(Long caregiverId);
}
