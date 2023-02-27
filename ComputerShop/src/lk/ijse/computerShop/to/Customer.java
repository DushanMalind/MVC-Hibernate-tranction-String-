package lk.ijse.computerShop.to;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")

public class Customer implements Serializable {
    @Id
    @GenericGenerator(name = "string_based",strategy = "lk.ijse.computerShop.generator.CustomerIDGenerator")
    @GeneratedValue(generator = "string_based")
    @Column(name = "customerId",length = 50)
    private String id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_contact")
    private String contact;

    public Customer() {
    }

    public Customer(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
