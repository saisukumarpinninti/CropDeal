package com.cropdeal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Document(collection = "Payments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentCropDeal{
    @Transient
    private static final String SEQUENCE_Name="Payment_Sequence";
    @MongoId
    private String id;
    private String customerId;
    private String transactionAmount;
    private String transactionId;
    private String CropId;
    private String Status;

}
