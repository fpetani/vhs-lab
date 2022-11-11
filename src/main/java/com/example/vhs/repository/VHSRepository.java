package com.example.vhs.repository;

import com.example.vhs.entity.VHS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VHSRepository extends JpaRepository<VHS, Long> {
}
