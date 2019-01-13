package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.AppConfig;
import cn.johnyu.springbootdemo01.pojo.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SingerFetcherTest {
    private static Logger logger= LoggerFactory.getLogger(SingerFetcherTest.class);

    @Autowired
    SingerFetcher singerFetcher;
    int index=0;
    @Test
    public void testFetchSingerListSync(){
        int total=0;
        List<Singer> singers=new ArrayList<>();
        int page=9758;
        while (true){
            singers=singerFetcher.fetchSingerListSync((page++)*80);
            if(singers.isEmpty()){
                logger.info("==========抓取总数：《 {} 》============",total);
                break;
            }
            total+=singers.size();
            logger.info("本次抓取歌手的数量：《 {} 》本页最后一个歌手：\t《 {} 》",singers.size(),singers.get(singers.size()-1).getName());
        }

    }
}
