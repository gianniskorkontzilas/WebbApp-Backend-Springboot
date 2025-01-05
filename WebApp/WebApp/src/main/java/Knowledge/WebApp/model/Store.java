package Knowledge.WebApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table(name = "Stores")
public class Store {

    public Store() {

    }

    public Store(Long id, String name, List<Customer> customers) {
        this.id = id;
        this.name = name;
        this.customers = customers;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name" , nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Customer> customers;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}

