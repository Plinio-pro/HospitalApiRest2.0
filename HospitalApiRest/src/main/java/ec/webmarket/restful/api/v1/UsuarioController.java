package ec.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.service.crud.UsuarioService;

import java.util.Optional;

@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_USUARIO })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Registro de nuevos usuarios
    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioDTO dto) {
        UsuarioDTO savedUser = usuarioService.register(dto);
        return ResponseEntity.ok(savedUser);
    }

    // Autenticación de usuarios
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestParam String nombreUsuario, @RequestParam String clave) {
        Optional<UsuarioDTO> user = usuarioService.authenticate(nombreUsuario, clave);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    // Asignación de roles (paciente u odontólogo)
    @PutMapping("/assignRole/{id}")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @RequestParam Boolean tipoUsuario) {
        boolean success = usuarioService.assignRole(id, tipoUsuario);
        return success ? ResponseEntity.ok("Rol asignado correctamente") : ResponseEntity.notFound().build();
    }

    // Recuperación y actualización de contraseñas
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestParam String newPassword) {
        boolean success = usuarioService.updatePassword(id, newPassword);
        return success ? ResponseEntity.ok("Contraseña actualizada correctamente") : ResponseEntity.notFound().build();
    }
}

