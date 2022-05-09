package com.cropdeal.Repository;
import com.cropdeal.entity.Farmer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
* Repository Interface for Farmer operations on the database
* */
@Repository
public interface FarmerRepository extends MongoRepository<Farmer, String>{
}
