package tech.codefirst.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.codefirst.model.ServiceForm;

public interface ServiceFormService  {
	
	public ServiceForm addService(ServiceForm serviceForm,MultipartFile multipartFile) throws Exception;
	public List<ServiceForm> readAllServices();
	public void deleteServiceFormService(int id);


}
