package com.max.littlebank.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.time.LocalDate;

/**
 * @author Serov Maxim
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(name = "image")
    private Blob image;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "name must be min 2 symbols")
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
            message = "Incorrect email")
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "\\+7\\d{3}-\\d{3}-\\d{2}-\\d{2}",
            message = "Use pattern +7XXX-XXX-XX-XX")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
}
