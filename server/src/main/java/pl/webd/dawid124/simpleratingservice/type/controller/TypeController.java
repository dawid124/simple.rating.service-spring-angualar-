package pl.webd.dawid124.simpleratingservice.type.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.webd.dawid124.simpleratingservice.type.model.Type;
import pl.webd.dawid124.simpleratingservice.type.service.TypeService;

import java.io.IOException;
import java.util.List;

@RestController
public class TypeController {

    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }


    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> creteNewProductType(@RequestBody Type type)
            throws AuthenticationException, IOException {

        if (!type.valid()) {
            return new ResponseEntity<>("NOT_VALID", HttpStatus.BAD_REQUEST);
        }

        int createdId = typeService.createType(type);

        if (createdId > 0) {
            return new ResponseEntity<>(type, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllTypes()
            throws AuthenticationException, IOException {

        List<Type> allTypes = typeService.getAllTypes();

        return new ResponseEntity<>(allTypes, HttpStatus.OK);
    }
}
