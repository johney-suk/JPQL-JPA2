package org.zerock.j07.store.entity;

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tbl_store")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private MainMenu mainmenu;


    public void changeName(String name) {
        this.name=name;
    }
}
