package ru.cft.quickpoll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="qp_options")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="option_value")
    private String value;

    @Column(name="poll_id")
    private Long pollId;
}
