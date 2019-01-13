package cn.johnyu.springbootdemo01;

import cn.johnyu.springbootdemo01.pojo.Song;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mysql.cj.xdevapi.JsonArray;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

public class Test2 {
    private String url="https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&ct=24&singermid=004Be55m1SJaLk&order=listen&begin=30&num=30&songstatus=1";
    static OkHttpClient client=new OkHttpClient();
    static ObjectMapper mapper=new ObjectMapper();
    static int index=0;
    @Test
    public void testFindSongs() throws Exception{
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response= client.newCall(request)
                .execute();
        String songsJson=new String(response.body().bytes());
        System.out.println(songsJson);
        JsonNode root= mapper.readTree(songsJson);

        ArrayNode songs=(ArrayNode) root.path("data").path("list");

        songs.forEach(song->{
            JsonNode musicDataNode=song.path("musicData");
            Song sng=mapper.convertValue(musicDataNode,Song.class);
            System.out.println(sng.getSongname()+"\t"+sng.getDuration()+"\t"+sng.getSongmid());

        });




    }
}
