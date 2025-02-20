package ec.webmarket.restful.service.crud;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.persistence.CitaRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class CitaService extends GenericCrudServiceImpl<Cita, CitaDTO> {

    @Autowired
    private CitaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Cita> find(CitaDTO dto) {
        return repository.findById(dto.getId());
    }

    public List<Cita> findByPaciente(Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }
    
    public List<Cita> findByOdontologo(Long odontologoId) {
        return repository.findByOdontologoId(odontologoId);
    }
    

    public List<CitaDTO> findByHorario(Horario horario) {
        return repository.findByHorario(horario)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    public List<CitaDTO> findByFechaYHora(LocalDateTime fechaYHora) {
        return repository.findByFechayHora(fechaYHora)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    public List<CitaDTO> findByEstado(String estado) {
        return repository.findByEstado(estado)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    public List<CitaDTO> findByMotivo(String motivo) {
        return repository.findByMotivo(motivo)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    
    public void delete(Long id) {
        Optional<Cita> cita = repository.findById(id);
        if (cita.isPresent()) {
            repository.delete(cita.get());
        } else {
            throw new NoSuchElementException("Cita con ID " + id + " no encontrada");
        }
    }
    
    @Override
    public Cita mapToDomain(CitaDTO dto) {
        return modelMapper.map(dto, Cita.class);
    }

    @Override
    public CitaDTO mapToDto(Cita domain) {
        return modelMapper.map(domain, CitaDTO.class);
    }
}
