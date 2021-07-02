package org.zerock.j07.board;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.board.entity.Board;
import org.zerock.j07.board.entity.Favorite;
import org.zerock.j07.board.repository.BoardRepository;
import org.zerock.j07.board.repository.FavoriteRepository;

import java.util.Arrays;
import java.util.stream.IntStream;

@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Test
    public void testFavorite(){

        IntStream.rangeClosed(1,500).forEach(i->{

            long bno = (long) (Math.random()*200)+1;
            Board board = Board.builder()
                    .bno(bno)
                    .build();

            Favorite favorite = Favorite.builder()
                    .board(board)
                    .mark(true)
                    .actor("user"+i)
                    .build();

            favoriteRepository.save(favorite);
        });

    }


    @Test
    public void testGet1(){

        Pageable pageable = PageRequest.of(0,10);

        Page<Object[]> result = boardRepository.getData1(pageable);

        result.getContent().forEach(arr ->log.info(Arrays.toString(arr)));

    }


    @Test
    public void insertDummies(){

        IntStream.rangeClosed(1,200).forEach(i ->{
            Board board = Board.builder()
                    .title("제목..")
                    .content("content....")
                    .writer("user"+i)
                    .build();
            boardRepository.save(board);

            log.info(board);
        });//end loop

    }

    @Test
    public void testSearch(){

        Pageable pageable = PageRequest.of(0, 10);
        String type = "tcw";
        String keyword = "10";

        Page<Object[]> result = boardRepository.getSearchList(type,keyword, pageable);

    }


}
