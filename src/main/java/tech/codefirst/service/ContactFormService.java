package tech.codefirst.service;

import java.util.List;  // ✅ Correct import

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.codefirst.model.ContactForm;

public interface ContactFormService {
	
    public ContactForm savecontactFormService(ContactForm contactForm);
	
    public  List<ContactForm> readAllContactService();
    
    public	void deletecontactFormService(int id);
    
    
}
