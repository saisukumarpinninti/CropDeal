package com.cropdeal.service;

import com.cropdeal.entity.sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public String getSequenceNumber(String sequencename){

        //Getting Sequence number
        //Getting the Document where key id is equal to sequencename
        Query q = new Query (Criteria.where("id").is(sequencename));

        //Update Sequence Number
        //The Update().inc() Method will take sqq_no as key and the increment value
        Update u = new Update().inc("seq_No",1);

        //Modifying in the Document
        sequence counter = mongoOperations.
                findAndModify(q,u,options().returnNew(true).upsert(true),sequence.class);

        //returning the value returns 1 if there is no such document or will return the sequence number
        return String.valueOf((!Objects.isNull(counter) ? counter.getSeq_No() : 1 ));
    }
}
