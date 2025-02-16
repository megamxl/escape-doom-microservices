package at.escapedoom.session.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_entity1")
public class TestEntity {
    @Id
    @GeneratedValue
    Integer id;

    String name;

}
