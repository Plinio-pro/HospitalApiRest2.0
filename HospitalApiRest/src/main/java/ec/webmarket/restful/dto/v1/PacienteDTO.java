package ec.webmarket.restful.dto.v1;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PacienteDTO {

    private Long id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private String direccion;
    private UsuarioDTO usuario;
}
