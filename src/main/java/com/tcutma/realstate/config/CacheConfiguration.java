package com.tcutma.realstate.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.tcutma.realstate.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Company.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Contact.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.JobTitle.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Exchanger.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Investor.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Investor.class.getName() + ".projects", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.ProjectBuilder.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.ProjectBuilder.class.getName() + ".projects", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Document.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Photo.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Photo.class.getName() + ".properties", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Photo.class.getName() + ".projects", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Location.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.BuildingType.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.BuildingType.class.getName() + ".projects", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.BuildingType.class.getName() + ".properties", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Tag.class.getName() + ".properties", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Tag.class.getName() + ".projects", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Tag.class.getName() + ".residentialAreas", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Notification.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.SupportCategory.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.SupportCategory.class.getName() + ".articles", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Article.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Employee.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.ResidentialArea.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.ResidentialArea.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName() + ".buildingtypes", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName() + ".investors", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName() + ".projectbuilders", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Project.class.getName() + ".photos", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Property.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Property.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Property.class.getName() + ".buildingtypes", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Property.class.getName() + ".photos", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.BlogPost.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.BlogPost.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.RecruitmentInfo.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Request.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.Comment.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.ProjectUser.class.getName(), jcacheConfiguration);
            cm.createCache(com.tcutma.realstate.domain.PropertyUser.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
