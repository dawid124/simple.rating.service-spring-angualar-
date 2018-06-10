package pl.webd.dawid124.simpleratingservice.file.service;

import pl.webd.dawid124.simpleratingservice.file.model.MyFile;

public interface FileService {

    MyFile createFile(byte[] file, long productId);
}
