package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        Long saveId = boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();
        return saveId;
    }

    public List<BoardDTO> findAll() {
    List<BoardEntity> boardEntityList = boardRepository.findAll();
    List<BoardDTO> boardDTOList = new ArrayList<>();
    for(BoardEntity boardEntity: boardEntityList){
        BoardDTO boardDTO =BoardDTO.toDTO(boardEntity);
        boardDTOList.add(boardDTO);
    }
    return boardDTOList;
    }

    public BoardDTO findById(Long id) {
      Optional<BoardEntity> board= boardRepository.findById(id);
      if(board.isPresent()){
        BoardEntity boardEntity = board.get();
        BoardDTO boardDTO = BoardDTO.toDTO(boardEntity);
        return boardDTO;
      }else {
            return null;
      }
    }
    @Transactional
    public int updateHits(Long id){
        return boardRepository.updateHits(id);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public void update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
