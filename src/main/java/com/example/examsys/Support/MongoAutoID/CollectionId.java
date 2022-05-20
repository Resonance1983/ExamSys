package com.example.examsys.Support.MongoAutoID;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "collection_id")
@TableName("collection_id")
@Data
public class CollectionId {
    private String id;
    @Field("collectioin_name")
    private String collectionName;
    private Long aid; // 自增id
}