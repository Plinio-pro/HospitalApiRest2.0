package ec.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.CitaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_CITA })
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Crear nueva cita
    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody @Valid CitaDTO citaDTO) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.create(citaDTO)), HttpStatus.CREATED);
    }

    // Modificar y reprogramar una cita existente
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCita(@PathVariable Long id, @RequestBody @Valid CitaDTO citaDTO) {
        citaDTO.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.update(citaDTO)), HttpStatus.OK);
    }

    // Cancelar cita
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        citaService.delete(id); // Pasamos el id al servicio
        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Cita cancelada exitosamente."), HttpStatus.OK);
    }

    // Ver historial de citas de un paciente
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> getCitasByPaciente(@PathVariable Long pacienteId) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.findByPaciente(pacienteId)), HttpStatus.OK);
    }


    // Ver historial de citas de un odontólogo
    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<?> getCitasByOdontologo(@PathVariable Long odontologoId) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.findByOdontologo(odontologoId)), HttpStatus.OK);
    }


}
