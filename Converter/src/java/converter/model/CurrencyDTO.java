/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.model;

/**
 *
 * @author zoe
 */
public interface CurrencyDTO {

    public Float getConversion();
    
    public void setConversion(Float conversion);
 
    public String getId();
    
    public void setId(String name);
    
}
