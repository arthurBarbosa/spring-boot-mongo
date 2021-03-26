package com.abcode.mongo.service;

import com.abcode.mongo.entities.Post;
import com.abcode.mongo.models.dto.PostDTO;
import com.abcode.mongo.repositories.PostRepository;
import com.abcode.mongo.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
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

    public List<PostDTO> findByTitle(String text) {
        var list = repository.searchTitle(text);
        return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }


    public List<PostDTO> fullSearch(String text, String start, String end) {
        Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end, Instant.now());
        var list = repository.fullSearch(text, startMoment, endMoment);
        return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
    }

    private Instant convertMoment(String originalString, Instant alternative) {
        try {
            return Instant.parse(originalString);
        } catch (DateTimeParseException e) {
            return alternative;
        }

    }


}
