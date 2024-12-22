package Knowledge.WebApp.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login" , nullable = false, unique = true)
    private String login;

    @Column(name = "password" , nullable = false, unique = true)
    private String password;
}




