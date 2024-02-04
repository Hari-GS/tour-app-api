package com.example.tourAppApi.services;

import com.example.tourAppApi.model.Bookings;
import com.example.tourAppApi.model.Model;
import com.example.tourAppApi.model.Plans;
import com.example.tourAppApi.model.User;
import com.example.tourAppApi.repo.BookingsRepo;
import com.example.tourAppApi.repo.PlansRepo;
import com.example.tourAppApi.repo.Repository;
import com.example.tourAppApi.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    Repository repository;

    @Autowired
    PlansRepo plansRepo;

    @Autowired
    BookingsRepo bookingsRepo;

    @Autowired
    UserRepo userRepo;

    public void save(Model model){
        repository.save(model);
    }

    public int getUserId(String username){
        Model model=repository.findByUsername(username);
        return model.getUserId();
    }


    public boolean authenticateUser(String username, String password) {
        // Retrieve the user from the database based on the provided username
        Model user = repository.findByUsername(username);

        // Check if the user exists and the provided password matches the stored password
        return user != null && user.getPassword().equals(password);
    }

    public List<Plans> fetchPlans(){
        return plansRepo.findAll();

    }

    public Plans fetchPlanByBusNumber(int busNumber) {
        return plansRepo.findByBusNumber(busNumber);
    }

    public void modify(int newNumberOfSeats,int busNumber) {
        Optional<Plans> optionalPlan = Optional.ofNullable(plansRepo.findByBusNumber(busNumber));

        if (optionalPlan.isPresent()) {
            Plans existingPlan = optionalPlan.get();
            existingPlan.setAvailableSeats(newNumberOfSeats);
            plansRepo.save(existingPlan);
        } else {
            // Handle the case where the plan with the specified bus number is not found
            // You can throw an exception, log an error, or handle it based on your requirements
        }
    }

    public void storeBookings(Bookings bookingDetails) {
        bookingsRepo.save(bookingDetails);

        Plans plans = plansRepo.findByBusNumber(bookingDetails.getBusNumber());
        plans.setAvailableSeats(plans.getAvailableSeats()-bookingDetails.getBookedSeats());
        plansRepo.save(plans);

        User user=userRepo.findByUserId(bookingDetails.getUserId());
        user.setBooked(true);
        userRepo.save(user);

        Model model=repository.findByUsername(bookingDetails.getUserName());
        model.setBooked(true);
        repository.save(model);
    }

    public Bookings getBookingDetailsService(int userid) {
        User user= userRepo.findByUserId(userid);
        System.out.println(user.getUserName());
        Bookings bookings = bookingsRepo.findByUserName(user.getUserName());
        return bookings;
    }

    public boolean getUserStatus(int userId){
        User user= userRepo.findByUserId(userId);
        System.out.println("hiiiiii");
        return user.isBooked();
    }

    public void storeUser(Model model){
        User user = new User();
        user.setUserId(model.getUserId());

        user.setUserName(model.getUsername());
        user.setBooked(false);
        user.setBookedSeats(0);
        user.setBusNumber(0);
        userRepo.save(user);

    }

//    public Bookings getBookingDetailsService() {
//
//    }
}
