package com.abcode.mongo.service;

import com.abcode.mongo.entities.Post;
import com.abcode.mongo.models.dto.PostDTO;
import com.abcode.mongo.repositories.PostRepository;
import com.abcode.mongo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<PostDTO> findAll() {
        var obj = repository.findAll();
        return obj.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    public PostDTO findById(String id) {
        var entity = getEntityById(id);
        return new PostDTO(entity);
    }

    public Post getEntityById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário encontrado."));
    }


}
