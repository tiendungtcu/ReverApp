package com.tcutma.realstate.web.rest.vm;

import java.io.Serializable;

public class UploadFileResponse implements Serializable {

    private Long id;

    private  String fileName;

    private  String fileDownloadUri;

    private String fileThumbUri;

    private String fileType;

    private long size;

    //private Instant createdDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public UploadFileResponse() {
    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileThumbUri, String fileType, long size){

        this.fileName = fileName;

        this.fileDownloadUri = fileDownloadUri;
        this.fileThumbUri = fileThumbUri;

        this.fileType = fileType;

        this.size = size;
    }

    public String getFileThumbUri() {
        return fileThumbUri;
    }

    public void setFileThumbUri(String fileThumbUri) {
        this.fileThumbUri = fileThumbUri;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
