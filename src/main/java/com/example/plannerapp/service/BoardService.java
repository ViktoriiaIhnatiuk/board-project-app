package com.example.plannerapp.service;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.BoardResponseDto;
import com.example.plannerapp.dto.response.BoardWithColumnsResponseDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard (BoardRequestDto boardRequestDto);

    List<BoardResponseDto> getAllBoards();

    BoardResponseDto getBoardById(Long id);

    BoardResponseDto deleteBoardById (Long id);

    BoardResponseDto renameBoardById(Long id, BoardRequestDto boardRequestDto);

    BoardResponseDto addSectionToBoardById(Long id, List<Long> sectionsIds);

    BoardResponseDto changePictureToBoardById(Long id, String path);

    BoardWithColumnsResponseDto getFullBoardInfoById(Long id);

    String removeSectionFromBoard(Long id, SectionRequestDto sectionRequestDto);
}
