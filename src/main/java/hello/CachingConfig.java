package hello;


import java.io.IOException;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheConfiguration.CacheEventListenerFactoryConfiguration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;


@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer {
	//public String CACHE_NAME = "greetingCache";
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }
    @Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager cacheManager = new EhCacheCacheManager();
        cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
        return cacheManager;
    }
    /*
    @Bean
    @Override
    public CacheManager cacheManager() {
    	
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName(CACHE_NAME);
        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        cacheConfiguration.setMaxEntriesLocalHeap(1000);
        
        
        CacheEventListenerFactoryConfiguration factoryConfig = new CacheEventListenerFactoryConfiguration();
        factoryConfig.setClass("net.sf.ehcache.distribution.jgroups.JGroupsCacheReplicatorFactory");
        factoryConfig.setProperties("replicateAsynchronously=true, replicatePuts=true, replicateUpdates=true, replicateUpdatesViaCopy=false, replicateRemovals=true");
        cacheConfiguration.addCacheEventListenerFactory(factoryConfig);
        
	    
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfiguration);

        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.newInstance(config);
        
        return new EhCacheCacheManager(cacheManager);
        
    }
*/
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}