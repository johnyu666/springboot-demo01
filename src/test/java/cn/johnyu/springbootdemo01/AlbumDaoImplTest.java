package cn.johnyu.springbootdemo01;

import cn.johnyu.springbootdemo01.dao.AlbumDao;
import cn.johnyu.springbootdemo01.pojo.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AlbumDaoImplTest {
    @Autowired
    AlbumDao albumDao;
    @Test
    public void test1(){
        List<Album> albums=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Album album=new Album();
            album.setAlbumId(++i);
            album.setMid("aa"+i);
            album.setName("name"+i);
            album.setPublicTime(new Date());
            albums.add(album);
        }
        int[] rs=albumDao.addAlbums(albums);
        Arrays.stream(rs).forEach(m-> System.out.println(m));
    }

}
