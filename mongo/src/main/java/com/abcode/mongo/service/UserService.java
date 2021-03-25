package com.abcode.mongo.service;

import com.abcode.mongo.entities.User;
import com.abcode.mongo.models.dto.UserDTO;
import com.abcode.mongo.repositories.UserRepository;
import com.abcode.mongo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll() {
        var users = repository.findAll();
        return users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public UserDTO findById(String id) {
        var entity = getUserById(id);
        return new UserDTO(entity);
    }

    public User getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado."));
    }

    public UserDTO insert(UserDTO dto) {
        var entity = new User();
        copyDtoToEntity(dto, entity);
        entity = repository.insert(entity);
        return new UserDTO(entity);
    }

    public UserDTO update(String id, UserDTO dto) {
        var entity = getUserById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }
}
