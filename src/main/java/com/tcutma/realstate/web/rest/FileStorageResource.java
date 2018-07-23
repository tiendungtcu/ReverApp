package com.tcutma.realstate.web.rest;


import com.tcutma.realstate.domain.enumeration.UploadType;
import com.tcutma.realstate.service.FileStorageService;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing FileUpload.
 */
@RestController
@RequestMapping("/api/v1")
public class FileStorageResource {

    private final Logger log = LoggerFactory.getLogger(FileStorageResource.class);

    //private static final String ENTITY_NAME = "fileupload";


    private final FileStorageService fileStorageService;

    @Autowired
    public FileStorageResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    /*
    * Upload one file
     */

    public UploadFileResponse uploadFile(MultipartFile file){

        UploadFileResponse uploadFileResponse = fileStorageService.storeFile(UploadType.PHOTO, file);

        return uploadFileResponse;
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
     * GET /files/{fileType}/{fileName:.+}: Download file by filename
     *
     * @param file array of files to upload
     * @param fileType type of file: photo,file,document
     * @return ResponseEntity with status 200 (OK) and the file with fileName
     */

    @GetMapping("/files/{fileType}/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile (@PathVariable(value = "fileType") int fileType,@PathVariable(value = "fileName") String fileName){

        log.info("Go go download {} with name {}",fileType,fileName);
        UploadType type = fileType==1?UploadType.PHOTO:fileType==2?UploadType.DOCUMENT:UploadType.FILE;
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(fileName);
        if (mimeType == null){
            mimeType = "image/png";
        }
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(type,fileName);
        log.info("From photoResource {} with name {}",type,fileName);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(mimeType))
            //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    /*
     * GET /files/: Get all uploaded files
     *
     * @param
     * @return ResponseEntity with status 200 (OK) and the list of uploaded files in body
     */

    @GetMapping("/files")
    public List<String> listUploadedFiles(){
        return fileStorageService.loadAllFiles(UploadType.FILE).map(path->MvcUriComponentsBuilder.fromMethodName(FileStorageResource.class,
            "downloadFile",path.getFileName().toString()).build().toString())
            .collect(Collectors.toList());
    }

}



