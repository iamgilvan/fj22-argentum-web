package br.com.caelum.argentum.bean;


import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;
import java.net.MalformedURLException;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.ChartModel;


@ManagedBean
@SessionScoped
public class ArgentumBean {
    
    private final List<Negociacao> negociacoes;
    private final ChartModel modeloGrafico;
    
    public ArgentumBean() throws MalformedURLException{
        this.negociacoes = new ClienteWebService().getNegociacoes();
        List<Candlestick> candles = new CandlestickFactory().constroiCandles(negociacoes);
        SerieTemporal serie = new SerieTemporal(candles);
        
        GeradorModeloGrafico  geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
        geradorGrafico.plotaMediaMovelSimples();
        this.modeloGrafico = geradorGrafico.getModeloGrafico();
        
    }
    
    public List<Negociacao> getNegociacoes(){
        return negociacoes;
    }

    public ChartModel getModeloGrafico() {
        return modeloGrafico;
    } 
}
