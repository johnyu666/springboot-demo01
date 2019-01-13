package cn.johnyu.springbootdemo01.dao;

import cn.johnyu.springbootdemo01.pojo.Album;

import java.util.List;

public interface AlbumDao {
    int[] addAlbums(List<Album> albums);
}
