package com.example.plannerapp.mapper;

import com.example.plannerapp.dto.response.DeletedSectionResponseDto;
import com.example.plannerapp.model.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionWithoutRelationsMapper implements ResponseDtoMapper<DeletedSectionResponseDto, Section> {
    @Override
    public DeletedSectionResponseDto mapToDto(Section model) {
        DeletedSectionResponseDto deletedSectionResponseDto = new DeletedSectionResponseDto();
        deletedSectionResponseDto.setId(model.getId());
        deletedSectionResponseDto.setName(model.getName());
        return deletedSectionResponseDto;
    }
}
