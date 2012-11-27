/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.model;

/**
 * DTO of the project's Currency
 * @author zoe
 */
public interface CurrencyDTO {


    public Float getConversion();
    
    public void setConversion(Float conversion);
 
    /**
     * Id of a conversion must be a string
     * @return 
     */
    public String getId();
    
    public void setId(String name);
    
}
