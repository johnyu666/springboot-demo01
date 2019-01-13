package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.dao.SongDao;
import cn.johnyu.springbootdemo01.fetcher.SongFetcher;
import cn.johnyu.springbootdemo01.pojo.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongManager {
    @Autowired
    SongDao songDao;
    @Autowired
    SongFetcher songFetcher;

    public int[] fetchSongsAndSave(String singerMid,int offset,int len){
        int[] rs=new int[0];
        List<Song> songs=songFetcher.fetchSongListSync(singerMid,offset,len);
        if(!songs.isEmpty()) {
            rs = songDao.addAlbums(songs);
        }
        return rs;
    }

    public static void main(String[] args) {
        int[] rs = new int[0];
        System.out.println(rs.length);
    }


}
