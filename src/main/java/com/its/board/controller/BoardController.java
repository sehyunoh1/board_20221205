package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
 private final BoardService boardService;

 @GetMapping("/save")
 public String saveform(){return "BoardPage/BoardSave";}

 @PostMapping("/save")
 public String save (@ModelAttribute BoardDTO boardDTO){
   boardService.save(boardDTO);
   return "redirect:/board/";
 }
 @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList =boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "BoardPage/BoardList";
 }
 @GetMapping("/{id}")
    public String findById(@PathVariable Long id , Model model){
     boardService.updateHits(id);
    BoardDTO boardDTO= boardService.findById(id);
    model.addAttribute("board",boardDTO);
    return "BoardPage/BoardDetail";
 }
 @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
 }
 @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
       BoardDTO boardDTO= boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "/BoardPage/BoardUpdate";
 }
@PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO){
     boardDTO.setBoardUpdatedTime(LocalDateTime.now());
     boardService.update(boardDTO);
        return "redirect:/board/"+boardDTO.getId();
}
}
