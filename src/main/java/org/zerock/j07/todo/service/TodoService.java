package org.zerock.j07.todo.service;

import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.entity.Todo;

public interface TodoService {

    ListResponseDTO<TodoDTO> list(ListRequestDTO dto);

    Long register(TodoDTO dto);

    TodoDTO read(Long tno);

    default TodoDTO entityToDto(Todo entity){
        return TodoDTO.builder()
                .tno(entity.getTno())
                .content(entity.getContent())
                .del(entity.isDel())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }

    default Todo dtoToEntity(TodoDTO dto){

        return Todo.builder()
                .tno(dto.getTno())
                .content(dto.getContent())
                .del(dto.isDel())
                .build();
    }

    long remove(Long tno);

    TodoDTO modify(TodoDTO todoDTO);
}