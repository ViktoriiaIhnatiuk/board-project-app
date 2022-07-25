package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.DeletedColumnResponseDto;
import com.example.plannerapp.model.Collumn;
import org.springframework.stereotype.Component;

@Component
public class ColumnWithoutRelationsMapper implements ResponseDtoMapper<DeletedColumnResponseDto, Collumn> {
    @Override
    public DeletedColumnResponseDto mapToDto(Collumn model) {
        DeletedColumnResponseDto deletedColumnResponseDto = new DeletedColumnResponseDto();
        deletedColumnResponseDto.setId(model.getId());
        deletedColumnResponseDto.setName(model.getName());
        return deletedColumnResponseDto;
    }
}
