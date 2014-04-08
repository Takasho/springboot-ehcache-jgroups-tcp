package hello;

import java.util.Date;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
@Component
public class Speaker {
    @Cacheable(value = "greetingCache", key = "#word")
    public String SayHello(String word) {
        System.out.println("Key: " + word + " not cached, it's now in cache");
        return word + "#" + new Date().toString();
    }
 
    @CacheEvict(value = "greetingCache", key = "#word")
    public String SayHelloClear(String word) {
        System.out.println("Clear all keys");
        return "Ok";
    }
}