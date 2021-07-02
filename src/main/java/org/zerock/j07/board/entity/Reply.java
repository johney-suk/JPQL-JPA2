package org.zerock.j07.board.entity;

import lombok.*;
import org.zerock.j07.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_reply", indexes = @Index(name="idx_reply_board", columnList = "board_bno"))
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = {"board"})
@ToString
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
