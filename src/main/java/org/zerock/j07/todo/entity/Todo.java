package org.zerock.j07.todo.entity;

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_todo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(nullable = false, length = 300)
    private String content;

    private boolean del;

    public void changeTitle(String content){
        this.content = content;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

}
