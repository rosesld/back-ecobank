package com.ecobank.auth.controller;

import com.ecobank.auth.model.Usuario;
import com.ecobank.auth.service.impl.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioController {

    private UsuarioServiceImpl usuarioServiceImpl;

    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(usuarioServiceImpl.saveUsuario(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioServiceImpl.updateUsuario(id, usuario));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioServiceImpl.deleteUsuario(id);
        return new ResponseEntity<>("El usuario ha sido eliminado", HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioServiceImpl.findByIdUsuario(id));
    }

    @GetMapping("/lista-usuarios")
    public ResponseEntity<List<Usuario>> findAllUsuarios(){
        return ResponseEntity.ok(usuarioServiceImpl.findAllUsuarios());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Usuario> findByNombreUsuario(@PathVariable String nombreUsuario){
        return usuarioServiceImpl.findByNombreUsuario(nombreUsuario)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
