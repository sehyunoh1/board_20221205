package com.its.board;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {
    @Autowired
    public BoardService boardService;
@Test
@Transactional
@Rollback(value = true)
    public void BoardSaveTest(){
    BoardDTO boardDTO = newBoard();
    Long savedId = boardService.save(boardDTO);
    BoardDTO savedBoard = boardService.findById(savedId);

    assertThat(boardDTO.getBoardWriter()).isEqualTo(savedBoard.getBoardWriter());


}

public BoardDTO newBoard(){
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setBoardWriter("이름");
    boardDTO.setBoardTitle("제목");
    boardDTO.setBoardContents("내용");
    boardDTO.setBoardPass("1234");
    boardDTO.setBoardCreatedTime(LocalDateTime.now());
    return boardDTO;
}
}
