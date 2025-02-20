package ec.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class PacienteService extends GenericCrudServiceImpl<Paciente, PacienteDTO> {

    @Autowired
    private PacienteRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Paciente> find(PacienteDTO dto) {
        return repository.findById(dto.getId());
    }

    // Obtener todos los pacientes
    public List<PacienteDTO> findAll() {
        List<Paciente> pacientes = repository.findAll();
        return pacientes.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // Buscar paciente por ID
    public Optional<PacienteDTO> findById(Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        return paciente.map(this::mapToDto);
    }

    // Buscar paciente por ID de usuario
    public Optional<PacienteDTO> findByUsuarioId(Long usuarioId) {
        Optional<Paciente> paciente = repository.findByUsuario_Id(usuarioId);
        return paciente.map(this::mapToDto);
    }

    // Buscar paciente por nombre de usuario
    public Optional<PacienteDTO> findByUsuarioNombreUsuario(String nombreUsuario) {
        Optional<Paciente> paciente = repository.findByUsuario_NombreUsuario(nombreUsuario);
        return paciente.map(this::mapToDto);
    }

    // Buscar paciente por cédula
    public Optional<PacienteDTO> findByCedula(String cedula) {
        Optional<Paciente> paciente = repository.findByCedula(cedula);
        return paciente.map(this::mapToDto);
    }

    // Guardar o actualizar un paciente
    public PacienteDTO save(PacienteDTO dto) {
        Paciente paciente = mapToDomain(dto);
        paciente = repository.save(paciente);
        return mapToDto(paciente);
    }

    // Eliminar paciente por ID
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Paciente mapToDomain(PacienteDTO dto) {
        return modelMapper.map(dto, Paciente.class);
    }

    @Override
    public PacienteDTO mapToDto(Paciente domain) {
        return modelMapper.map(domain, PacienteDTO.class);
    }
}
