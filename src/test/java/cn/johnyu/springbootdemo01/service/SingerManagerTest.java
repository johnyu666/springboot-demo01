package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.AppConfig;
import cn.johnyu.springbootdemo01.dao.SingerDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SingerManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(SingerManagerTest.class);
    @Autowired
    SingerManager singerManager;
    @Test
    public void testFetchSongsAndSave(){
        int page=9768,total=0;
        int[] rs;
        while (true){
            rs=singerManager.fetchSongsAndSave(page*80);
            if(rs.length==0){
                logger.info("======= 共存了 {"+total+"} 名歌手 ");
                break;
            }
            logger.info("本次存储了 {"+rs.length+"} ");
            total+=80;

        }

    }
}
