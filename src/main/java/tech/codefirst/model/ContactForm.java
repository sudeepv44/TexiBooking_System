package tech.codefirst.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.Collate;
import org.springframework.boot.context.properties.bind.Name;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "contactform")
public class ContactForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
 
	
	@Column(length=30)
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Size(min = 5, max = 50, message = "Email size must be between 5 and 50 characters")
    @Column(length=50)
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Min(value = 1000000000L, message = "Phone number must be at least 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be at most 10 digits")
    @Column(length=10)
    private Long phone;

    @NotEmpty(message = "Message cannot be empty")
    @Size(min = 5, max = 300, message = "Message should be between 5 and 300 characters")
    @Column(length=500)
    private String message;
}





































//package tech.codefirst.model;
//
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class ContactForm {
//    
//    @Id
//    private int id;
//    
//    @NotEmpty(message = "Name cannot be empty")
//    @Size(min = 2, max = 15, message = "Name size is invalid. It should be between 2 and 15 characters.")
//    @Pattern(regexp = "^[A-Za-z]+([-' ]?[A-Za-z]+)*$", message = "Name must only contain letters, spaces, apostrophes, or hyphens.")
//    private String name;
//    
//    @NotEmpty(message = "Email cannot be empty")
//    @Size(min = 5, max = 50, message = "Email size is invalid. It should be between 5 and 50 characters.")
//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email format")
//    private String email;
//    
//    @NotEmpty(message = "Phone number cannot be empty")
//    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
//    private String phone;
//
//    @NotEmpty(message = "Message cannot be empty")
//    @Size(min = 5, max = 500, message = "Message length must be between 5 and 500 characters.")
//    @Pattern(regexp = "^[A-Za-z0-9 .,!?;:'\"-]*$", message = "Message contains invalid characters.")
//    private String message;
//}

