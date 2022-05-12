package com.copdeal.Repository;
import com.copdeal.entity.Crop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* Repository Interface for Curd operations on the database
* */
@Repository
public interface CropRepository extends MongoRepository<Crop, String>{
    List<Crop> getListByfarmerid(String farmerid);
}
