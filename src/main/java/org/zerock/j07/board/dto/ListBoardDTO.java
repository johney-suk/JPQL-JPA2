package org.zerock.j07.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListBoardDTO {

    private BoardDTO boardDTO;
    private long likeCount;
    private long replyCount;
}
