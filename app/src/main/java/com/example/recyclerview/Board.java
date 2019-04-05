package com.example.recyclerview;

import java.io.Serializable;

public class Board implements Serializable {
    private String boardName;
    private String boardContent;


    public void setBoardTitle(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardTitle() {
        return this.boardName;
    }

    public void setBoardContent(String boardContent){
        this.boardContent = boardContent;
    }

    public String getBoardContent(){
        return this.boardContent;
    }
}
