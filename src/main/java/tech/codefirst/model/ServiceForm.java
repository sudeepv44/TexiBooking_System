package tech.codefirst.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


   
    private String image; // Storing image name instead of binary data

  
    private String title;

    
    private String description;
}
