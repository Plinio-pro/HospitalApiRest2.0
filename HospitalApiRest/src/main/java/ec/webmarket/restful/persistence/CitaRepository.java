package ec.webmarket.restful.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.domain.Paciente;

public interface CitaRepository extends JpaRepository<Cita, Long>{
	
	List<Cita> findByPacienteId(Long pacienteId);
	
	List<Cita> findByOdontologoId(Long odontologoId);
	
	List<Cita> findByHorario(Horario horario);
	
	List<Cita> findByFechayHora(LocalDateTime FechayHora);
	
	List<Cita> findByEstado(String Estado);
	
	List<Cita> findByMotivo(String Motivo);
} 
