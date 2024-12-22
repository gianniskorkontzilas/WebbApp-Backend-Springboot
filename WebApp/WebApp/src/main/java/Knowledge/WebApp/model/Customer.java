package Knowledge.WebApp.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vat_number", nullable = false, unique = true)
    private String vatNumber;

    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @Column(name = "last_name" ,  nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

}

