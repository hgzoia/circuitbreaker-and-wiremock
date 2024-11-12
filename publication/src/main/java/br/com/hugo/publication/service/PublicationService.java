package br.com.hugo.publication.service;

import br.com.hugo.publication.domain.Publication;
import br.com.hugo.publication.mapper.PublicationMapper;
import br.com.hugo.publication.repository.PublicationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PublicationService {

    private final PublicationRepository repository;
    private final PublicationMapper mapper;
    private final CommentService commentService;

    public void insert(Publication publication){
        var entity = mapper.toPublicationEntity(publication);
        repository.save(entity);
    }

    public List<Publication> findAll(){
        var publications = repository.findAll();
        return publications.stream().map(mapper::toPublication).toList();
    }


    public Publication findById(String id){
        var publication = repository.findById(id)
                .map(mapper::toPublication)
                .orElseThrow(RuntimeException::new);

        publication.setComments(commentService.getComments(id));
        return publication;
    }
}
