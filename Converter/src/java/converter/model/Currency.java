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
 * @author zoe
 */
@Entity
public class Currency implements CurrencyDTO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;    
    private Integer conversion; // Conversion to convert in euro

    public Currency() {
    }
    
    /*
     * Create a new instance of Currency
     */
    public Currency(String name, Integer conversion){
        this.name = name;
        this.conversion = conversion;       
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public void setId(String name) {
        this.name = name;
    }

    @Override
    public Integer getConversion(){
        return conversion;
    }
    
    @Override
    public void setConversion(Integer conversion){
        this.conversion = conversion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

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
