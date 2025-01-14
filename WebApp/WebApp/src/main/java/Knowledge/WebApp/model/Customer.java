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
@Table(name = "Customers",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"first_name", "last_name"})
})
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
    @JoinColumn(name = "store_id")
    private Store store;

    public Long getId() {
        return id;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Store getStore() {
        return store;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

