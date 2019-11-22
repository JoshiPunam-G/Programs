package com.bridgelabz.fundoo.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoConfiguration extends AbstractMongoConfiguration  {


    @Value("${spring.data.mongodb.database:test}")
    private String database;

    @Value("${spring.data.mongodb.host:localhost}:${spring.data.mongodb.port:27017}")
    private String host;

    @Autowired
    private MappingMongoConverter mongoConverter;

    // Converts . into a mongo friendly char
    @PostConstruct
    public void setUpMongoEscapeCharacterConversion() {
        mongoConverter.setMapKeyDotReplacement("_");
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    public Mongo mongo() throws Exception {
        return new MongoClient(host);
    }

    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        converterList.add(new MongoLocalDateTimeFromStringConverter());
        return new CustomConversions(null, converterList);
    }

    private static final class MongoLocalDateTimeFromStringConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            return source == null ? null : LocalDateTime.parse(source);
        }
    }

	@Override
	public MongoClient mongoClient() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
