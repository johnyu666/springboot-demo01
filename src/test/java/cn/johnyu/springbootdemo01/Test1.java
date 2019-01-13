package cn.johnyu.springbootdemo01;

import cn.johnyu.springbootdemo01.pojo.Album;
import cn.johnyu.springbootdemo01.pojo.QueryObject;
import cn.johnyu.springbootdemo01.pojo.Singer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Test1 {
    private static OkHttpClient client = new OkHttpClient();
    private static Properties prop = new Properties();
    private ObjectMapper mapper = new ObjectMapper();

    public static QueryObject createQueryObject(String queryJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        QueryObject qo = mapper.readValue(queryJson, QueryObject.class);
        return qo;
    }


    @BeforeClass
    public static void init() throws Exception {
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties"));
    }

    //查询单个歌手信息：
    @Test
    public void testFindSinger() throws Exception {
        String url = prop.getProperty("singer.url");

        String referer = prop.getProperty("singer.referer");
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .header("referer", referer)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String ss = new String(response.body().bytes());
                        System.out.println(ss);
                    }
                });
        Thread.sleep(2000);
    }


    //查询所有的专辑
    @Test
    public void testFindAllAlbums() throws Exception {
        QueryObject qo = createQueryObject(prop.getProperty("albumlib.request.body"));
        qo.getAlbumlib().getParam().put("area", -1);
        qo.getAlbumlib().getParam().put("sin",590000);
        qo.getAlbumlib().getParam().put("num", 2);
        qo.getAlbumlib().getParam().put("year", -1);

        String body = mapper.writeValueAsString(qo);

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), body);

        Request request = new Request.Builder()
                .method("POST", requestBody)
                .url(prop.getProperty("albumlib.url"))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //定位json的位置，取得唱片数组（类型：JsonArray）
                JsonNode allAlbumNodes = mapper.readTree(response.body().bytes()).path("albumlib").path("data").path("list");
                System.out.println(mapper.writeValueAsString(allAlbumNodes));
//                //转换成为List<Album>
//                List<Album> albums = mapper.convertValue(allAlbumNodes, new TypeReference<List<Album>>() {
//                });
//                albums.forEach(album -> System.out.println(album));
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
        Thread.sleep(2000);
    }

    @Test
    public void testFindAllSingers() throws Exception {
        String url = prop.getProperty("singerList.url");
        String singerListequestody = prop.getProperty("singerList.request.body");
        RequestBody body = RequestBody.create(MediaType.get("application/json"), singerListequestody);
        Request request = new Request.Builder()
                .method("POST", body)
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JsonNode singerNodes=mapper.readTree(response.body().bytes())
                                .path("singerList")
                                .path("data")
                                .path("singerlist");
                        List<Singer> singers=mapper.convertValue(singerNodes,new TypeReference<List<Singer>>(){});
                        singers.forEach(singer -> System.out.println(singer));
                    }
                });
        Thread.sleep(2000);
    }

}
