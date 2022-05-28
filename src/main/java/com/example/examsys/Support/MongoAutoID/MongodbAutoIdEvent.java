package com.example.examsys.Support.MongoAutoID;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

//非常不稳定地执行，不知道为什么
@Component
public class MongodbAutoIdEvent extends AbstractMongoEventListener<Object> {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        if (null != source) {
            ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
                @SneakyThrows
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    if (field.isAnnotationPresent(AutoId.class)) {
                        Field idField = source.getClass().getDeclaredField("id");
                        idField.setAccessible(true);
                        long id = idField.getLong(source);
                        System.out.println(id);
                        if (id == 0)
                            field.set(source, getNextId(source.getClass().getSimpleName(), id));
                        else
                            field.set(source, id);
                    }
                }
            });
        }
        super.onBeforeConvert(event);
    }

    private Long getNextId(String collectionName, long input) {
        Query query = new Query(Criteria.where("collectioinName").is(collectionName));
        Update update = new Update();
        if (input == 0)
            update.inc("aid", 1);
        else
            update.set("aid", input);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        CollectionId collectionId = mongoTemplate.findAndModify(query, update, options, CollectionId.class);
        return collectionId.getAid();
    }
}