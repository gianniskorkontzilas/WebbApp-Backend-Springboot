package Knowledge.WebApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Stores")
public class Store {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name" , nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Customer> customers;


}

