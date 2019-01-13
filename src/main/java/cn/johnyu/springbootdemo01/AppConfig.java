package cn.johnyu.springbootdemo01;

import cn.johnyu.springbootdemo01.fetcher.AlbumFetcher;
import cn.johnyu.springbootdemo01.pojo.Album;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
//@PropertySources(value = {@PropertySource("classpath:conf.properties"),@PropertySource("classpath:database.properties")})
@ImportResource(locations = {"classpath:applicationContext.xml"})
@EnableTransactionManagement
@ComponentScan
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper(){

        return  new ObjectMapper();
    }
    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient();
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        QQDataManager manager=context.getBean(QQDataManager.class);
//        manager.save1();
        AlbumFetcher fetcher=context.getBean(AlbumFetcher.class);
       List<Album> albums= fetcher.fetchAlbumListSync(0,2);
       albums.forEach(album -> System.out.println(album));
    }
}
