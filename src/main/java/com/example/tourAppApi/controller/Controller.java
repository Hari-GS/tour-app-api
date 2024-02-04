package com.example.tourAppApi.controller;

import com.example.tourAppApi.model.Bookings;
import com.example.tourAppApi.model.Model;
import com.example.tourAppApi.model.Plans;
import com.example.tourAppApi.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3001/")
public class Controller {

    @Autowired
    Service service;

    @PostMapping("/to-book/{userName}")
    public ResponseEntity<String> setBookingDetails(@PathVariable String userName, @RequestBody Bookings bookingDetails){
        System.out.println(bookingDetails.getUserId());
        if(service.getUserStatus(bookingDetails.getUserId())){
            return new ResponseEntity<>("Booking details for the user already exist", HttpStatus.CONFLICT);
        }else{
            service.storeBookings(bookingDetails);
            return new ResponseEntity<>("Booking details stored successfully", HttpStatus.OK);
        }

    }

    @GetMapping("/{userId}/booked")
    public Bookings getBookingDetails(@PathVariable int userId) {
        return service.getBookingDetailsService(userId);

    }

    @GetMapping("/plans")
    public List<Plans> getBusDetails() {
        return service.fetchPlans();
    }

    @GetMapping("/plan/{busNumber}")
    public Plans getBusDetail(@PathVariable int busNumber) {
        return service.fetchPlanByBusNumber(busNumber);

    }

    @PostMapping("/plan/{busNumber}")
    public ResponseEntity<Plans> modifyBusSeats( @RequestParam int seats, @PathVariable int busNumber) {
        Plans plans = service.fetchPlanByBusNumber(busNumber);

        if (plans != null) {
            // Reduce the number of seats by 1 (or adjust as needed)
            int totalSeats= plans.getAvailableSeats()-seats;
            service.modify(totalSeats,busNumber); // Assuming a method to save the updated plan in your service

            return ResponseEntity.ok(plans);
        } else {
            // Return a response indicating that the bus with the given number was not found
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/signup")
    public void saveSignUps(@RequestBody Model model) {
        service.save(model);
        service.storeUser(model);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Model loginRequest) {
        // Assuming your 'Model' class has 'username' and 'password' fields
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        int userId = 0;
        // Perform authentication logic using your service or repository
        boolean isAuthenticated = service.authenticateUser(username, password);
        if(isAuthenticated){
            userId=service.getUserId(username);
        }

        if (isAuthenticated) {
            // For simplicity, returning a success message if the username and password are authenticated
            return ResponseEntity.ok("Authentication successful for user: " + username + ". User ID: " + userId);
        } else {
            // Return a response indicating failed authentication
            return ResponseEntity.status(401).body("Authentication failed for user: " + username);
        }
    }
}
