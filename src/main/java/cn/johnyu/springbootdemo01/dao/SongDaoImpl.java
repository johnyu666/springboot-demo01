package cn.johnyu.springbootdemo01.dao;

import cn.johnyu.springbootdemo01.pojo.Singer;
import cn.johnyu.springbootdemo01.pojo.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Repository
public class SongDaoImpl implements SongDao {
    @Autowired
    JdbcTemplate template;

    private String serializeSingerIds(List<Singer> singers){
        final StringBuilder sb=new StringBuilder();
        singers.forEach(singer -> {
            sb.append(singer.getId()+"-");
        });
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    @Transactional
    @Override
    public int[] addAlbums(List<Song> songs) {
        String sql="insert into song(song_id,mid,name,duration,album_id,singers) values(?,?,?,?,?,?)";
        List<Object[]> args=new ArrayList<>();
        songs.forEach(song -> {
            String singerIds=serializeSingerIds(song.getSingers());
            Object obj[]=new Object[]{song.getSongid(),song.getSongmid(),song.getSongname(),song.getDuration(),song.getAlbumid(),singerIds};
            args.add(obj);
        });
        int[] rs=template.batchUpdate(sql,args);
        return rs;
    }
}
