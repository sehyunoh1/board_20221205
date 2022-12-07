package com.its.board.service;

import com.its.board.commons.PagingConst;
import com.its.board.dto.BoardDTO;
import com.its.board.dto.PagingDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.entity.BoardFileEntity;
import com.its.board.repository.BoardFileRepository;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public Long save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().isEmpty()){
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            Long saveId = boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();
            return saveId;
        }else{
          MultipartFile boardFile = boardDTO.getBoardFile();
          String originalFileName = boardFile.getOriginalFilename();
          String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
          String savePath = "D:/springboot_img/" + storedFileName;
          boardFile.transferTo(new File(savePath));

        BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
        Long savedId = boardRepository.save(boardEntity).getId();
        BoardEntity entity =  boardRepository.findById(savedId).get();
        BoardFileEntity boardFileEntity = BoardFileEntity.toSaveBoardFileEntity(entity,originalFileName,storedFileName);

         boardFileRepository.save(boardFileEntity).getId();
          return savedId;
        }

    }

    @Transactional
    public List<BoardDTO> findAll(int page) {
    List<BoardEntity> boardEntityList = boardRepository.findAll();
    List<BoardDTO> boardDTOList = new ArrayList<>();
    for(BoardEntity boardEntity: boardEntityList){
        BoardDTO boardDTO =BoardDTO.toDTO(boardEntity);
        boardDTOList.add(boardDTO);
    }
    return boardDTOList;
    }

    @Transactional // 부모 Entity에서 자식 Entity를 직접 가져올 때 필요함.
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
