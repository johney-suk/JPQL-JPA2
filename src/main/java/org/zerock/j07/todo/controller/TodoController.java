package org.zerock.j07.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.service.TodoService;


@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<TodoDTO>> list(ListRequestDTO listRequestDTO){

        log.info(listRequestDTO);

        return ResponseEntity.ok(todoService.list(listRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody TodoDTO todoDTO){

        log.info("register..........." + todoDTO);

        Long tno = todoService.register(todoDTO);

        log.info("result......." + tno);

        return ResponseEntity.ok().body(tno);
    }

    @GetMapping("/{tno}")
    public ResponseEntity<TodoDTO> read(@PathVariable Long tno){

        TodoDTO dto = todoService.read(tno);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable Long tno){

        long deletedTno = todoService.remove(tno);

        return ResponseEntity.ok().body(deletedTno);
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno, @RequestBody TodoDTO todoDTO) {

        todoDTO.setTno(tno);

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return ResponseEntity.ok(todoDTO);
    }
}
