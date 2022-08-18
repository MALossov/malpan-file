package com.malpan.file.operation;

import com.malpan.file.operation.delete.Deleter;
import com.malpan.file.operation.download.Downloader;
import com.malpan.file.operation.upload.Uploader;

public interface FileOperationFactory {
    Uploader getUploader();
    Downloader getDownloader();
    Deleter getDeleter();
}