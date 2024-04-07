package com.Event.EventOrder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"event_name","user_id"})
)
public class EventOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    Integer userId;
    @Column(name = "event_name")
    String  eventName ;

    List<Integer> orderId;
 }
