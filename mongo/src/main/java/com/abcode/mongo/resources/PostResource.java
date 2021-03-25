package com.abcode.mongo.resources;

import com.abcode.mongo.models.dto.PostDTO;
import com.abcode.mongo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(postService.findById(id));
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        var list = postService.findByTitle(text);
        return ResponseEntity.ok().body(postService.findByTitle(text));
    }

}
