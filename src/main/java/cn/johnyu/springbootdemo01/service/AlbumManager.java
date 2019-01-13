package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.dao.AlbumDao;
import cn.johnyu.springbootdemo01.fetcher.AlbumFetcher;
import cn.johnyu.springbootdemo01.pojo.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumManager {
    @Autowired
    AlbumDao albumDao;
    @Autowired
    AlbumFetcher fetcher;

    /**
     *
     * @param offset
     * @param len
     * @return 数组的length为0时，代表没有数据向数据插入
     */
    @Transactional
    public int[] fetchAlbumsAndSave(int offset,int len){
        int[] rs=null;
        List<Album> albums=albums=fetcher.fetchAlbumListSync(offset,len);
        if(!albums.isEmpty()){
            rs=albumDao.addAlbums(albums);
        }else {
            rs=new int[0];
        }
        return rs;
    }
}
