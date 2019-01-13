package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.AppConfig;
import cn.johnyu.springbootdemo01.pojo.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AlbumFetcherTest {
    private static Logger logger= LoggerFactory.getLogger(AlbumFetcherTest.class);
    @Autowired
    AlbumFetcher albumFetcher;
    int i=0;
    @Test
    public void testFetchAlbumListSync() {

        List<Album> albums=null;
        int offset = 570950,len=100,total=0;
        while (true){
            albums = albumFetcher.fetchAlbumListSync(offset, len);
            if(albums.isEmpty()){
                logger.info("============== 共抓取唱片 《{}》张 ============",total);
                break;
            }
            offset += len;
            logger.info("共 《{}》 张,最后一张唱片名称 \t《{}》",albums.size(),albums.get(albums.size()-1).getName());


        }

    }
}
