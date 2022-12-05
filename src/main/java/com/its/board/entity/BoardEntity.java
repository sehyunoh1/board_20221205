package com.its.board.entity;

import com.its.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20 , nullable = false)
    private String boardWriter;
    @Column(length = 50 , nullable = false)
    private String boardTitle;
    @Column(length = 20 , nullable = false)
    private String boardPass;
    @Column(length = 500)
    private String boardContents;
    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
    BoardEntity boardEntity = new BoardEntity();
    boardEntity.setId(boardDTO.getId());
    boardEntity.setBoardTitle(boardDTO.getBoardTitle());
    boardEntity.setBoardWriter(boardDTO.getBoardWriter());
    boardEntity.setBoardContents(boardDTO.getBoardContents());
    boardEntity.setBoardPass(boardDTO.getBoardPass());
    boardEntity.setBoardHits(boardDTO.getBoardHits());
    return boardEntity;
    }

}
