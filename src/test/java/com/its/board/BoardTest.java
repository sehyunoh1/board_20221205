package com.its.board;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BoardTest {
    @Autowired
    public BoardService boardService;
    public BoardDTO newBoard(int i){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardWriter("이름" + i);
        boardDTO.setBoardTitle("제목"+ i);
        boardDTO.setBoardContents("내용"+ i);
        boardDTO.setBoardPass("1234"+ i);
        boardDTO.setBoardCreatedTime(LocalDateTime.now());
        return boardDTO;
    }
@Test
@Transactional
@Rollback(value = true)
@DisplayName("글작성 테스트")
    public void BoardSaveTest(){
    BoardDTO boardDTO = newBoard(1);
    Long savedId = boardService.save(boardDTO);
    BoardDTO savedBoard = boardService.findById(savedId);

    assertThat(boardDTO.getBoardWriter()).isEqualTo(savedBoard.getBoardWriter());
}

@Test
@Transactional
@Rollback(value = false)
@DisplayName("다수 글 작성") // 데이터를 한번에 많이 저장할때 사용
public void saveList(){
        for(int i=1; i<=20; i++){
           boardService.save(newBoard(i));
        }

    IntStream.rangeClosed(21,40).forEach(i -> {
        boardService.save(newBoard(i));
    });
}

}
