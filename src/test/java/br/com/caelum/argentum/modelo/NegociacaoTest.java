package br.com.caelum.argentum.modelo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class NegociacaoTest {

    @Test
    public void dataDaNegociacaoEhImutavel(){
        // se criar um negocio no dia 15...
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Negociacao negociacao = new Negociacao(10, 5, calendar);

        //ainda que eu tente mudar a data para 20...
        negociacao.getData().set(Calendar.DAY_OF_MONTH, 20);

        //Ele continua no dia 15.
        Assert.assertEquals(15, negociacao.getData().get(Calendar.DAY_OF_MONTH));

    }
    //Esse teste indica sucesso cado uma exception for lançada.
    @Test (expected = IllegalArgumentException.class)
    public void naoCriaNegociacaoComDataNula() {
        new Negociacao(10, 5, null);
    }

    //Teste de verificação se a negociação aconteceu na data atual
    @Test
    public void mesmoMilissegundoEhDoMesmoDia(){
        Calendar agora = Calendar.getInstance();
        Calendar mesmoMomento = (Calendar) agora.clone();

        Negociacao negociacao = new Negociacao(40.0, 100, agora);
        Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
    }

    // Teste JUnit com horarios Diferentes e no mesmo dia
    @Test
    public void comHorariosDiferentesEhNoMesmoDia() {
        // usando GregorianCalendar(ano, mes, dia, hora, minuto)
        Calendar manha = new GregorianCalendar(2011,10,20,8,30);
        Calendar tarde = new GregorianCalendar(2011,10,20,15,30);

        Negociacao negociacao = new Negociacao(40.0,100, manha);
        Assert.assertTrue(negociacao.isMesmoDia(tarde));
    }
    // Teste JUnit com mesmo dia mas meses diferentes
    @Test
    public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia(){
        Calendar jan = new GregorianCalendar(2011,1,20,8,30);
        Calendar feb = new GregorianCalendar(2011,2,20,15,30);

        Negociacao negociacao = new Negociacao(40.0,100, jan);
        Assert.assertFalse(negociacao.isMesmoDia(feb));
    }
    // Teste JUnit com dia e meses iguais mas ano diferentes
    @Test
    public void mesmoDiaEMesMasAnosDiferesNaoSaoMesmoDia(){
        Calendar atual = new GregorianCalendar(2017,1,20,8,30);
        Calendar anterior = new GregorianCalendar(2016,1,20,15,30);

        Negociacao negociacao = new Negociacao(40.0,100, atual);
        Assert.assertFalse(negociacao.isMesmoDia(anterior));
    }

}