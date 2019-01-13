package cn.johnyu.springbootdemo01.dao;

import cn.johnyu.springbootdemo01.pojo.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SingerDaoImpl implements SingerDao {
    @Autowired
    private JdbcTemplate template;
    @Override
    @Transactional
    public int addSinger(Singer singer) {
        String sql="insert into singer (singer_id ,mid ,name ,country) values(?,?,?,?)";
        int rs=template.update(sql,singer.getSingerId(),singer.getMid(),singer.getName(),singer.getCountry());
        return rs;
    }

    /**
     * 插入数据
     * @param singers
     * @return
     */
    @Override
    @Transactional
    public int[] addSingers(List<Singer> singers) {
        String sql="insert into singer (singer_id ,mid ,name ,country) values(?,?,?,?)";
        List<Object[]> args=new ArrayList<>();
        singers.forEach(singer -> {
            Object[] objs=new Object[]{singer.getSingerId(),singer.getMid(),singer.getName(),singer.getCountry()};
            args.add(objs);
        });
        int[] rs=template.batchUpdate(sql,args);
        return rs;
    }

}
