/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.controller;

import converter.model.Currency;
import converter.model.CurrencyDTO;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Simon Cathébras
 * @author Zoé Bellot
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyFacade {
    @PersistenceContext(unitName = "converterPU")
    private EntityManager em;

    
    /**
     * Create a new instance of currency
     * 
     * @param name of the currency
     * @param conversion conversion between the currency and euro
     * @return an object Currency which corresponds to 
     * the name and the conversion
     */
    public CurrencyDTO createNewCurrency(String name, Float conversion) {
        Currency newCurrency = new Currency(name, conversion);
        em.persist(newCurrency);
        return newCurrency;
    }
    
    /**
     * get Currency which call "name"
     * 
     * @param name : the name of the currency       
     * @return CurrencyDTO which primary key is equal to "name"
     */
    public CurrencyDTO getCurrency(String name) {
        CurrencyDTO currency = em.find(Currency.class, name);
        if (currency == null) {
            throw new EntityNotFoundException("No currency with the name " + name);
        }
        return currency;
    }
    
}
