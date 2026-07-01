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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer BidListId;
    String account;
    String type;
    Double bidQuantity;
    Double askQuantity;
    Double bid;
    Double ask;
    String benchmark;
    Timestamp bidListDate;
    String commentary;
    String security;
    String status;
    String trader;
    String book;
    String creationName;
    Timestamp creationDate;
    String revisionName;
    Timestamp revisionDate;
    String dealName;
    String dealType;
    String sourceListId;
    String side;
}
