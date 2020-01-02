package com.pci.hjMos.common.api.redis;

import com.pci.hjMos.common.CommonApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommonApplication.class})
@Slf4j
public class RedisTest {

    @Resource
    private RedisService redisService;

    /**
     * 设置key-value值
     */
    @Test
    public void testSetValue(){
        redisService.set("a", "aa");
        log.info("设置a的值，{}",redisService.get("a").toString());
    }

    /**
     * 设置key的过期时间
     */
    @Test
    public void testExpire(){
        String key = "a";
        redisService.expire(key, 10);
        log.info("成功设置过期时间");
    }

    /**
     * 设置key-value且设置key的过期时间
     */
    @Test
    public void testPutExpireKey(){
        String key = "aa";
        Long expireTime = 100L;
        redisService.set(key, "hhh", expireTime);
        log.info("设置key-value，且设置key的过期时间");
    }

    /**
     * 设置key的过期时间
     */
    @Test
    public void testExpireKey(){
        String key = "a";
        Long expireTime = 100L;
        redisService.expireKey(key, expireTime);
        log.info("成功设置过期时间");
    }

    /**
     * 获取key对应的value
     */
    @Test
    public void testGet(){
        String key = "aa";
        log.info("{}的值为：{}",key,redisService.get(key).toString());
    }

    /**
     * 获取key集合中的value值
     */
    @Test
    public void testGetCollection(){

        Set<String> set = new HashSet<>();
        redisService.set("a", "aa");
        redisService.set("b", "bb");

        set.add("a");
        set.add("b");
        Collection<Object> objects = redisService.get(set);
        log.info(objects.toString());          // [aa, bb]
    }

    /**
     * 自增a的值，如果这是a的值为非数据类型，会报错
     */
    @Test
    public void testIncr(){
        redisService.set("a", 1);
        redisService.incr("a");
        log.info(redisService.get("a").toString());    // 2
    }

    /**
     * 模糊查询key的值
     */
    @Test
    public void testFuzzy(){
        redisService.set("aa", 1);
        redisService.set("ab", 1);
        Set<String> set = redisService.fuzzy("a*");
        log.info(set.toString());       // [age, ab, aa]
    }

    /**
     * 判断键值key是否存在
     */
    @Test
    public void testKeyExists(){
        String key = "a";
        Boolean exists = redisService.exists(key);
        if(exists){
            log.info("{}键存在",key);
        }else {
            log.info("{}键不存在",key);
        }
    }

    /**
     * 删除键 key
     */
    @Test
    public void testDeleteKey(){
        String key = "a";
        Boolean exists = redisService.delete(key);
        if(exists){
            log.info("{}键删除成功",key);
        }else {
            log.info("{}键删除不成功",key);
        }
    }

    /**
     * 批量删除key键集合
     */
    @Test
    public void testDelateKeys(){
        // [age, ab, aa]
        Set<String> keys = new HashSet<>();
        keys.add("aa");
        keys.add("ab");
        Long delNum = redisService.delete(keys);
        log.info("成功删除{}个key键",delNum);
        Set<String> set = redisService.fuzzy("a*");
        log.info(set.toString());    // [age]
    }

    /**
     * 设置hash类型数据
     */
    @Test
    public void testHashPut(){
        String key = "user";
        String hashKey = "name";
        String value = "zhangsan";
        redisService.hPut(key, hashKey, value);
        log.info("设置hash对象成功");
    }

    /**
     * 根据key和hashkey获取hash类型的值
     */
    @Test
    public void testHashGet(){
        String key = "user";
        String hashKey = "name";
        Object obj = redisService.hGet(key, hashKey);
        log.info("获取hash类型的值，{}",obj.toString());          // zhangsan
    }

    /**
     * 根据key和hashkey删除hash类型的key键
     */
    @Test
    public void testHashRemove(){
        String key = "user";
        String hashKey = "name";
        redisService.hRemove(key, hashKey);
    }

    /**
     * 获取hash类型key的所有属性及属性值
     */
    @Test
    public void testHashEntries(){
        String key = "user";
        Map<Object, Object> objMap = redisService.hEntries(key);
        log.info(objMap.toString());   // {age=13, name=zhangshan}
    }

    /**
     * 判断hash类型数据是否存在对象的某个属性
     */
    @Test
    public void testHashExists(){
        String key = "user";
        String hashKey = "name1";
        Boolean hExists = redisService.hExists(key, hashKey);
        if(hExists){
            log.info("存在hash类型键值{}-{}",key,hashKey);
        }else{
            log.info("不存在hash类型键值{}-{}",key,hashKey);
        }
    }

    /**
     * 批量设置值
     */
    @Test
    public void testMultiPut(){
        Map<String, Object> map = new HashMap<>();
        map.put("asd", "dsa");
        map.put("qwe", "ewq");
        map.put("zxc", "cxz");
        redisService.multiSet(map);
    }

    /**
     * 获取批量key的value值
     */
    @Test
    public void testMultiGet(){
        Set<String> set = new HashSet<>();
        set.add("asd");
        set.add("zxc");
        List<Object> list = redisService.multiGet(set);
        log.info(list.toString());      // [dsa, cxz]
    }


}
