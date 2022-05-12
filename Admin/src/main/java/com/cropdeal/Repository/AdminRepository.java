package com.cropdeal.Repository;

import com.cropdeal.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository Interface for Curd operations on the database
 * */
@Repository
public interface AdminRepository  extends MongoRepository<Admin,String> {
}
