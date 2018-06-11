package pl.webd.dawid124.simpleratingservice.file.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.webd.dawid124.simpleratingservice.file.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/image/{path}", method = RequestMethod.GET)
    public FileSystemResource getImage(@PathVariable("path") String path, HttpServletResponse response) throws IOException {

        return fileService.getFile(path);
    }
}
