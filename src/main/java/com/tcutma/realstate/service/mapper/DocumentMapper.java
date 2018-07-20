package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.DocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Document and its DTO DocumentDTO.
 */
@Mapper(componentModel = "spring", uses = {PhotoMapper.class})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    @Mapping(source = "photo.id", target = "photoId")
    DocumentDTO toDto(Document document);

    @Mapping(source = "photoId", target = "photo")
    @Mapping(target = "project", ignore = true)
    Document toEntity(DocumentDTO documentDTO);

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
