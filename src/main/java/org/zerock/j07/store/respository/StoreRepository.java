package org.zerock.j07.store.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.j07.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
