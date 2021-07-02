package org.zerock.j07.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j07.todo.entity.Todo;
import org.zerock.j07.todo.repository.dynamic.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo,Long>, TodoSearch {

    @Query("select t from Todo t where t.content like concat('%',:keyword,'%') order by t.tno desc")
    Page<Todo> getList(String keyword, Pageable pageable);

    @Modifying
    @Query("update Todo set content =:content where tno =:tno")
    void updateContent(String content, Long tno);
}
