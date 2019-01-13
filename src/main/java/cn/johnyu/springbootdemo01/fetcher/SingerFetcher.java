package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.dao.SingerDao;
import cn.johnyu.springbootdemo01.pojo.Album;
import cn.johnyu.springbootdemo01.pojo.QueryObject;
import cn.johnyu.springbootdemo01.pojo.Singer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@Component
public class SingerFetcher {

    @Autowired
    private OkHttpClient client;
    @Autowired
    private ObjectMapper mapper;

    @Value("${singerList.url}")
    private String singerList;
    @Value("${singerList.request.body}")
    private String singerListBody;



    public List<Singer> fetchSingerListSync(int offset, int curPage){
        List<Singer> singers= null;
        try {
            QueryObject queryObject=mapper.readValue(singerListBody,QueryObject.class);
            queryObject.getSingerList().getParam().put("sin",offset);
            queryObject.getSingerList().getParam().put("cur_page",curPage);
            singerListBody=mapper.writeValueAsString(queryObject);
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), singerListBody);
            Request request=new Request.Builder()
                    .method("POST",requestBody)
                    .url(singerList)
                    .build();
            Response response=client.newCall(request).execute();
            JsonNode singerNodes=mapper.readTree(response.body().bytes())
                    .path("singerList")
                    .path("data")
                    .path("singerlist");
            singers = mapper.convertValue(singerNodes,new TypeReference<List<Singer>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return singers;

    }

    /**
     *
     * @param offset
     * @param curPage
     * @param consumer
     */
    public void fetchSingerList(int offset, int curPage, Consumer<List<Singer>> consumer){

        try {
            QueryObject qo=mapper.readValue(singerListBody,QueryObject.class);
            qo.getSingerList().getParam().put("sin",offset);
            qo.getSingerList().getParam().put("cur_page",curPage);
            singerListBody=mapper.writeValueAsString(qo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), singerListBody);
        Request request=new Request.Builder()
                .method("POST",requestBody)
                .url(singerList)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        JsonNode singerNodes=mapper.readTree(response.body().bytes())
                                .path("singerList")
                                .path("data")
                                .path("singerlist");
                        List<Singer> singers=mapper.convertValue(singerNodes,new TypeReference<List<Singer>>(){});
                        consumer.accept(singers);
                    }
                });
    }

}
