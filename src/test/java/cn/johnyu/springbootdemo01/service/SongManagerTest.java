package cn.johnyu.springbootdemo01.service;

import cn.johnyu.springbootdemo01.AppConfig;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SongManagerTest {
    @Autowired
    SongManager songManager;
    static Map<String, String> singersMap = new HashMap<>();

    @BeforeClass
    public static void readSingersInfo() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("singers.data");
        System.out.println(stream);
        Scanner scanner = new Scanner(stream);
        String line = null;
        while (true) {
            try {
                line = scanner.nextLine();
                if(line.trim().length()==0) break;
            } catch (NoSuchElementException e) {
                break;
            }
            String[] temp = line.split("\t");
            singersMap.put(temp[0], temp[1]);
        }
    }


    public void fetchSongsAndSave(String singerMid,int offset,int len) {
        System.out.println("正在抓取《"+singersMap.get(singerMid)+"》");
        int[] rs = null;
        do {
            System.out.println("offset:\t" + offset);
            rs = songManager.fetchSongsAndSave(singerMid, offset, len);
            offset += len;
        } while (rs.length != 0);
    }

    @Test
    public void testFetchSongsAndSave() {
        String singerMid = "004Be55m1SJaLk";
       fetchSongsAndSave(singerMid,0,150);
    }

    @Test
    public void testFetchSongsAndSaveWithList() {
        singersMap.keySet().forEach(mid->{
            fetchSongsAndSave(mid,0,150);
        });

    }
}
