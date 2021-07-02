package org.zerock.j07.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.j07.common.dto.ListResponseDTO;
import org.zerock.j07.common.dto.PageMaker;
import org.zerock.j07.common.dto.ListRequestDTO;
import org.zerock.j07.todo.dto.TodoDTO;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.TodoRepository;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;


    @Override
    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {

        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),dto.getPageable());

        List<TodoDTO> dtoList = result.getContent().stream()
                .map( (todo) -> entityToDto(todo))
                .collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(),(int)result.getTotalElements());

        log.info(pageMaker);

        return ListResponseDTO.<TodoDTO>builder()
                .dtoList(dtoList)
                .pageMaker(pageMaker)
                .listRequestDTO(dto)
                .build();
    }

    @Override
    public Long register(TodoDTO dto) {

        log.info(dto);

        Todo entity = dtoToEntity(dto);

        todoRepository.save(entity);

        return entity.getTno();
    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);

        log.info(result);

        if (result.isPresent()){
            Todo todo = result.get();

            return entityToDto(todo);
        }

        return null;
    }

    @Override
    public long remove(Long tno) {

        todoRepository.deleteById(tno);

        return tno;
    }

    @Override
    public TodoDTO modify(TodoDTO todoDTO) {

        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        if(result.isPresent()){

            Todo entity = result.get();
            entity.changeTitle(todoDTO.getContent());
            entity.changeDel(todoDTO.isDel());

            Todo saveResult =  todoRepository.save(entity);

            return entityToDto(saveResult);
        }
        return null;
    }
}
