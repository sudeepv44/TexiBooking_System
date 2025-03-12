package tech.codefirst.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
@Table(name = "bookingform")
public class BookingForm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only alphabets and spaces")
    private String name;

    @Column(name = "source", length = 100, nullable = false)
    @NotBlank(message = "Source cannot be blank")
    @Size(min = 2, max = 50, message = "Source should be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Source must contain only alphabets and spaces")
    private String source;

    @Column(length = 50, nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(name = "destination", length = 100, nullable = false)
    @NotBlank(message = "Destination cannot be blank")
    @Size(min = 2, max = 50, message = "Destination should be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Destination must contain only alphabets and spaces")
    private String destination;

    @Column(nullable = false)
    @NotNull(message = "Time cannot be null")
    private LocalTime time;

    @Column(nullable = false)
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Comfort preference cannot be blank")
    private String comfort;

    @Min(value = 2, message = "At least 2 adults are required to travel")
    @Max(value = 4, message = "Maximum 4 adults are allowed")
    private int adult;

    @Max(value = 3, message = "Maximum 3 children are allowed")
    private int children;

    @Column(length = 2000, nullable = false)
    @NotBlank(message = "Message cannot be blank")
    @Size(min = 5, max = 300, message = "Message should be between 5 and 300 characters")
    @Pattern(regexp = "^[A-Za-z0-9.,!? ]+$", message = "Message contains invalid characters")
    private String message;
}
