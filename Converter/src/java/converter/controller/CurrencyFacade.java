/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.controller;

import javax.ejb.Stateless;

/**
 *
 * @author zoe
 */
@Stateless
public class CurrencyFacade {
    
    private EntityManager;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public CurrencyDTO createNewCurrency(String name, Integer conversion) {
        Currency newCurrency = new Currency(name, conversion);
        return newCurrency;
    }

}
