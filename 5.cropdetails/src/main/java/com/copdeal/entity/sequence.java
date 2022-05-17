package com.copdeal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "sequence")
public class sequence {
    @Id
    //sequence id
    private String id;
    //Sequence number
    private int seq_No;
}
