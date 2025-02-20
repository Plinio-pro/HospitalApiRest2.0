package ec.webmarket.restful.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.webmarket.restful.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	
	List<Paciente> findByNombre(String nombre);
	
	List<Paciente> findByApellido(String apellido);
	
	List<Paciente> findByTelefono(String telefono);
	
	List<Paciente> findByEmail(String email);
	
	List<Paciente> findByFechaNacimiento(LocalDate fechaNacimiento);
	
	List<Paciente> findByDireccion(String direccion);
	
	Optional<Paciente> findByUsuario_Id(Long usuarioId);

    Optional<Paciente> findByUsuario_NombreUsuario(String nombreUsuario);
    
    Optional<Paciente> findByCedula(String cedula);
}
