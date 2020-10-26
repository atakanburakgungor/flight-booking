package com.burakgungor.airlinebooking.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_external_reference")
@EqualsAndHashCode(of = {"href", "type"}, callSuper = false)
public class ExternalReference extends BaseEntity {

    private String href;

    private String type;

}
