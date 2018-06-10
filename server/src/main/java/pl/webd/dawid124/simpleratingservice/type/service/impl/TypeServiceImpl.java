package pl.webd.dawid124.simpleratingservice.type.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.type.mapper.TypeMapper;
import pl.webd.dawid124.simpleratingservice.type.model.Type;
import pl.webd.dawid124.simpleratingservice.type.service.TypeService;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    private TypeMapper typeMapper;

    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public int createType(Type type) {
        return typeMapper.insertType(type);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeMapper.fetchAllTypes();
    }
}
