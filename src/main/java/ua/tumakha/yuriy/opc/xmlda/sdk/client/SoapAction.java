package ua.tumakha.yuriy.opc.xmlda.sdk.client;

/**
 * OPC XML-DA SOAP Actions.
 *
 * @author Yuriy Tumakha
 */
enum SoapAction {
    
    GET_STATUS("GetStatus"),

    BROWSE("Browse"),

    GET_PROPERTIES("GetProperties"),

    READ("Read"),

    WRITE("Write"),

    SUBSCRIBE("Subscribe"),

    SUBSCRIPTION_POLLED_REFRESH("SubscriptionPolledRefresh"),

    SUBSCRIPTION_CANCEL("SubscriptionCancel");

    private static final String ACTION_BASE = "http://opcfoundation.org/webservices/XMLDA/1.0/";

    private final String action;

    SoapAction(String action) {
        this.action = action;
    }

    String getActionPath() {
        return new StringBuilder(ACTION_BASE).append(action).toString();
    }

}
