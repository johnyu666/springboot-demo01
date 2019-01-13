package cn.johnyu.springbootdemo01.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Song {
    private Long id;
    private long songid;
    private String songmid;
    private String songname;
    @JsonProperty("interval")
    private int duration;
    private long albumid;
    private String albummid;
    @JsonProperty("singer")
    private List<Singer> singers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSongid() {
        return songid;
    }

    public void setSongid(long songid) {
        this.songid = songid;
    }

    public String getSongmid() {
        return songmid;
    }

    public void setSongmid(String songmid) {
        this.songmid = songmid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(long albumid) {
        this.albumid = albumid;
    }
    public String getAlbummid() {
        return albummid;
    }
    public void setAlbummid(String albummid) {
        this.albummid = albummid;
    }

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }
}
