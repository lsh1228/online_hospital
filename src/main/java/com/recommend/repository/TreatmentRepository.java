package com.recommend.repository;
import java.util.List;


import com.recommend.bean.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    List<Treatment> findByTitleContaining(String title);
}