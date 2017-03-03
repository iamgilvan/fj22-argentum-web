package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.SerieTemporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class GeradorDeSerie {
    
/**
 * Serve para ajudar a fazer os testes  .
 * 
 * Recebe uma sequencia de valores e cria candles com abertura, fechamento,
 * minimo e máximo iguais, mil de volume e data de hoje. Finalmente, devolve
 * tais candle encapsulada em uma Serie Temporal
 * 
 */
   
    public static SerieTemporal criaSerie(double... valores){
        List<Candlestick> candles = new ArrayList<Candlestick>();
        for (double d : valores) {
            candles.add(new Candlestick(d, d, d, d, 1000, Calendar.getInstance()));
        }
        return new SerieTemporal(candles);
    }
}
