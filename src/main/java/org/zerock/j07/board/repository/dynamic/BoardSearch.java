package org.zerock.j07.board.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Object[]> getSearchList(String type, String keyword, Pageable pageable);
}
