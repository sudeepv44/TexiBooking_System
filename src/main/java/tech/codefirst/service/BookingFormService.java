package tech.codefirst.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tech.codefirst.model.BookingForm;
import tech.codefirst.model.ContactForm;



public interface BookingFormService {
	
	public  BookingForm savebookingFormService(BookingForm bookingForm);
	
	   public  List<BookingForm> readAllBookingService();

	    public	void deletebookingFormService(int id);
	

}
