package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidList {
    @Id
    @Column(name = "bid_list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer id;
    private   String account;
    private  String type;
    private  Double bidQuantity;
    private  Double askQuantity;
    private  Double bid;
    private  Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
    private  String security;
    private  String status;
    private  String trader;
    private   String book;
    private   String creationName;
    private   Timestamp creationDate;
    private   String revisionName;
    private   Timestamp revisionDate;
    private   String dealName;
    private   String dealType;
    private   String sourceListId;
    private   String side;

}
