package in.co.dhdigital.missiontracker.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FURY")
public class Fury extends Member {

}
