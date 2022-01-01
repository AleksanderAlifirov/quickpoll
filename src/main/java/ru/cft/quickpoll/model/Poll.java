package ru.cft.quickpoll.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="qp_polls")
@Builder
@Data
public class Poll {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name="question")
    private String question;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="poll_id")
    private Set<Option> options;
}
