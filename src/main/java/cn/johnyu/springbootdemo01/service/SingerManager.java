package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.dao.SingerDao;
import cn.johnyu.springbootdemo01.fetcher.SingerFetcher;
import cn.johnyu.springbootdemo01.pojo.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingerManager {
    @Autowired
    SingerDao singerDao;
    @Autowired
    SingerFetcher singerFetcher;

    public int[]  fetchSongsAndSave(int offset){
        int[] rs=null;
        List<Singer> singers=singerFetcher.fetchSingerListSync(offset);
        if(!singers.isEmpty()){
            rs=singerDao.addSingers(singers);
        }
        else{
            rs=new int[0];
        }
        return rs;
    }

}
