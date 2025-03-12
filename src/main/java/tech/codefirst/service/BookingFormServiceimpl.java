package tech.codefirst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.codefirst.dao.BookingFormCrud;
import tech.codefirst.model.BookingForm;

@Service
public class BookingFormServiceimpl implements BookingFormService {
	
   private BookingFormCrud bookingFormCrud;
   
     @Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
	   this.bookingFormCrud = bookingFormCrud;
}


	@Override
	public BookingForm savebookingFormService(BookingForm bookingForm) {
		
		return bookingFormCrud.save(bookingForm);
	}


	@Override
	public List<BookingForm> readAllBookingService() {
		
		return bookingFormCrud.findAll();
	}


	
	
	
	@Override
	public void deletebookingFormService(int id) {
		
		bookingFormCrud.deleteById(id);
		
	}


}
