package com.playermusic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongNode {
    private Song song;
    private SongNode next;
    private SongNode prev;

    public SongNode(Song song) {
        this.song = song;
        this.next = null;
        this.prev = null;
    }
}