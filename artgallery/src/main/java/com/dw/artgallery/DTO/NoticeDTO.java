package com.dw.artgallery.DTO;

import lombok.*;

@Getter
@Setter
public class NoticeDTO {
    private Long id;
    private String noticetitle;
    private String content;

    public NoticeDTO() {}

    public NoticeDTO(Long id, String noticetitle, String content) {
        this.id = id;
        this.noticetitle = noticetitle;
        this.content = content;
    }

    // getter, setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNoticetitle() { return noticetitle; }
    public void setNoticetitle(String noticetitle) { this.noticetitle = noticetitle; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}