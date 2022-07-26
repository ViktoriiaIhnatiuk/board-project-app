package com.example.plannerapp.service.impl;

import com.example.plannerapp.dto.request.BoardRequestDto;
import com.example.plannerapp.dto.request.SectionRequestDto;
import com.example.plannerapp.dto.response.BoardResponseDto;
import com.example.plannerapp.dto.response.BoardWithSectionsResponseDto;
import com.example.plannerapp.mapper.RequestDtoMapper;
import com.example.plannerapp.mapper.ResponseDtoMapper;
import com.example.plannerapp.model.Board;
import com.example.plannerapp.model.Section;
import com.example.plannerapp.repository.BoardRepository;
import com.example.plannerapp.repository.SectionRepository;
import com.example.plannerapp.service.BoardService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final RequestDtoMapper<BoardRequestDto, Board>
            requestDtoMapper;
    private final ResponseDtoMapper<BoardResponseDto, Board>
            responseDtoMapper;
    private final ResponseDtoMapper<BoardWithSectionsResponseDto, Board> boardWithSectionsResponseDtoMapper;

    private final SectionRepository sectionRepository;

    public BoardServiceImpl(BoardRepository boardRepository,
                            RequestDtoMapper<BoardRequestDto, Board> requestDtoMapper,
                            ResponseDtoMapper<BoardResponseDto, Board> responseDtoMapper,
                            ResponseDtoMapper<BoardWithSectionsResponseDto, Board> boardWithSectionsResponseDtoMapper,
                            SectionRepository sectionRepository) {
        this.boardRepository = boardRepository;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.boardWithSectionsResponseDtoMapper = boardWithSectionsResponseDtoMapper;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        return responseDtoMapper.mapToDto(boardRepository.save(requestDtoMapper.mapToModel(boardRequestDto)));
    }

    @Override
    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto getBoardById(Long id) {
        return responseDtoMapper.mapToDto(boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find board by id: " + id)));
    }

    @Override
    public BoardResponseDto deleteBoardById(Long id) {
        Board deletedBoard = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't delete board by id: " + id));
        boardRepository.deleteById(id);
        return responseDtoMapper.mapToDto(deletedBoard);
    }

    @Override
    public BoardResponseDto renameBoardById(Long id, BoardRequestDto boardRequestDto) {
        return null;
    }

    @Override
    public BoardResponseDto addSectionToBoardById(Long id, List<Long> sectionsIds) {
        Board board = boardRepository.getById(id);
        List<Long> sections = board.getSections().stream()
                .map(Section::getId)
                .collect(Collectors.toList());
        sections.addAll(sectionsIds);
        List<Section> updatedSections = sections.stream()
                .map(sectionRepository::getById)
                .collect(Collectors.toList());
        board.setSections(updatedSections);
        Board updatedBoard = boardRepository.save(board);
        return responseDtoMapper.mapToDto(updatedBoard);
    }

    @Override
    public BoardResponseDto changePictureToBoardById(Long id, String path) {
        Board board = boardRepository.getById(id);
        board.setBackgroundImagePath(path);
        Board updatedBoard = boardRepository.save(board);
        return responseDtoMapper.mapToDto(updatedBoard);
    }

    @Override
    public BoardWithSectionsResponseDto getFullBoardInfoById(Long id) {
        return boardWithSectionsResponseDtoMapper.mapToDto(boardRepository.getById(id));
    }

    @Override
    public String removeSectionFromBoard(Long id, SectionRequestDto sectionRequestDto) {
        Board board = boardRepository.getById(id);
        List<Section> sections = board.getSections();
        Section section = sectionRepository.getSectionByName(sectionRequestDto.getName());
        List <Board> boards = section.getBoards();
        Board updatedBoard = new Board();
        Section updatedSection = new Section();
        if (board != null && section != null) {
            sections.remove(section);
            board.setSections(sections);
            updatedBoard = boardRepository.save(board);
            boards.remove(board);
            updatedSection = sectionRepository.save(section);
        }
        if (updatedBoard == null) {
            throw new RuntimeException("Can't remove section from board with id: " + id);
        }
        if (updatedSection == null) {
            throw new RuntimeException("Can't update section after removing from board with id: " + id);
        }
        return "Successful";
    }

}
