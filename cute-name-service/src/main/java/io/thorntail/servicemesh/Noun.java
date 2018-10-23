package io.thorntail.servicemesh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Ken Finnigan
 */
@Entity
@Table(name = "NOUN")
@NamedQueries({
        @NamedQuery(name = "Noun.findAll", query = "SELECT n FROM Noun n")
})
public class Noun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 40)
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
