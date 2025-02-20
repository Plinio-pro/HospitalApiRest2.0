package ec.webmarket.restful.service.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.persistence.HorarioRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;
import ec.webmarket.restful.service.crud.HorarioService;

@Service
public class HorarioService extends GenericCrudServiceImpl<Horario, HorarioDTO> {

    @Autowired
    private HorarioRepository repository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Horario> find(HorarioDTO dto) {
        return repository.findById(dto.getId());
    }

    public List<HorarioDTO> findByDisponibilidad(Boolean disponibilidad) {
        List<Horario> horarios = repository.findByDisponibilidad(disponibilidad);
        return horarios.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    
    public Optional<HorarioDTO> findById(Long id) {
        Optional<Horario> horario = repository.findById(id);
        return horario.map(this::mapToDto);
    }

    public List<HorarioDTO> findByOdontologoId(Long odontologoId) {
        List<Horario> horarios = repository.findByOdontologo_Id(odontologoId);
        return horarios.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<HorarioDTO> findByFecha(LocalDate fecha) {
        List<Horario> horarios = repository.findByFecha(fecha);
        return horarios.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Horario mapToDomain(HorarioDTO dto) {
        return modelMapper.map(dto, Horario.class);
    }

    @Override
    public HorarioDTO mapToDto(Horario domain) {
        return modelMapper.map(domain, HorarioDTO.class);
    }
}