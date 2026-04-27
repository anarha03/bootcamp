package com.example.bootcamp.mapper;

import com.example.bootcamp.dto.request.UserRequestDTO;
import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    User requestToEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO entityToResponse(User user);

}
