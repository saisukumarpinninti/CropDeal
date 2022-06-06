package com.cropdeal.Repsitory;

import com.cropdeal.entity.PaymentCropDeal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface detailsrepo extends MongoRepository<PaymentCropDeal,String> {

}
