/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.view;

import converter.model.CurrencyDTO;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author zoe
 */
@Named(value = "converterManager")
@ConversationScoped
public class ConverterManager implements Serializable {

    
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    /**
     * Creates a new instance of ConverterManager
     */
    public ConverterManager() {
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
    
    public Integer findConversion(CurrencyDTO currencyFrom, 
            CurrencyDTO currencyTo, Integer amount) {
        Integer conversionFrom = currencyFrom.getConversion();
        Integer conversionTo = currencyTo.getConversion();
        return amount*(conversionFrom/conversionTo);
    }
}