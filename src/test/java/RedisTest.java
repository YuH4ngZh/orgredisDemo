import com.dinge.dao.JedisClientSingle;
import com.dinge.service.AddService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("../resources/spring/spring.xml")
    public class RedisTest {

        @Resource
        private AddService addService;

        @Resource
        private JedisClientSingle jedisClientSingle;

        @Test
        public void getTest() {
            String aa = addService.setName("c","d");
            System.out.println(addService.getName("c")+"--"+aa);

            long a = addService.del("c");
            System.out.println(a);

            Set<String> keys = addService.getAllKeys();
            for(String key :keys){
                System.out.println(key);
            }
        }
        //String类型
        @Test
        public void test2(){
            jedisClientSingle.set("name", "wangh");
            jedisClientSingle.set("age", "18");
            jedisClientSingle.set("sex", "man");
            jedisClientSingle.set("addr", "man");

            long size = jedisClientSingle.dbsize();
            System.out.println("key数量:"+size);
            Iterator<String> its = jedisClientSingle.getAll().iterator();
            while(its.hasNext()){
                System.out.println("key:"+its.next());
            }
            long age = jedisClientSingle.decr("age");
            System.out.println("age:"+age);
            long d = jedisClientSingle.mdel("java.lang.String","a","v");
            System.out.println("mdel:"+d);
            long addr = jedisClientSingle.expire("addr", 100000);
            System.out.println("addr:"+addr);
            long ttl = jedisClientSingle.ttl("addr");
            System.out.println("ttl:"+ttl);
            //String nname = jedisClientSingle.keyrename("addr", "address");
            //System.out.println(nname);
        }
        //List 类型
        @Test
        public void test3(){
            jedisClientSingle.lpush("list", "1");
            jedisClientSingle.lpush("list", "2");
            jedisClientSingle.lpush("list", "3");
            jedisClientSingle.lpush("list", "1");

            long l = jedisClientSingle.llen("list");
            System.out.println("长度："+l);

            List<String> lists = jedisClientSingle.lrange("list", 0, -1);
            for(String elem:lists){
                System.out.println("list中值："+elem);
            }

            String four = jedisClientSingle.lset("list", 3, "4");
            System.out.println("第四个值改为："+four);

            jedisClientSingle.rpush("list1", "1");
            jedisClientSingle.rpush("list1", "2");
            jedisClientSingle.rpush("list1", "3");
            List<String> lists1 = jedisClientSingle.lrange("list1", 0, -1);
            for(String elem:lists1){
                System.out.println("list1中值："+elem);
            }
            long rem = jedisClientSingle.lrem("list1", 2, "1");
            System.out.println("rem:"+rem);
        }
        //SET
        @Test
        public void test4(){
            jedisClientSingle.sadd("set", "1","2","3","1");
            long l = jedisClientSingle.scard("set");
            System.out.println("基数："+l);
            Iterator<String> its = jedisClientSingle.smembers("set").iterator();
            while(its.hasNext()){
                System.out.println("值为："+its.next());
            }
            boolean b = jedisClientSingle.sismember("set", "1");
            System.out.println(b);

            long ll = jedisClientSingle.smove("set", "set1", "1");
            System.out.println(ll);
            Iterator<String> its1 = jedisClientSingle.smembers("set1").iterator();
            while(its1.hasNext()){
                System.out.println("set1值为："+its1.next());
            }
            Iterator<String> its2 = jedisClientSingle.smembers("set").iterator();
            while(its2.hasNext()){
                System.out.println("值为："+its2.next());
            }
        }

}
