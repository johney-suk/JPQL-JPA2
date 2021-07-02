package org.zerock.j07.todo.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.j07.todo.entity.Todo;

public interface TodoSearch {

    Todo doA();

    Page<Todo> listWithSearch(String keyword, Pageable pageable);
}
