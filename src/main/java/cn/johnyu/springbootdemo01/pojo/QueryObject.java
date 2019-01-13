package cn.johnyu.springbootdemo01.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryObject {
    private Map<String,Integer> comm;
    private QueryBody albumlib;
    private QueryBody singerList;

    public Map<String, Integer> getComm() {
        return comm;
    }

    public void setComm(Map<String, Integer> comm) {
        this.comm = comm;
    }

    public QueryBody getAlbumlib() {
        return albumlib;
    }

    public void setAlbumlib(QueryBody albumlib) {
        this.albumlib = albumlib;
    }

    public QueryBody getSingerList() {
        return singerList;
    }

    public void setSingerList(QueryBody singerList) {
        this.singerList = singerList;
    }

    public static class QueryBody {
        private String method;
        private Map<String,Integer> param;
        private String module;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String, Integer> getParam() {
            return param;
        }

        public void setParam(Map<String, Integer> param) {
            this.param = param;
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }
    }
}
