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
 * @author zoe
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CurrencyFacade {
    @PersistenceContext(unitName = "converterPU")
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
