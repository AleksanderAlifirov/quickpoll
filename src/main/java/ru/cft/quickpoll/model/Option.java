package ru.cft.quickpoll.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="qp_options")
@Data
public class Option {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="option_value")
    private String value;

    @Column(name="poll_id")
    private Long pollId;
}
