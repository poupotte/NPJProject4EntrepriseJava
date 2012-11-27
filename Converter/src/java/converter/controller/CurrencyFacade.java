/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.controller;

import converter.model.Currency;
import converter.model.CurrencyDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author zoe
 */
@Stateless
public class CurrencyFacade {
    
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public CurrencyDTO createNewCurrency(String name, Float conversion) {
        Currency newCurrency = new Currency(name, conversion);
        em.persist(newCurrency);
        return newCurrency;
    }
    
    public CurrencyDTO getCurrency(String name) {
        CurrencyDTO currency = em.find(Currency.class, name);
        if (currency == null) {
            throw new EntityNotFoundException("No currency with the name " + name);
        }
        return currency;
    }
    
}
