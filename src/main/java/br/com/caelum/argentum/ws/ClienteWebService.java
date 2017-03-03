package br.com.caelum.argentum.ws;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author gilvan
 */
@WebService(serviceName = "ClienteWebService")
public class ClienteWebService {

    ///Constante onde é feita a requisição
    private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";

    
    //Retornar Lista de negociações
    public List<Negociacao> getNegociacoes() throws MalformedURLException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(URL_WEBSERVICE);

            connection = (HttpURLConnection)url.openConnection();
            InputStream content = connection.getInputStream();

            return new LeitorXML().carrega(content);
         } catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();            
        }
    }
}
