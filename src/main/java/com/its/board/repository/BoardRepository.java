package com.its.board.repository;

import com.its.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Modifying // update or delete 할때는 modifying 어노테이션 작성
    // 쿼리는 Entity 기준으로 작성
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
    // 아래는 DB 기준으로 작성한 코드
//    @Query(value = "update board_table b set b.board_hits = b.board_hits + 1 where b.id = :id", nativeQuery=true)
     int updateHits(@Param("id") Long id);
    }

