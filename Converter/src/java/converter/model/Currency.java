/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Simon Cathébras
 * @author Zoé Bellot
 */
@Entity
public class Currency implements CurrencyDTO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;    
    private Float conversion; // Conversion to convert in euro

    public Currency() {
    }
    
    /**
     * Create a new instance of Currency
     * 
     * @param name : name of the currency
     * @param conversion : conversion to convert in euro
     */
    public Currency(String name, Float conversion){
        this.name = name;
        this.conversion = conversion;       
    }

    /**
     * Get name of the currency
     * 
     * @return the name of the currency
     */
    @Override
    public String getId() {
        return name;
    }

    /**
     * Set name of the currency
     * 
     * @param name : name of the currency
     */
    @Override
    public void setId(String name) {
        this.name = name;
    }

    /**
     * Get the conversion of the currency to convert this currency to euro
     * 
     * @return conversion of the currency
     */
    @Override
    public Float getConversion(){
        return conversion;
    }
    
    /**
     * Set the conversion of the currency
     * 
     * @param conversion : conversion between the currency and euro
     */
    @Override
    public void setConversion(Float conversion){
        this.conversion = conversion;
    }
    
    /**
     * 
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    
}
