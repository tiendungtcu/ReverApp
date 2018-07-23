package com.tcutma.realstate.service;

import com.tcutma.realstate.domain.enumeration.UploadType;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    /**
     *  Init upload path
     *
     */
    void init();

    /**
     * Save a file.
     *
     *
     * @param type
     * @param file the file to save
     * @return the string contain download url
     */
    UploadFileResponse storeFile(UploadType type, MultipartFile file) ;

    /**
     * Get a file.
     *
     * @param fileName the file to download
     * @return the resource
     */
    Resource loadFileAsResource(UploadType type, String fileName);

    /**
     *  Load all filename
     * @param
     * @return
     */

    Stream<Path> loadAllFiles(UploadType type);

    /**
     *  Find full path to filename
     *
     */

    Path loadFile(UploadType type,String fileName);

    /**
     *  Delete all files
     *
     */
    void deleteAllFiles();

}
