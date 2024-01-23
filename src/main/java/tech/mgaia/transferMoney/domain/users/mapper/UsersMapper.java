package tech.mgaia.transferMoney.domain.users.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tech.mgaia.transferMoney.domain.users.dto.UsersDTO;
import tech.mgaia.transferMoney.domain.users.model.Users;

@Mapper
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    UsersDTO toDTO(Users users);
    Users toObject(UsersDTO usersDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    Users updateObjectFromDTO(UsersDTO usersDTO, @MappingTarget Users users);

}