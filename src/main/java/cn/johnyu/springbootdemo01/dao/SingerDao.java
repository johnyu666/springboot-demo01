package cn.johnyu.springbootdemo01.dao;

import cn.johnyu.springbootdemo01.pojo.Singer;

import java.util.List;

public interface SingerDao {
    public int addSinger(Singer singer);
    public int[] addSingers(List<Singer> singers) ;
}
