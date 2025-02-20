package ec.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.persistence.OdontologoRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class OdontologoService extends GenericCrudServiceImpl<Odontologo, OdontologoDTO> {

    @Autowired
    private OdontologoRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<Odontologo> find(OdontologoDTO dto) {
        return repository.findById(dto.getId());
    }

    public List<OdontologoDTO> findAll() {
        List<Odontologo> odontologos = repository.findAll();
        return odontologos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Optional<OdontologoDTO> findById(Long id) {
        Optional<Odontologo> odontologo = repository.findById(id);
        return odontologo.map(this::mapToDto);
    }

    public Optional<OdontologoDTO> findByUsuarioId(Long usuarioId) {
        Optional<Odontologo> odontologo = repository.findByUsuario_Id(usuarioId);
        return odontologo.map(this::mapToDto);
    }

    public Optional<OdontologoDTO> findByUsuarioNombreUsuario(String nombreUsuario) {
        Optional<Odontologo> odontologo = repository.findByUsuario_NombreUsuario(nombreUsuario);
        return odontologo.map(this::mapToDto);
    }

    public Optional<OdontologoDTO> findByCedula(String cedula) {
        Optional<Odontologo> odontologo = repository.findByCedula(cedula);
        return odontologo.map(this::mapToDto);
    }

    public OdontologoDTO save(OdontologoDTO dto) {
        Odontologo odontologo = mapToDomain(dto);
        odontologo = repository.save(odontologo);
        return mapToDto(odontologo);
    }

    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Odontologo mapToDomain(OdontologoDTO dto) {
        return modelMapper.map(dto, Odontologo.class);
    }

    @Override
    public OdontologoDTO mapToDto(Odontologo domain) {
        return modelMapper.map(domain, OdontologoDTO.class);
    }
}
