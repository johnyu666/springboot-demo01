package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.dao.SingerDao;
import cn.johnyu.springbootdemo01.fetcher.SingerFetcher;
import cn.johnyu.springbootdemo01.pojo.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QQDataManager {
    @Autowired
    private SingerDao singerDao;
    @Autowired
    private SingerFetcher fetcher;

    private int total=0;

    public void save(){
        for (int page=0;page<=9809;page++) {
            fetcher.fetchSingerList((page-1)*80, page, singers -> {
                singers.forEach(singer -> singerDao.addSinger(singer));
            });
        }
    }

    public void save1(){
        int i = 0;
        for (int page=0;page<=9809;page++) {
            List<Singer> singers = fetcher.fetchSingerListSync(page*80, -1);
            int[] rs=singerDao.addSingers(singers);
            total+=rs.length;
            System.out.println("total singer count :\t"+total);
        }
    }


}
