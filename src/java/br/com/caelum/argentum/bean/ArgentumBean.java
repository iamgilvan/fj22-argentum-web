package br.com.caelum.argentum.bean;


import java.net.MalformedURLException;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.ws.ClienteWebService;


@ManagedBean
@SessionScoped
public class ArgentumBean {
    
    private List<Negociacao> negociacoes;
    
    public ArgentumBean() throws MalformedURLException{
        negociacoes = new ClienteWebService().getNegociacoes();
    }
    
    public List<Negociacao> getNegociacoes(){
        return negociacoes;
    }
  
}
