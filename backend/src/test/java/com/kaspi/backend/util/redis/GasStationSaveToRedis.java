package com.kaspi.backend.util.redis;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.dao.RedisGasStationDao;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GasStationSaveToRedis {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    GasStationDao gasStationDao;
    @Autowired
    RedisGasStationDao redisGasStationDao;

    /**
     * TODO 추후 Redis를 사용하는 경우 다시 사용.. 현재 Redis보다 DB 가 더 빠름
     * (Full sacn 인 경우)
     */

//    @Test
//    void saveAllGasStation() {
//        Iterable<GasStation> gasStationDaoAll = gasStationDao.findAll();
//        List<RedisGasStation> redisGasStations = new ArrayList<>();
//        for (GasStation gasStation : gasStationDaoAll) {
//            RedisGasStation redisGasStation = RedisGasStation.builder().stationNo(gasStation.getStationNo())
//                    .address(gasStation.getAddress())
//                    .name(gasStation.getName()).brand(gasStation.getBrand()).build();
//            redisGasStations.add(redisGasStation);
//        }
//        redisGasStationDao.saveAll(redisGasStations);
//    }

//    @Test
//    void findById() {
//        List<RedisGasStation> 유진 = gasStationsWithName("유진");
//        for (RedisGasStation redisGasStation : 유진) {
//            System.out.println("redisGasStation.getName() = " + redisGasStation.getName());
//        }
//    }
//
//    List<RedisGasStation> gasStationsWithName(String name) {
//        List<RedisGasStation> matchingGasStations = new ArrayList<>();
//        Iterable<RedisGasStation> redisGasStations = redisGasStationDao.findAll();
//        for (RedisGasStation redisGasStation : redisGasStations) {
//            if (redisGasStation.getName().contains(name)) {
//                matchingGasStations.add(redisGasStation);
//            }
//        }
//        System.out.println("matchingGasStations = " + matchingGasStations);
//        return matchingGasStations;
//    }

}
