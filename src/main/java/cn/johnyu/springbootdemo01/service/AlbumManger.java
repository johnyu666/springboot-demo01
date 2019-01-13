package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.dao.AlbumDao;
import cn.johnyu.springbootdemo01.fetcher.AlbumFetcher;
import cn.johnyu.springbootdemo01.pojo.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumManger {
    @Autowired
    AlbumDao albumDao;
    @Autowired
    AlbumFetcher fetcher;
    @Transactional
    public int[] fetchAlbumsAndSave(int offset,int len){
        List<Album> albums=fetcher.fetchAlbumListSync(offset,len);
        int[] rs=albumDao.addAlbums(albums);
        return rs;
    }



}
