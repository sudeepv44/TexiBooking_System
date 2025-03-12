package tech.codefirst.controller;



import java.util.List;

import org.hibernate.validator.constraints.Mod10Check;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.persistence.Id;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import tech.codefirst.model.BookingForm;
import tech.codefirst.model.ContactForm;
import tech.codefirst.model.ServiceForm;
import tech.codefirst.service.BookingFormService;
import tech.codefirst.service.ContactFormService;
import tech.codefirst.service.ContactFormServiceimpl;
import tech.codefirst.service.ServiceFormService;

@Controller
public class MyController {
	
	
	private ContactFormService  contactFormService;
	
	private BookingFormService  bookingFormService;
	
	private ServiceFormService serviceFormService;
	

	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}

	@Autowired
    public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	@GetMapping(path= {"/","index","welcome","home"})
	public String welcomeView(HttpServletRequest request,Model model) {
		String requestURI = request.getRequestURI();
         model.addAttribute("mycurrentpage",requestURI);
         model.addAttribute("bookingForm",new BookingForm());
		return "index";
	}
	
	@GetMapping(path= "/login")
	public String adminloginView(HttpServletRequest request,Model model) {
	    ServletContext servletContext = request.getServletContext();
	    Object attribute = servletContext.getAttribute("logout");
	    if (attribute instanceof Boolean) {
	    	model.addAttribute("logout",attribute);
	    	servletContext.removeAttribute("logout");
			
		}
		return "adminlogin";
	}
	
	

	@GetMapping(path="about")
	public String aboutView(HttpServletRequest request,Model model) {
		String requestURI = request.getRequestURI();
        model.addAttribute("mycurrentpage",requestURI);
		return "about";
	}
	
	@GetMapping(path="cars")
	public String carsView(HttpServletRequest request,Model model) {
		String requestURI = request.getRequestURI();
        model.addAttribute("mycurrentpage",requestURI);
		return "cars";
	}
	
	@GetMapping(path="contacts")
	public String contactsView(HttpServletRequest request,Model model) {
		String requestURI = request.getRequestURI();
        model.addAttribute("mycurrentpage",requestURI);
        model.addAttribute("contactForm", new ContactForm());
		return "contacts";
	}
	
	
	@GetMapping(path="services")
	public String servicesView(HttpServletRequest request,Model model) {
		String requestURI = request.getRequestURI();
        model.addAttribute("mycurrentpage",requestURI);
        //data collection for service
        List<ServiceForm> allServices = serviceFormService.readAllServices();
        model.addAttribute("addservices",allServices);
		return "services";
	}
	
	
	
	@PostMapping(path="contactform")
	public String contactForm(@Valid @ModelAttribute ContactForm contactForm,BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult",bindingResult);
			return "contacts";
		}
		
		ContactForm savecontactFormService = contactFormService.savecontactFormService(contactForm);
		if (contactFormService!=null) {
			 redirectAttributes.addFlashAttribute("message","DATA INSERTED SUCCESSFULLY");
		}else {
			
			 redirectAttributes.addFlashAttribute("message","SOMETHING WENT WRONG");
		}
		
		return "redirect:/contacts";
	}
	
	
	
	
	
	@PostMapping(path="bookingform")
	public String bookingForm(@Valid @ModelAttribute BookingForm bookingForm,BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("bindingResult",bindingResult);
			return "index";
		}else if (bookingForm.getAdult()+bookingForm.getChildren()>4) {
			model.addAttribute("message","Total no of adult and children can not be more than 4");
			return "index";
		}
	 
		//service call
		BookingForm savebookingFormService = bookingFormService.savebookingFormService(bookingForm);
		if (savebookingFormService!=null) {
			redirectAttributes.addFlashAttribute("message","BOOKING FORM DATA INSERTED SUCCESSFULLY");
			
		}else {
			 redirectAttributes.addFlashAttribute("message","SOMETHING WENT WRONG");
		}
	
		return "redirect:/index";
	}
	

}


//		ContactForm savecontactFormService = contactFormService.savecontactFormService(contactForm);
//		if (contactFormService!=null) {
//			 redirectAttributes.addFlashAttribute("message","DATA INSERTED SUCCESSFULLY");
//		}else {
//			
//			 redirectAttributes.addFlashAttribute("message","SOMETHING WENT WRONG");
//		}