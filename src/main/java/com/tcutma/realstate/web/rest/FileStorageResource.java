package com.tcutma.realstate.web.rest;


import com.tcutma.realstate.service.FileStorageService;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing FileUpload.
 */
@RestController
@RequestMapping("/api/v1")
public class FileUploadResource {

    private final Logger log = LoggerFactory.getLogger(FileUploadResource.class);

    //private static final String ENTITY_NAME = "fileupload";

    private final FileStorageService fileStorageService;

    public FileUploadResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }
/*
* Upload one file
 */

    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file){

        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(fileName)
            .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri,
            file.getContentType(),file.getSize());
    }

    /*
    * POST /files: Upload multiple files
    *
    * @param files array of files to upload
    * @return ResponseEntity with status 200 (OK) and the list of uploaded files in body
    */

    @PostMapping("/files")
    @Timed
    public List<UploadFileResponse> uploadFiles(@RequestParam("files") MultipartFile[] files){
        log.debug("REST request to upload an array of files ");
        return Arrays.asList(files)
            .stream()
            .map(file ->uploadFile(file))
            .collect(Collectors.toList());

    }

    /*
     * GET /files/{fileName:.+}: Upload multiple files
     *
     * @param files array of files to upload
     * @return ResponseEntity with status 200 (OK) and the list of uploaded files in body
     */

    @GetMapping("/file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile (@PathVariable String fileName, HttpServletRequest request){

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (IOException ex){
            log.info("Could not determine file type");
        }

        // Fallback to default content type if type could not be determined
        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }
}



