package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDTO {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime regDate;
    private int hit;
}
