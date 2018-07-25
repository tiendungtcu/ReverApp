package com.tcutma.realstate.service.impl;

import com.tcutma.realstate.config.ApplicationProperties;
import com.tcutma.realstate.domain.Photo;
import com.tcutma.realstate.domain.enumeration.UploadType;
import com.tcutma.realstate.exception.FileStorageException;
import com.tcutma.realstate.exception.MyFileNotFoundException;
import com.tcutma.realstate.repository.PhotoRepository;
import com.tcutma.realstate.service.FileStorageService;
import com.tcutma.realstate.service.mapper.PhotoMapper;
import com.tcutma.realstate.web.rest.vm.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.stream.Stream;

@Service
@Transactional
public class FileStorageServiceImpl implements FileStorageService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final Path fileLocation;

    private  final Path photoLocation;

    private final Path documentLocation;

    private final PhotoRepository photoRepository; // my added code

    private final PhotoMapper photoMapper; // my added code

    @Autowired
    public FileStorageServiceImpl(ApplicationProperties properties, PhotoRepository photoRepository, PhotoMapper photoMapper) {
        this.fileLocation = Paths.get(properties.getUploadDir());
        this.photoLocation = Paths.get(properties.getPhotoDir());
        this.documentLocation = Paths.get(properties.getDocumentDir());
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }

    @Override
    public UploadFileResponse storeFile(UploadType type, MultipartFile file) {

        // Get root path belong to file type
        Path rootLocation = type==UploadType.FILE?fileLocation:type==UploadType.PHOTO?photoLocation:documentLocation;

        // Get file path for uri
        String typePath = type==UploadType.FILE?"3/":type==UploadType.PHOTO?"1/":"2/";

        //Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // get current time stamp
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Convert current timestamp to String
        String currTimeStamp = String.valueOf(timestamp.getTime());

        // Create file name contain timestamp
        fileName = currTimeStamp +"-"+ fileName;

        // Create file download uri from filename
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/v1/files/")
            .path(typePath)
            .path(fileName)
            .toUriString();

        try {

            // Check if file is empty
            if(file.isEmpty()){
                throw new FileStorageException("Failed to store empty file "+ fileName);
            }

            // Check if the file's name contains invalid characters
            if (fileName.contains("..")){
                throw  new FileStorageException("File name contains invalid characters" +fileName);
            }

            // Get target location
            //Path targetLocation = fileStorageLocation.resolve(fileName);

            // Copy file to the target location (Replacing exist file with the same name)
            try(InputStream inputStream = file.getInputStream()){

                Files.copy(inputStream, rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }

            String fileThumbUri = "";
            // return filename, fileDownloadUri, contentType, fileSize

            UploadFileResponse ufr = new UploadFileResponse(fileName, fileDownloadUri,
                fileThumbUri, file.getContentType(),file.getSize());

            // if uploaded photo
            if (type==UploadType.PHOTO){

                // convert upload file response to Photo
                Photo photo = photoMapper.toPhoto(ufr);

                // Create new photo entity
                photo = photoRepository.save(photo);

                // map photo to ufr
                ufr = photoMapper.toResponse(photo);
            }

            return  ufr;

        }catch(IOException ex){

            ex.printStackTrace();
            throw  new FileStorageException("Could not store file "+ fileName + "Please try again",ex);
        }
    }

    @Override
    public Resource loadFileAsResource(UploadType type, String fileName) {
        try{

            // Get file path
            Path file = loadFile(type,fileName);
            log.debug("After load file type {} and filename: {}",type,fileName);

            // Get resource
            Resource resource = new UrlResource(file.toUri());
            log.debug("And resource of the file: {}",resource);

            if (resource.exists() || resource.isReadable()){

                return resource;
            }else{

                throw new MyFileNotFoundException("Could not read file " + fileName);
            }
        } catch (MalformedURLException ex){

            throw new MyFileNotFoundException("File not found "+ fileName, ex);
        }
    }

    @Override
    public Stream<Path> loadAllFiles(UploadType type) {
        Path rootLocation = type==UploadType.FILE?fileLocation:type==UploadType.PHOTO?photoLocation:documentLocation;
        log.debug("View all uploaded {} from: {}",type,rootLocation);
        try{
            return Files.walk(rootLocation,1)
                .filter(path->!path.equals(rootLocation))
                .map(rootLocation::relativize);
        } catch (IOException e){
            throw  new FileStorageException("Failed to read stored files ",e);
        }
    }

    @Override
    public Path loadFile(UploadType type,String fileName) {
        Path rootLocation = type==UploadType.FILE?fileLocation:type==UploadType.PHOTO?photoLocation:documentLocation;
        return rootLocation.resolve(fileName);
    }

    @Override
    public void deleteAllFiles() {
        FileSystemUtils.deleteRecursively(fileLocation.toFile());
    }

    /**
     *  Create upload folders
     */
    @Override
    public void init() {
        try {
            Files.createDirectories(fileLocation);
            Files.createDirectories(photoLocation);
            Files.createDirectories(documentLocation);
        }
        catch (IOException e) {
            throw new FileStorageException("Could not initialize storage", e);
        }
    }
}
