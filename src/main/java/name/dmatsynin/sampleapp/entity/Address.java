package name.dmatsynin.sampleapp.entity;

import javax.persistence.*;
/**
 * Created by: dmatsynin
 * Date: 6/2/14
 */

@Entity
@Table(name = "address")
public class Address {

    private Long id;
    private String streetAddress;
    private String suburb;
    private String city;
    private String postcode;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "street_address", nullable = false)
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
