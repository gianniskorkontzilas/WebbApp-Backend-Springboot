package Knowledge.WebApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotNull
    private Long id;

    @NotNull
    private String vatNumber;

    @NotNull
    @Size(min = 3, max = 16)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 16)
    private String lastName;

}
