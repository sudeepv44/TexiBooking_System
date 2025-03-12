package tech.codefirst.controller;

import java.awt.Image;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tech.codefirst.model.ContactForm;
import tech.codefirst.model.ServiceForm;
import tech.codefirst.service.AdminCredentialsService;
import tech.codefirst.service.BookingFormService;
import tech.codefirst.service.ContactFormService;
import tech.codefirst.service.ServiceFormService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	
	private ContactFormService  contactFormService;
	
	private BookingFormService  bookingFormService;
	
	private AdminCredentialsService adminCredentialsService;
	
	private ServiceFormService serviceFormService;
	
	
	@Autowired
	public void setServiceFormService(ServiceFormService serviceFormService) {
		this.serviceFormService = serviceFormService;
	}

	@Autowired
	public void setAdminCredentialsService(AdminCredentialsService adminCredentialsService) {
		this.adminCredentialsService = adminCredentialsService;
	}
	
	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}
	

	@Autowired
    public void setContactFormService(ContactFormService contactFormService) {
		this.contactFormService = contactFormService;
	}

	
	
	@GetMapping("dashboard")
	public String adminDashboard() {
		return "admin/dashboard";
		
	}
	
	
	@GetMapping("readAllContacts")
	public String readAllContacts(Model model) {
		
		model.addAttribute("allcontacts",contactFormService.readAllContactService());
	
		return "admin/readAllContacts";
	
	}
	
	@GetMapping("readAllBookings")
	public String readAllBookings(Model model) {
		
		model.addAttribute("allbookings",bookingFormService.readAllBookingService());
	
		return "admin/readAllBookings";
	
	}
	
	
	@GetMapping("readAllServices")
	public String readAllServices(Model model) {
		
		model.addAttribute("allservices",serviceFormService.readAllServices());
	
		return "admin/readAllServices";
	
	}
	
	
	
	@GetMapping("addService")
	public String addServicecview() {
		
      return "admin/addService";
	}
	
	@InitBinder
	public void stopBinder(WebDataBinder webDataBinder){
		webDataBinder.setDisallowedFields("image");
	}
	
	
	@PostMapping("addService")
	public String addServicec(@ModelAttribute ServiceForm serviceForm,@RequestParam("image") MultipartFile multipartFile,RedirectAttributes redirectAttributes) {
		
		String originalFilename = multipartFile.getOriginalFilename();
		serviceForm.setImage(originalFilename);
		
		try {
			
			 ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
			if (service!=null) {
				redirectAttributes.addFlashAttribute("message","SERVICE ADDED SUCCESSFULLY");
			}else {
				redirectAttributes.addFlashAttribute("message","SOMETHING WENT WRONG");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return "redirect:/admin/addService";
	}
	
	
	@GetMapping("deleteContact/{id}")
	public String deleteContacts(@PathVariable int id,RedirectAttributes redirectAttributes) {
		
		
		contactFormService.deletecontactFormService(id);
		redirectAttributes.addFlashAttribute("message","CONTACT DELETED SUCCESSFULLY");
	    return "redirect:/admin/readAllContacts";
	
	}
	

	@GetMapping("deleteBooking/{id}")
	public String deleteBooking(@PathVariable int id,RedirectAttributes redirectAttributes) {
		
		
		bookingFormService.deletebookingFormService(id);
		redirectAttributes.addFlashAttribute("message","Booking DELETED SUCCESSFULLY");
	    return "redirect:/admin/readAllBookings";
	
	}
	
	
	@GetMapping("deleteService/{id}")
	public String deleteService(@PathVariable int id,RedirectAttributes redirectAttributes) {
		
		
	
		serviceFormService.deleteServiceFormService(id);
		redirectAttributes.addFlashAttribute("message","Service DELETED SUCCESSFULLY");
	    return "redirect:/admin/readAllServices";
	
	}
	
	
	
	@GetMapping("changeCredentials")
	public String changelogindetailsView() {
		return "admin/changeCredentials";
	}
	
	@PostMapping("changeCredentials")
	public String changeCredentials(
	        @RequestParam("oldusername") String oldusername,
	        @RequestParam("oldpassword") String oldpassword,
	        @RequestParam("newusername") String newusername,
	        @RequestParam("newpassword") String newpassword,
	        RedirectAttributes redirectAttributes) {
	    
	    // Validate old credentials
	    String result = adminCredentialsService.checkAdminCredentials(oldusername, oldpassword);
	    
	    if (result.equals("SUCCESS")) {
	        // If credentials are valid, update the username & password
	        result = adminCredentialsService.updateAdminCredentials(newusername, newpassword, oldusername);
	        redirectAttributes.addFlashAttribute("message", result);
	    } else {
	        // If validation fails, send failure message
	        redirectAttributes.addFlashAttribute("message", result);
	    }

	    // Redirect back to admin dashboard with success or failure message
	    return "redirect:/admin/dashboard";
	}


	
	
}
