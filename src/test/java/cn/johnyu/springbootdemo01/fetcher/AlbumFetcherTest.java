package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.AppConfig;
import cn.johnyu.springbootdemo01.pojo.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AlbumFetcherTest {
    @Autowired
    AlbumFetcher albumFetcher;
    int i=0;
    @Test
    public void testFetchAlbumListSync() {

        //每页100条，最后一页
        int offset = 573950;
        //offset=0;
        List<Album> albums = albumFetcher.fetchAlbumListSync(offset, 100);
        //offset += 100;
        albums.forEach(album -> System.out.println((++i)+"\t"+album.getAlbumId()+"\t"+album.getName() + "\t" + album.getSingers().get(0).getName()));

    }
}
