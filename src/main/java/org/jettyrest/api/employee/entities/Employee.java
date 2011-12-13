package org.jettyrest.api.employee.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    private String dept;

    @Temporal(value = TemporalType.DATE)
    private Date created;

    @Temporal(value = TemporalType.DATE)
    private Date modified;

    public Employee() {
        setCreated(new Date());
    }

    public Employee(String name, String dept) {
        setName(name);
        setDept(dept);
        Date now = new Date();
        setCreated(now);
        setModified(now);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
