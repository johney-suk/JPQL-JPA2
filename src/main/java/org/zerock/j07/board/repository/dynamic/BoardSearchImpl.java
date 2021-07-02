package org.zerock.j07.board.repository.dynamic;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.entity.QBoard;
import org.zerock.j07.board.entity.QFavorite;
import org.zerock.j07.board.entity.QReply;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> getSearchList(String type, String keyword, Pageable pageable) {
        log.warn("............................getSearch...........................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QFavorite favorite = QFavorite.favorite;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.leftJoin(favorite).on(favorite.board.eq(board));

        JPQLQuery<Tuple> tuple = query.select(board, reply.countDistinct(), favorite.countDistinct());

        if (keyword != null && type != null){
            BooleanBuilder condition = new BooleanBuilder();

            String[] typeArr = type.split("");

            for(String t: typeArr){
                if (t.equals("t")){
                    condition.or(board.title.contains(keyword));
                }else if (t.equals("w")){
                    condition.or(board.writer.contains(keyword));
                }else if(t.equals("c")){
                    condition.or(board.content.contains(keyword));
                }
            }//end for

            tuple.where(condition);
        }

        tuple.where(board.bno.gt(0L));
        tuple.groupBy(board);
        tuple.orderBy(board.bno.desc());

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList =
                tupleList.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());

        long totalCount = tuple.fetchCount();

        return new PageImpl<>(arrList, pageable, totalCount);
    }
}
