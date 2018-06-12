package pl.webd.dawid124.simpleratingservice.file.service;

import org.springframework.core.io.FileSystemResource;
import pl.webd.dawid124.simpleratingservice.file.model.Picture;

import java.io.File;

public interface FileService {

    Picture createFile(byte[] file, long productId);

    FileSystemResource getFile(String path);

    void removeFile(Picture picture);
}
