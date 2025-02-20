package ec.webmarket.restful.api.v1;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.HorarioService;

@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_HORARIO })
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // 1. Crear un horario nuevo
    @PostMapping
    public ResponseEntity<?> crearHorario(@RequestBody HorarioDTO horarioDTO) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarioService.create(horarioDTO)), HttpStatus.CREATED);
    }

    // 2. Actualizar y bloquear un horario (cambiar disponibilidad)
    @PutMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquearHorario(@PathVariable Long id) {
        HorarioDTO horario = horarioService.findById(id).orElse(null);
        if (horario == null) {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "Horario no encontrado"), HttpStatus.NOT_FOUND);
        }
        horario.setDisponibilidad(false); // Se bloquea el horario
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarioService.update(horario)), HttpStatus.OK);
    }

    // 3. Obtener todos los horarios disponibles o no disponibles
    @GetMapping("/disponibilidad/{estado}")
    public ResponseEntity<?> obtenerHorariosPorDisponibilidad(@PathVariable Boolean estado) {
        List<HorarioDTO> horarios = horarioService.findByDisponibilidad(estado);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }

    // 4. Obtener horarios de un odontólogo específico
    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<?> obtenerHorariosPorOdontologo(@PathVariable Long odontologoId) {
        List<HorarioDTO> horarios = horarioService.findByOdontologoId(odontologoId);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }

    // 5. Obtener horarios de una fecha específica
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> obtenerHorariosPorFecha(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        List<HorarioDTO> horarios = horarioService.findByFecha(localDate);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }
}