package com.czj.dao.cache;

import com.czj.dao.SeckillDao;
import com.czj.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试 redis Dao
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private long id = 1001;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {
        // redis 获取和添加值
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillDao.queryById(id);
            if (seckill != null) {
                redisDao.putSeckill(seckill);
                System.out.println("从db中获取秒杀商品信息并put到redis：" + seckill.getName());
            }
        } else {
            System.out.println("直接从redis中获取秒杀商品信息" + seckill.getName());
        }
    }

}
