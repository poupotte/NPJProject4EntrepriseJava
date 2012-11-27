/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.view;

import converter.controller.CurrencyFacade;
import converter.model.CurrencyDTO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Simon Cathébras 
 * @author Zoé Bellot
 */

@Named("converterManager")
@ConversationScoped
public class ConverterManager implements Serializable {

    @EJB
    private CurrencyFacade currencyFacade;
    private CurrencyDTO currencyFrom;
    private CurrencyDTO currencyTo;
    private Integer amount;
    private Float result;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    public void setCurrencyFrom(CurrencyDTO currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public void setCurrencyTo(CurrencyDTO currencyTo) {
        this.currencyTo = currencyTo;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public CurrencyDTO getCurrencyFrom() {
        return null;
    }

    public CurrencyDTO getCurrencyTo() {
        return null;
    }

    public Integer getAmount() {
        return null;
    }

    public Float getResult() {
        return result;
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    /**
     * Returns <code>false</code> if the latest transaction failed, otherwise <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }
    
    public void findConversion() {
        startConversation();
        Float conversionFrom = currencyFrom.getConversion();
        Float conversionTo = currencyTo.getConversion();
        result = amount*(conversionFrom/conversionTo);
    }
    
    
    public void initDatabase() {
        try {
            startConversation();
            transactionFailure = null;
            CurrencyDTO currency = currencyFacade.createNewCurrency("euro", 1.0f);
            currency = currencyFacade.createNewCurrency("sek", 0.1166f);
            currency = currencyFacade.createNewCurrency("dollar", 0.7698f);
            currency = currencyFacade.createNewCurrency("pound", 1.2350f);
            currency = currencyFacade.createNewCurrency("yuan",0.1238f);          
        } catch (Exception e) {
            handleException(e);
        }
        
        
        
    }
    

}
