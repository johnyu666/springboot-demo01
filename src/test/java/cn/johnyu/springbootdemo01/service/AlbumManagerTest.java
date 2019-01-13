package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AlbumManagerTest {
    @Autowired
    AlbumManger albumManger;
    @Test
    public void testFetchAlbumsAndSave(){
        int offset=573900;
        for(int i=0;i<10;i++){
            int[] rs=albumManger.fetchAlbumsAndSave(offset,100);
            offset+=100;
            System.out.println(offset+"\t:"+rs.length);
        }



    }

    /**
     * 共573964条记录：2019-1-12
     */
    @Test
    public void testFetchAlbumsAndSaveAll(){
        int[] rs=null;
        int offset=0;
        do{
            rs=albumManger.fetchAlbumsAndSave(offset,100);
            offset+=100;
            System.out.println(offset+":\t"+rs.length);
        }while (rs.length!=0);

    }
}
