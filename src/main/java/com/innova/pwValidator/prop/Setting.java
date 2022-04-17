package com.innova.pwValidator.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@Entity
@Table(name="settings")
public class Setting {

    @Id
    @Column(name="name")
    private String name;
    @Column(name="min_length")
    private int minLength;
    @Column(name="max_length")
    private int maxLength;
    @Column(name="types")
    private List<PatternType> types;
    @Column(name="min_count_map")
    private Map<PatternType, Integer> minCountMap;
}
