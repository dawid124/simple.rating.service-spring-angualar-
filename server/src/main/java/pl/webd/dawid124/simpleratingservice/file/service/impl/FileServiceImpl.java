package pl.webd.dawid124.simpleratingservice.file.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.file.mapper.FileMapper;
import pl.webd.dawid124.simpleratingservice.file.model.Picture;
import pl.webd.dawid124.simpleratingservice.file.service.FileService;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.path}")
    private String FILE_PATH;

    private FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public Picture createFile(byte[] bytes, long productId) {
        String base64Image = new String(bytes, StandardCharsets.UTF_8).split(",")[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);

        String path = storeFile(imageBytes);

        Picture picture = new Picture(path, productId);
        fileMapper.insertFile(picture);

        return picture;
    }

    @Override
    public FileSystemResource getFile(String path) {
        return  new FileSystemResource(FILE_PATH + path);
    }

    private String storeFile(byte[] bytes) {
        UUID uuid = UUID.randomUUID();
        String path = FILE_PATH + uuid;
        try {
            Files.write(Paths.get(path), bytes);
        } catch (IOException e) {
            LOGGER.error("error write file on disc", e);
            return null;
        }

        return uuid.toString();
    }
}
