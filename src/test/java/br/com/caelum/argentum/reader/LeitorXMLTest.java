package br.com.caelum.argentum.reader;

import br.com.caelum.argentum.modelo.Negociacao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class LeitorXMLTest {
    
    @Test
    public void carregaXmlComUmaNegociacaoEmListaUnitaria(){
        String xmlDeTeste = "<list>"
                                + "<negociacao>"
                                    + "<preco>43.5</preco>"
                                    + "<quantidade>1000</quantidade>"
                                    + "<data>"
                                        + "<time>1322233344455</time>"
                                    + "</data>"
                                + "</negociacao>"
                            + "</list>";
        
        LeitorXML leitor = new LeitorXML();
        
        InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
        
        List<Negociacao> negociacoes = leitor.carrega(xml);
        
        //System.out.println(negociacoes.size()) + " - "+ negociacoes.get(0).getPreco());
        
        Assert.assertTrue(negociacoes.size() == 1);
        Assert.assertEquals(43.5, negociacoes.get(0).getPreco(), 000001);
        Assert.assertEquals(1000, negociacoes.get(0).getQuantidade(), 0.000001);
               
   }
    
   @Test
   public void carregaXMLComZeroNegociacoes(){
       String xmlDeTeste = "<list></list>";
       
       LeitorXML leitor = new LeitorXML();
       
       InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
       
       List<Negociacao> negociacoes = leitor.carrega(xml);
       
       Assert.assertTrue(negociacoes.isEmpty());
       
   }
   
   @Test
   public void carregaXMLComPrecoOuQauntidadeFaltando(){
       String xmlDeTeste = "<list>" 
				+ "<negociacao>"
                                    + "<data>" 
                                        + "<time>1322244555666777</time>" 
                                    + "</data>"
				+ "</negociacao>" 
                         + "</list>";
       LeitorXML leitor = new LeitorXML();
       
       InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
       
       List<Negociacao> negociacoes = leitor.carrega(xml);
       
       Assert.assertEquals(0.0, negociacoes.get(0).getQuantidade(), 0.00001);
       
   }
   
   @Test
   public void carregaXMlComMaisDeUmaNegociacao(){
       String xmlDeTeste = "<list>" 
        			+ "<negociacao>"
                                    + "<preco>43.5</preco>" 
                                    + "<quantidade>1000</quantidade>"
                                    + "<data>" 
                                        + "<time>1322244555666777</time>" 
                                    + "</data>"
				+ "</negociacao>" 
				+ "<negociacao>"
                                    + "<preco>22.0</preco>" 
                                    + "<quantidade>10</quantidade>"
                                    + "<data>" 
        				+ "<time>44555666777</time>" 
                			+ "</data>"
				+ "</negociacao>" 
				+ "<negociacao>"
                                    + "<preco>34.0</preco>" 
                                    + "<quantidade>3000</quantidade>"
                                    + "<data>" 
                                        + "<time>1325666777</time>" 
                                    + "</data>"
				+ "</negociacao>" 
				+ "<negociacao>"
                                    + "<preco>70.0</preco>" 
                                    + "<quantidade>2000</quantidade>"
                                    + "<data>" 
                                        + "<time>2244555666777</time>" 
                                    + "</data>"
				+ "</negociacao>" 
				+ "<negociacao>"
                                    + "<preco>100.1</preco>" 
                                    + "<quantidade>200</quantidade>"
                                    + "<data>" 
                                        + "<time>1324455777</time>" 
                                    + "</data>"
				+ "</negociacao>" 							
			+ "</list>";	
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
                
                Assert.assertTrue(negociacoes.size() == 5);
                Assert.assertEquals(10, negociacoes.get(1).getQuantidade(), 0.00001);
                Assert.assertEquals(100.1, negociacoes.get(4).getPreco(), 0.00001);
                
   }
}
