package org.zerock.j07.todo.repository.dynamic;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j07.todo.entity.QTodo;
import org.zerock.j07.todo.entity.Todo;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Todo doA() {

        log.warn("doA.....................");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);
        query.where(todo.tno.gt(1L));
//        query.where(todo.content.like("%9%"));
        query.orderBy(todo.tno.desc());

        query.offset(0);
        query.limit(10);

        List<Todo> result = query.fetch(); // 데이터 조회

        long count = query.fetchCount(); // 전체 count

        log.warn("=======");
        log.warn("fetch(): " + result);
        log.warn("=======");
        log.info("Total Count: " + count);

        return null;
    }

    @Override
    public Page<Todo> listWithSearch(String keyword, Pageable pageable) {
        log.warn("listWithSearch...");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        if (keyword != null && keyword.trim().length() != 0){
            query.where(todo.content.contains(keyword));
        }
        query.where(todo.tno.gt(0L)); // idx 타게끔 유도하는 코드
        query.orderBy(todo.tno.desc());

        //Pageable = 0, 10
        //paging
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> list = query.fetch();
        Long count = query.fetchCount();

        return new PageImpl<>(list, pageable,count);

    }
}
