package br.com.hugo.publication.controller;

import br.com.hugo.publication.domain.Publication;
import br.com.hugo.publication.mapper.PublicationMapper;
import br.com.hugo.publication.controller.request.PublicationRequest;
import br.com.hugo.publication.service.PublicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PublicationController {

    private final PublicationService service;
    private final PublicationMapper mapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody PublicationRequest request){
        service.insert(mapper.toPublication(request));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Publication> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publication findById(@PathVariable("id") String id){
        return service.findById(id);
    }

}
