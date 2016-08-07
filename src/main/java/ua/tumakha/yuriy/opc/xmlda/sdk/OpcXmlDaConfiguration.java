package ua.tumakha.yuriy.opc.xmlda.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import ua.tumakha.yuriy.opc.xmlda.sdk.client.OpcXmlDaClient;

import java.util.Locale;

/**
 *  OPC XML-DA SOAP Client Configuration.
 *
 * @author Yuriy Tumakha
 */
@Configuration
public class OpcXmlDaConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(OpcXmlDaConfiguration.class);

    private static final String OPC_XMLDA_SERVER_URL_PROPERTY = "opc.xmlda.server.url";

    @Autowired
    private Environment env;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ua.tumakha.yuriy.opc.xmlda.sdk.model");
        return marshaller;
    }

    @Bean
    public WebServiceMessageSender messageSender() {
        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
        messageSender.setConnectionTimeout(11000);
        messageSender.setReadTimeout(9000);
        return messageSender;
    }

    @Bean
    public OpcXmlDaClient opcXmlDaClient(Jaxb2Marshaller marshaller, WebServiceMessageSender messageSender) {
        String serverUrl = env.getProperty(OPC_XMLDA_SERVER_URL_PROPERTY, "http://opc.xmlda.server.url");
        LOG.info("OPC XML-DA server: {}", serverUrl);

        OpcXmlDaClient client = new OpcXmlDaClient();
        client.setDefaultUri(serverUrl);
        client.setDefaultLocale(Locale.US);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setMessageSender(messageSender);
        return client;
    }

}
