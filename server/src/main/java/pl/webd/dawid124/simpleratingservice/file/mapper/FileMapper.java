package pl.webd.dawid124.simpleratingservice.file.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;
import pl.webd.dawid124.simpleratingservice.file.model.MyFile;

@Repository
public interface FileMapper {

    @Insert("insert into pictures(label,src,product_fk) values(#{label},#{src},#{productId})")
    @Options(useGeneratedKeys=true)
    int insertFile(MyFile myFile);
}
