package org.zerock.j07.store;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.zerock.j07.store.entity.MainMenu;
import org.zerock.j07.store.entity.Store;
import org.zerock.j07.store.respository.StoreRepository;


import java.util.Optional;
import java.util.stream.IntStream;


@ActiveProfiles("dev")
@SpringBootTest
@Log4j2
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void test(){
        log.info(storeRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Store store = Store.builder()
                    .name("namefortest"+i)
                    .latitude(123.123+i)
                    .longitude(456.456+i)
                    .address("서울시 종로구 숭인도 27-"+i+"번지")
                    .mainmenu(MainMenu.CHINA)
                    .build();
            storeRepository.save(store);
    });
    }

    @Test
    public void testSelect(){

        Optional<Store> result = storeRepository.findById(100L);

        result.ifPresent(store -> log.info(store));

        log.info(result);
    }

    @Test
    public void testUpdate() {

        Optional<Store> result = storeRepository.findById(100L);

        result.ifPresent(store -> {
            store.changeName("testforupdate");
            storeRepository.save(store);
        });

    }

    @Test
    public void testDelete() {

        storeRepository.deleteById(100L);
 //       storeRepository.delete(Store.builder().sno(99L).build());

    }

    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(1,10, Sort.by("sno").descending());

        Page<Store> result = storeRepository.findAll(pageable);

//        log.info(result);

        result.getContent().forEach(store -> log.info(store));

    }
}
