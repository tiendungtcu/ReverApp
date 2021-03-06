package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.PhotoDTO;

import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import org.mapstruct.*;

/**
 * Mapper for the entity Photo and its DTO PhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhotoMapper extends EntityMapper<PhotoDTO, Photo> {


    @Mapping(source = "fileName",target = "photoName")
    @Mapping(source = "fileDownloadUri", target = "photoUrl")
    @Mapping(source = "fileType", target = "photoMimeType")
    @Mapping(source = "fileThumbUri", target = "photoThumbnailUrl")
    @Mapping(target = "resourceId", ignore = true)
    @Mapping(target = "photoDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    Photo toPhoto(UploadFileResponse ufr);

    @Mapping(source = "photoName",target = "fileName")
    @Mapping(source = "photoUrl", target = "fileDownloadUri")
    @Mapping(source = "photoMimeType", target = "fileType")
    @Mapping(source = "photoThumbnailUrl", target = "fileThumbUri")
    @Mapping(target = "size", ignore = true)
    //@Mapping(target = "resourceType", ignore = true)
    //@Mapping(target = "resourceId", ignore = true)
    UploadFileResponse toResponse(Photo photo);



    default Photo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Photo photo = new Photo();
        photo.setId(id);
        return photo;
    }
}
