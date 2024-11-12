package br.com.hugo.publication.mapper;

import br.com.hugo.publication.domain.Publication;
import br.com.hugo.publication.repository.entity.PublicationEntity;
import br.com.hugo.publication.controller.request.PublicationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    PublicationEntity toPublicationEntity(Publication publication);

    Publication toPublication(PublicationEntity entity);

    Publication toPublication(PublicationRequest request);



}
