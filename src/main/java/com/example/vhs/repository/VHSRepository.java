package com.example.vhs.repository;

import com.example.vhs.entity.VHS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VHSRepository extends JpaRepository<VHS, Long> {
    List<VHS> findByTitleContainingIgnoreCase(String title);
    List<VHS> findByYear(Integer year);

    List<VHS> findByGenreContainingIgnoreCase(String genre);
}
