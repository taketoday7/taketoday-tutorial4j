package cn.tuyucheng.taketoday.mapper;

import cn.tuyucheng.taketoday.dto.PersonDTO;
import cn.tuyucheng.taketoday.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

   PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

   @Mapping(target = "id", source = "person.id", defaultExpression = "java(java.util.UUID.randomUUID().toString())")
   PersonDTO personToPersonDTO(Person person);
}