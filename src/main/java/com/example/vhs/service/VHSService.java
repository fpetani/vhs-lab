package com.example.vhs.service;

import com.example.vhs.exception.EntityDoesNotExistException;
import com.example.vhs.repository.VHSRepository;
import com.example.vhs.entity.VHS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VHSService {
    private final VHSRepository vhsRepository;

    @Autowired
    public VHSService(VHSRepository vhsRepository){
        this.vhsRepository=vhsRepository;
    }

    public List<VHS> findAll(){
        return vhsRepository.findAll();
    }

    public List<VHS> filterByTitle(String title){ return vhsRepository.findByTitleContainingIgnoreCase(title);}

    public List<VHS> filterByYear(Integer year){return vhsRepository.findByYear(year);}

    public List<VHS> filterByGenre(String genre){return vhsRepository.findByGenreContainingIgnoreCase(genre);}

    public VHS findById(Long id){
        return vhsRepository.findById(id).orElseThrow(()->new EntityDoesNotExistException("VHS with given id does not exist."));
    }
}
