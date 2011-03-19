package org.asesauta.anapalabra;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Divide palabras en sílabas y localiza la sílaba tónica.
 * @author Jorge Tercero
 */
public class Silabeador
{
	  String[][] conversiones = {
	    {"ch", "@"},{"ll", "#"},{"gue", "%e"},{"gué", "%é"},{"gui", "%i"},{"guí", "%í"},
	    {"qu", "&"},{"rr", "$"},{"ya","|a"},{"ye","|e"},{"yi","|i"},{"yo","|o"},{"yu","|u"}
	  };
	  String[] abiertas = {"a", "á", "e", "é", "o", "ó"};
	  String[] cerradas = {"i", "u", "ü", "y"};
	  String[] cerradas_tilde = {"í", "ú"};
	  Pattern patron_tilde = Pattern.compile("á|é|í|ó|ú");
	  Pattern patron_vocal_n_s = Pattern.compile(".*(á|é|í|ó|ú|a|e|i|o|u|n|s)");
	  String enye = "ñ";
	  
	  public String[] getVocales() {
		  int size = abiertas.length+cerradas.length+cerradas_tilde.length;
		  String[] vocales = new String[size];
		  int i = 0;
		  for(int j = 0; j<abiertas.length; j++) {
			  vocales[i]=abiertas[j];
			  i++;
		  }
		  for(int j = 0; j<cerradas.length; j++) {
			  vocales[i]=abiertas[j];
			  i++;
		  }
		  for(int j = 0; j<cerradas_tilde.length; j++) {
			  vocales[i]=abiertas[j];
			  i++;
		  }
		  return vocales;
	  }
	  
	  public String[] getTierrasRaras() {
		  int size = getVocales().length+1;
		  String[] raras = new String[size];
		  for(int i = 0; i<getVocales().length; i++) {
			  raras[i]=abiertas[i];
		  }
		  raras[size]=enye;
		  return raras;
	  }

	  public Pattern getPattern()
	  {
		  StringBuffer regex = new StringBuffer(getTierrasRaras()[0]);
		  for (int i=1; i<getTierrasRaras().length; i++) {
			  regex.append("|").append(getTierrasRaras()[i]);
		  }
		  return Pattern.compile(regex.toString());
	  }

	  /**
	   * Devuelve una colección de sílabas representando la palabra <code>w</code>.
	   */
	  public ArrayList<String> silabear(String w)
	  {
	    w = format(w);
	    int corte; 
	    String silaba;
	    ArrayList<String> silabas = new ArrayList<String>();
	    while(w.length()>0)
	    {
	      corte = next_s(w)+1;
	      silaba = unformat( w.substring(0,corte) );
	      w = w.substring(corte);
	      silabas<<silaba
	    }
	    logger.log(level, silabas.toString())
	    silabas
	  }
	  
	  
	  public int next_s(String w)
	  {
	    char[] a = w.toCharArray();

	    // excepción: subrayar
		if (a[0]=='s' && a[1]=='u' && a[2]=='b' && a[3]=='r') return 2;

	    int vocal = a.indexOf{ esVocal(it) }

	    // sabemos que todas las letras anteriores a vocal + vocal forman parte de la sílaba... veamos las siguientes

	    // vocal es la última vocal de la palabra: no hay más sílabas
	    if (ultimaVocal(vocal, a)) return w.size()-1
	    
	    def l1 = vocal+1
	    
	    // l1 es la última letra
	    if (l1+1==a.size())
	    {
	        if (esVocal(a[l1]) && isHiato(a[vocal], a[l1])) return vocal
	        else return l1
	    }

	    def l2 = l1+1;
	    if (esConsonante(a[l1]) && esVocal(a[l2])) // VCV
	    {
	        return vocal
	    }
	    else if (esConsonante(a[l1]) && esConsonante(a[l2])) // VCC
	    {
	        if ( ['tr','gr','pr','br','bl','fr','fl','cl','dr','pl'].any{ it==a[l1].toString()+a[l2].toString() } ) return vocal // aTRapa
	        if ( 'ns'==a[l1].toString()+a[l2].toString() )
	        {
	            if (a.size()>l2+1 && esConsonante(a[l2+1])) return l2 // caso traNSporte
	        }
	        return l1 // baRCo
	    }
	    else if (esVocal(a[l1])) // VV?
	    {
	        logger.log(level, "VV?: ${a}")

	        if (isHiato(a[vocal], a[l1])) return vocal
	        else return vocal+next_s(w.substring(l1))+1
	    }
	    return 0
	  }
	  
	  def ultimaVocal(vocal, a)
	  {
	    for (def i=vocal+1; i<a.size(); i++)
	    {
	      if (esVocal(a[i]))
	      {
	        return false
	      }
	    }
	    return true
	  }
	  
	  def isHiato(v1, v2)
	  {
	     return (
	      cerradas_tilde.any{ it==v1 || it==v2 } // si una de ellas es cerrada y lleva tilde
	      ||
	      ( abiertas.any{ it==v1 } && abiertas.any{ it==v2 } ) // si las dos son abiertas
	      ||
	      (v1==v2) // si son iguales (aa, ii)
	    )
	  }
	  def esVocal(l) {
	    vocales.any{it==Character.toLowerCase(l)}
	  }
	  def esConsonante(l) {
	    (!esVocal(l)) && (l==~/\D/)
	  }
	  def esValida(l) {
	    !(l==~/\D/)
	  }
	  public String format(String w)
	  {
	    if (w==null) w = "";
	    for (int i=0; i<conversiones.length; i++) {
	    	w = w.replace(conversiones[i][0], conversiones[i][1]);
	    }
	    return w;
	  }
	  public String unformat(String w)
	  {
	    if (w==null) w = "";
	    for (int i=0; i<conversiones.length; i++) {
	    	w = w.replace(conversiones[i][1], conversiones[i][0]);
	    }
	    return w;
	  }
	  def tonica(silabas)
	  {
	    if (silabas.size()==1) return 0;
	    
	    def ret = -1
	    // si una sílaba tiene tilde, esa es la tónica
	    silabas.eachWithIndex { s, i -> 
	       if (s.toLowerCase() =~ patron_tilde) ret = i;
	    }
	    if (ret != -1) return ret
	      
	    // sólo puede ser aguda o llana
	    def ultima = silabas[-1]
	    if (ultima ==~ patron_vocal_n_s) return silabas.size()-2 // llana
	    else return silabas.size()-1 // aguda
	  }

	  /**
	   * De una sílaba devuelve la posición de la vocal tónica:
	   * mía -> 1 (í)
	   * mia -> 2 (a)
	   */
	  def vocalTonica(silaba)
	  {
	    def letras = silaba.toLowerCase().toCharArray()
	    def ret = -1, j  = 0;

	    // si hay sólo una vocal, es ésa: mas
	    letras.eachWithIndex { s, i -> 
	       if (s =~ /a|e|i|o|u|á|é|í|ó|ú/)
	       {
	         j++
	         ret = i
	       }
	    }
	   if (j==1) return ret
	    
	    // si hay una acentuada: día, aVIÓN
	    ret = -1
	    letras.eachWithIndex { s, i -> 
	       if (s =~ /á|é|í|ó|ú/)
	       {
	         ret = i
	       }
	    }
	    if (ret != -1) return ret

	    // si no, la abierta: MIERda
	    ret = -1
	    letras.eachWithIndex { s, i -> 
	       if (s =~ /a|á|e|é|o|ó/)
	       {
	         ret = i
	       }
	    }
	    return ret
	  }
	}
