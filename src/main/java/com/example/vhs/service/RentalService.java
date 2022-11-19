package com.example.vhs.service;

import com.example.vhs.entity.Rental;
import com.example.vhs.entity.User;
import com.example.vhs.entity.VHS;
import com.example.vhs.exception.ApiRequestException;
import com.example.vhs.exception.EntityDoesNotExistException;
import com.example.vhs.repository.RentalRepository;
import com.example.vhs.repository.UserRepository;
import com.example.vhs.repository.VHSRepository;
import com.example.vhs.request.RentalForm;
import com.example.vhs.request.ReturnForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    private final Logger log = LoggerFactory.getLogger(RentalService.class);
    private final UserRepository userRepository;
    private final VHSRepository vhsRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository,
                         VHSRepository vhsRepository){
        this.rentalRepository=rentalRepository;
        this.userRepository=userRepository;
        this.vhsRepository=vhsRepository;
    }

    public List<Rental> findAllRental(){
        return rentalRepository.findAll();
    }

    public Rental findRentalById(Long id){
        return rentalRepository.findById(id).orElseThrow(()-> new EntityDoesNotExistException("Rental with given id does not exist"));
    }

    public Rental createRental(RentalForm form) {
        Rental newRental = new Rental();

        //Setting VHS
        VHS vhs = vhsRepository.findById(form.getVhsId()).orElseThrow(()->new EntityDoesNotExistException("No VHS with given id found"));
        if(vhs.isRented()){
            log.error("VHS already rented by another user");
            throw new ApiRequestException("VHS already rented by another user");
        }
        vhs.setRented(true);
        vhsRepository.save(vhs);
        newRental.setVhs(vhs);

        newRental.setRentalDuration(form.getRentalDuration());
        //Setting user
        User user = userRepository.findByUsername(form.getUsername()).orElseThrow(()->new EntityDoesNotExistException("No user with given username found"));
        newRental.setUser(user);

        //Setting rentalDate
        LocalDate rentalDate = LocalDate.parse(form.getRentalDate());
        newRental.setRentalDate(rentalDate);

        //Setting due date
        LocalDate dueDate = rentalDate.plusDays(form.getRentalDuration());
        newRental.setDueDate(dueDate);

        return rentalRepository.save(newRental);
    }
    /*Could use some code refactoring*/
    public Rental updateRental(Long rentalId, ReturnForm form){
        Rental rentalUpdate = rentalRepository.findById(rentalId).orElseThrow(()->new EntityDoesNotExistException("No rental with given id found"));
        LocalDate returnDate = LocalDate.parse(form.getReturnDate());
        rentalUpdate.setReturnDate(returnDate);

        //Free VHS for other users
        VHS vhs = rentalUpdate.getVhs();
        vhs.setRented(false);
        vhsRepository.save(vhs);

        //Calculate late fee
        LocalDate dueDate = rentalUpdate.getDueDate();
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

        //each day that user is late with returning costs him 0.5 fee per day defined in VHS entity
        if(daysLate > 0){
            rentalUpdate.setLateFee(daysLate*vhs.getFeePerDay());
        }else{
            rentalUpdate.setLateFee(0.0);
        }

        return rentalRepository.save(rentalUpdate);
    }

    public Rental deleteRental(Long id){
        Rental toDelete = rentalRepository.findById(id).orElseThrow(()->new EntityDoesNotExistException("No rental with given id found"));
        rentalRepository.delete(toDelete);

        return toDelete;
    }

}
