package ec.webmarket.restful.dto.v1;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CitaDTO {
	
	private Long id;
	private PacienteDTO paciente;
	private OdontologoDTO odontologo;
	private HorarioDTO horario;
	private LocalDateTime fechayHora;
	private String estado;
	private String motivo;
	
	
}
