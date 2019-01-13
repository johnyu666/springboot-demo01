package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.AppConfig;
import cn.johnyu.springbootdemo01.pojo.Song;
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
public class SongFetcherTest {
    private static Logger logger= LoggerFactory.getLogger(SingerFetcherTest.class);
    @Autowired
    SongFetcher songFetcher;
    @Test
    public void testConvertQueryString(){
        System.out.println(songFetcher.convertQueryString("abc",100,20));
    }
    int index=0;
    @Test
    public void testFetchSongListSync(){
        String mid="0019iLuN2glWFi";
        List<Song> songs=songFetcher.fetchSongListSync(mid,0,100);
        songs.forEach(song -> {
            logger.info((++index)+"\t"+song.getSongname()+song.getSingers().size());
        });
    }

}
