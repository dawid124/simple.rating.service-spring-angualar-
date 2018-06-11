package pl.webd.dawid124.simpleratingservice.file.service;

import pl.webd.dawid124.simpleratingservice.file.model.Picture;

import java.io.File;

public interface FileService {

    Picture createFile(byte[] file, long productId);

    File getFile(String path);
}
