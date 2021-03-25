package com.abcode.mongo.service;

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
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado."));
        return new UserDTO(entity);
    }
}
