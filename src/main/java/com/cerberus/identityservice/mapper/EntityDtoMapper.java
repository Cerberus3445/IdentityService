package com.cerberus.identityservice.mapper;

import com.cerberus.identityservice.domain.user.UserCredential;
import com.cerberus.identityservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityDtoMapper {

    private final ModelMapper modelMapper;

    public UserDto toDto(UserCredential user){
        return this.modelMapper.map(user, UserDto.class);
    }

    public UserCredential toEntity(UserDto userDto){
        return this.modelMapper.map(userDto, UserCredential.class);
    }
}
