package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service

public class HotelManagementService {

    @Autowired
    HotelManagementRepository  hotelManagementRepository = new HotelManagementRepository();

    public HotelManagementService() {

    }


    public String addHotel(Hotel hotel) {
       return hotelManagementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return hotelManagementRepository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        List<Hotel> list = hotelManagementRepository.getAllHotels();
        Hotel max = list.get(0);

        for(Hotel hotel: list){
            if(hotel.getFacilities().size()> max.getFacilities().size()){
                max = hotel;

            }else if(hotel.getFacilities().size()==max.getFacilities().size()) {

                if (max.getHotelName().compareTo(hotel.getHotelName()) == -1)
                    max = hotel;
            }
        }

        if(max.getFacilities().size()==0)
            return "";

        return max.getHotelName();

    }

    public Integer bookARoom(Booking booking) {
        booking.setBookingId(String.valueOf(UUID.randomUUID()));

        return hotelManagementRepository.bookARoom(booking);

    }

    public int getBookings(Integer aadharCard) {

        return hotelManagementRepository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        return hotelManagementRepository.updateFacilities(newFacilities,hotelName);

    }
}
