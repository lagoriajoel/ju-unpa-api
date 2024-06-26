package juUnpa.API.Security.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@Data
public class NuevoUsuario {

    @NotBlank
    private String nombre;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();





}
