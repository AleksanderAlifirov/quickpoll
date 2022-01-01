package ru.cft.quickpoll.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="qp_votes")
@Builder
@Data
public class Vote {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="option_id")
    private Option option;
}
