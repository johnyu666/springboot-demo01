package cn.johnyu.springbootdemo01.fetcher;

import cn.johnyu.springbootdemo01.pojo.Song;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SongFetcher {
    @Autowired
    private OkHttpClient client;
    @Autowired
    private ObjectMapper mapper;

    @Value("${songList.request.querystring}")
    private String songListQueryString;

    public String convertQueryString(String mid,int offset,int len){
        return songListQueryString.replace("(singermid)",mid)
                .replace("(begin)",""+offset)
                .replace("(num)",""+len);

    }

    public List<Song> fetchSongListSync(String mid,int offset,int len){

        List<Song> songs=new ArrayList<>();
        JsonNode root=null;
        try {
            Request request=new Request.Builder()
                    .url(convertQueryString(mid,offset,len)).get().build();
            Response response= client.newCall(request).execute();
            String songsJson=new String(response.body().bytes());
            root= mapper.readTree(songsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayNode nodes=(ArrayNode) root.path("data").path("list");

        nodes.forEach(node->{
            JsonNode musicDataNode=node.path("musicData");
            Song song=mapper.convertValue(musicDataNode,Song.class);
            songs.add(song);
        });
        return songs;
    }


}
