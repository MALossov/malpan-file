package com.malpan.file.operation;

import javax.annotation.Resource;

import com.malpan.file.operation.delete.Deleter;
import com.malpan.file.operation.delete.product.LocalStorageDeleter;
import com.malpan.file.operation.download.Downloader;
import com.malpan.file.operation.download.product.LocalStorageDownloader;
import com.malpan.file.operation.upload.Uploader;
import com.malpan.file.operation.upload.product.LocalStorageUploader;

import org.springframework.stereotype.Component;

@Component
public class LocalStorageOperationFactory implements FileOperationFactory{

    @Resource
    LocalStorageUploader localStorageUploader;
    @Resource
    LocalStorageDownloader localStorageDownloader;
    @Resource
    LocalStorageDeleter localStorageDeleter;
    @Override
    public Uploader getUploader() {
        return localStorageUploader;
    }

    @Override
    public Downloader getDownloader() {
        return localStorageDownloader;
    }

    @Override
    public Deleter getDeleter() {
        return localStorageDeleter;
    }


}