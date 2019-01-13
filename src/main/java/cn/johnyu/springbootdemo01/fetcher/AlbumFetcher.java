package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.pojo.Album;
import cn.johnyu.springbootdemo01.pojo.QueryObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
@Component
public class AlbumFetcher {
    @Autowired
    private OkHttpClient client;
    @Autowired
    private ObjectMapper mapper;

    @Value("${albumlib.url}")
    private String albumlibUrl;
    @Value("${albumlib.request.body}")
    private String albumQuery;

    /**
     * 把查询条件，拼装成为查询用的json
     * @param offset
     * @param len
     * @return
     */
    private String convertAlbumQuery(int offset,int len){
        String result= null;
        try {
            QueryObject queryObject=mapper.readValue(albumQuery,QueryObject.class);
            queryObject.getAlbumlib().getParam().put("sin",offset);
            queryObject.getAlbumlib().getParam().put("num",len-1);
            result = mapper.writeValueAsString(queryObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param offset
     * @param len
     * @return 当无数据时，isEmpty() 为true
     */
    public List<Album> fetchAlbumListSync(int offset,int len){
        List<Album> albums=null;
        try {
            albumQuery=convertAlbumQuery(offset,len);
            RequestBody requestBody=RequestBody.create(MediaType.get("application/json"), albumQuery);
            Request request=new Request.Builder()
                    .url(albumlibUrl)
                    .post(requestBody)
                    .build();
            Response response=client.newCall(request).execute();
            //定位到所有的album的json array {"albumlib":{"data":{"list":[..所有albums]}}}
            JsonNode albumJsonArray= mapper.readTree(response.body().bytes())
                    .path("albumlib")
                    .path("data")
                    .path("list");

            albums=mapper.convertValue(albumJsonArray,new TypeReference<List<Album>>(){});

        } catch (IOException e) {
            e.printStackTrace();
        }
        return albums;
    }
}
