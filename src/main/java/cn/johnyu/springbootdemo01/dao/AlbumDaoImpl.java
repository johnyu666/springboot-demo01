package cn.johnyu.springbootdemo01.dao;

import cn.johnyu.springbootdemo01.pojo.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AlbumDaoImpl implements AlbumDao {
    @Autowired
    private JdbcTemplate template;
    @Override
    public int[] addAlbums(List<Album> albums) {
        List<Object[]> args=new ArrayList<>();
        String sql="insert into album (album_id,name,mid,public_time,main_singer_id) values(?,?,?,?,?)";
        albums.forEach(album -> args.add(new Object[]{album.getAlbumId(),album.getName(),album.getMid(),album.getPublicTime(),album.getSingers().get(0).getSingerId()}));
        return template.batchUpdate(sql,args);
    }
}
