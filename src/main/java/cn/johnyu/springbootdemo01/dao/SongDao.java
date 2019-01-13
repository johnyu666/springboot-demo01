package cn.johnyu.springbootdemo01.dao;

import cn.johnyu.springbootdemo01.pojo.Album;
import cn.johnyu.springbootdemo01.pojo.Song;

import java.util.List;

public interface SongDao {
    int[] addAlbums(List<Song> songs);
}
