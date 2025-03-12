package tech.codefirst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.codefirst.dao.ContactFormCrud;
import tech.codefirst.model.ContactForm;

@Service
public class ContactFormServiceimpl implements ContactFormService {
	
	private ContactFormCrud contactFormCrud;
	
	@Autowired
		public void setContactFormCrud(ContactFormCrud contactFormCrud) {
		this.contactFormCrud = contactFormCrud;
	}


	@Override
	public ContactForm savecontactFormService(ContactForm contactForm) {
	
		return contactFormCrud.save(contactForm);
		
	}


	@Override
	public List<ContactForm> readAllContactService() {
		
		return contactFormCrud.findAll();
	}


	@Override
	public void deletecontactFormService(int id) {
		
		contactFormCrud.deleteById(id);
		
	}


}
