package com.malpan.file.operation.delete;

import com.malpan.file.operation.delete.domain.DeleteFile;

public abstract class Deleter {
    public abstract void delete(DeleteFile deleteFile);
}
