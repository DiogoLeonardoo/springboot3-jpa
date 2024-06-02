package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    @RestController

    // O @RequestMapping é uma anotação que mapeia solicitações da web
    // para classes manipuladoras específicas, nesse caso, UserResource
    // manipula solicitações da web para /users que realiza operações CRUD

    @RequestMapping(value = "/users")
    public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // O método findById é um endpoint para acessar os usuários por id
    // Metodo GET para obter dados (@GetMapping)

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
        }

    // O método delete é um endpoint para deletar os usuários

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
        }

        // O método update é um endpoint para atualizar os usuários

    @PutMapping(value = "/{id}")
        public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
            obj = service.update(id, obj);
            return ResponseEntity.ok().body(obj);
        }
    }