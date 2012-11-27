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
    private String currencyFrom;
    private String currencyTo;
    private Integer amount;
    private Float result = 0.0f;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    /**
     * set the original currency
     *
     * @param currencyFrom
     */
    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    /**
     * set the destination currency
     *
     * @param currencyTo
     */
    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    /**
     * set the amount of the conversion
     *
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * set the result of the conversion
     *
     * @param result
     */
    public void setResult(Float result) {
        this.result = result;
    }

    /**
     * not used
     *
     * @return
     */
    public String getCurrencyFrom() {
        return null;
    }

    /**
     * not used
     *
     * @return
     */
    public String getCurrencyTo() {
        return null;
    }

    /**
     * not used
     *
     * @return
     */
    public Integer getAmount() {
        return null;
    }

    /**
     * return the result of convertion
     *
     * @return
     */
    public Float getResult() {
        return result;
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    /**
     * Start the conversation with the bean
     */
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     * stop the conversation with the beans
     */
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    /**
     * Returns
     * <code>false</code> if the latest transaction failed, otherwise
     * <code>false</code>.
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

    /**
     * compute the conversion from
     * <code>currencyFrom</code> to
     * <code>currencyTo</code> and store the result into
     * <code>result</code>
     */
    public void findConversion() {
        startConversation();
        Float conversionFrom = (currencyFacade.getCurrency(currencyFrom)).getConversion();
        Float conversionTo = (currencyFacade.getCurrency(currencyTo)).getConversion();
        result = amount * (conversionFrom / conversionTo);
    }

    /**
     * initialisation of the database by adding some references conversions
     */
    public void initDatabase() {
        try {
            startConversation();
            transactionFailure = null;
            if (currencyFacade.getCurrency("euro") == null) {
                CurrencyDTO currency = currencyFacade.createNewCurrency("euro", 1.0f);
                currency = currencyFacade.createNewCurrency("sek", 0.1166f);
                currency = currencyFacade.createNewCurrency("dollar", 0.7698f);
                currency = currencyFacade.createNewCurrency("pound", 1.2350f);
                currency = currencyFacade.createNewCurrency("yuan", 0.1238f);
            }
        } catch (Exception e) {
            handleException(e);
        }



    }
}
