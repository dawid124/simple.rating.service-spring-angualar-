package pl.webd.dawid124.simpleratingservice.type.service;

import pl.webd.dawid124.simpleratingservice.type.model.Type;

import java.util.List;

public interface TypeService {

    int createType(Type type);

    List<Type> getAllTypes();
}
