package com.driver.repository;


import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HotelManagementRepository {
    Map<String, Hotel> hotelDb;
    Map<Integer, User> userDb;

    Map<String, Booking> bookingDb;

    public HotelManagementRepository(){
        hotelDb = new HashMap<>();
        userDb = new HashMap<>();
        bookingDb = new HashMap<>();
    }


    public String addHotel(Hotel hotel) {

        if(hotel==null || hotel.getHotelName()==null)
            return "FAILURE";

        if(hotelDb.containsKey(hotel.getHotelName()))
            return "FAILURE";

        hotelDb.put(hotel.getHotelName(),hotel);

        return "SUCCESS";

    }

    public Integer addUser(User user) {

        userDb.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public List<Hotel> getAllHotels() {

        return (List<Hotel>) hotelDb.values();
    }

    public Integer bookARoom(Booking booking) {



        Hotel hotel = hotelDb.get(booking.getHotelName());
//        if(hotel==null)
//            return
        if(booking.getNoOfRooms()>hotel.getAvailableRooms())
            return -1;

        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());

        booking.setAmountToBePaid(hotel.getPricePerNight()*booking.getNoOfRooms());


        bookingDb.put(booking.getBookingId(),booking);

        return booking.getAmountToBePaid();
    }

    public int getBookings(Integer aadharCard) {

//        User user = userDb.get(aadharCard);
//        if(user==null)
//            return -1;
        int count = 0;
        for (Booking booking:  bookingDb.values()){
            if(booking.getBookingAadharCard()==aadharCard){
                count++;
            }
        }
        return count;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelDb.get(hotelName);

//        if(hotel==null)
//            return null;
        List<Facility> list = hotel.getFacilities();

        for (Facility facility: newFacilities){
            if(list.contains(facility))
                continue;
            list.add(facility);
        }
        hotel.setFacilities(list);
        return hotel;


    }
}
