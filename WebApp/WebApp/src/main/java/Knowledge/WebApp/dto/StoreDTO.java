package Knowledge.WebApp.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class StoreDTO {

    public StoreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 16)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
