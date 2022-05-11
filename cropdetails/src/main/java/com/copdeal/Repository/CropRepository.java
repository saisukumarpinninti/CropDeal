package com.copdeal.Repository;
import com.copdeal.entity.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
* Repository Interface for Farmer operations on the database
* */
@Repository
public interface CropRepository extends MongoRepository<Crop, String>{
}
