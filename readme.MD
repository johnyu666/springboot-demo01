## 抓取QQ上的音乐信息

## 使用技术列表：
spring
spring-jdbc
spring-test
okhttp
jackson-json

## 特殊处理：

### 歌曲的查找方法：
1. 按归歌手的singer_mid进行查询。
2. 合唱歌曲：在song数据表中，增加singers字段，以"singer_id1-singer_id2"形式保存数据

### 单个歌手查询：
1. url:https://c.y.qq.com/splcloud/fcgi-bin/fcg_get_singer_desc.fcg?singermid=004Be55m1SJaLk&utf8=1&outCharset=utf-8&format=xml
2. 注意：必须加入--   referer:https://c.y.qq.com/xhr_proxy_utf8.html



### 常见歌手列表：
002u0TJy47WWOj	林忆莲
000cISVf2QqLc6	莫文蔚
001rbe4h38XQBr	齐豫
001reYD01YuOQ8	品冠
0022mAua0xcjon	苏芮
003deq1Q3zMiFx	无印良品
0036ERbx4IUm9V	伍佰
0001v4XU1cZxPy	张国荣
003JUs4X1xzjld	赵传
003NThQh3ujqIo	周华健
0043OO7j3TsLnO	杨坤
003yM0OT1EPY0y	郭富城
0019iLuN2glWFi	孙楠
003JGrNQ3RjelA	张惠妹
000B2mbx2kEMLz	张雨生
003Nz2So3XXYek	陈奕迅
002tkdEU4gLVqO	萧亚轩
0031dOOZ1zyvZw	许茹芸
0000mFvh1jtLcz	张信哲
000wzGa32NPFZi	赵薇
004Be55m1SJaLk	张学友
00493hsy19IASE	蔡淳佳
004UaX1n1X7bEa	周迅
001QY1cc2SIqpB	水木年华

//根据歌手的mid查询歌曲
https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&ct=24&singermid=004Be55m1SJaLk&order=listen&begin=30&num=30&songstatus=1
https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?g_tk=5381&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&ct=24&singermid=004Be55m1SJaLk&order=listen&begin=0&num=2&songstatus=1