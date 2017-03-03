package br.com.caelum.argentum.grafico;

import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.SerieTemporal;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author gilvan
 */
public class GeradorModeloGrafico {

    private final SerieTemporal serie;
    private final int comeco;
    private final int fim;
    private final LineChartModel modeloGrafico;
    
    
     //atributos: serie, começo fim e grafico
    public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim){
        this.serie = serie;
        this.comeco = comeco;
        this.fim = fim;
        this.modeloGrafico = new LineChartModel();
    }
    
    public void plotaMediaMovelSimples(){
        MediaMovelSimples indicador = new MediaMovelSimples();
        LineChartSeries chartSerie = new LineChartSeries("MMS - Fechamento");
        
        for (int i = comeco; i <= fim; i++) {
            double valor = indicador.calcula(i, serie);
            chartSerie.set(i, valor);
        }
        this.modeloGrafico.addSeries(chartSerie);
        this.modeloGrafico.setLegendPosition("w");
        this.modeloGrafico.setTitle("Indicadores");
    }
     
    public ChartModel getModeloGrafico(){
        return this.modeloGrafico;
   }
    
}
