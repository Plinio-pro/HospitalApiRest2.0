package ec.webmarket.restful.dto.v1;

import lombok.Data;

@Data
public class OdontologoDTO {

    private Long id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private UsuarioDTO usuario; // Relación con UsuarioDTO
}
