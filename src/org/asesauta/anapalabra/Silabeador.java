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
	  char[] abiertas = {'a', 'á', 'e', 'é', 'o', 'ó'};
	  char[] cerradas = {'i', 'u', 'ü', 'y'};
	  char[] cerradas_tilde = {'í', 'ú'};
	  Pattern patron_tilde = Pattern.compile("á|é|í|ó|ú");
	  Pattern patron_vocal_n_s = Pattern.compile(".*(á|é|í|ó|ú|a|e|i|o|u|n|s)");
	  char enye = 'ñ';
	  
	  public char[] getVocales() {
		  int size = abiertas.length+cerradas.length+cerradas_tilde.length;
		  char[] vocales = new char[size];
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
	  
	  public char[] getTierrasRaras() {
		  int size = getVocales().length+1;
		  char[] raras = new char[size];
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
	      silabas.add(silaba);
	    }
	    return silabas;
	  }
	  
	  
	  public int next_s(String w)
	  {
	    char[] a = w.toCharArray();

	    // excepción: subrayar
		if (a[0]=='s' && a[1]=='u' && a[2]=='b' && a[3]=='r') return 2;

		int vocal=0;
		boolean found=false;
		while (vocal<a.length && !found) {
			found = esVocal(a[vocal]);
			vocal++;
		}

	    // sabemos que todas las letras anteriores a vocal + vocal forman parte de la sílaba... veamos las siguientes

	    // vocal es la última vocal de la palabra: no hay más sílabas
	    if (ultimaVocal(vocal, a)) return w.length()-1;
	    
	    int l1 = vocal+1;
	    
	    // l1 es la última letra
	    if (l1+1==a.length)
	    {
	        if (esVocal(a[l1]) && isHiato(a[vocal], a[l1])) return vocal;
	        else return l1;
	    }

	    int l2 = l1+1;
	    if (esConsonante(a[l1]) && esVocal(a[l2])) // VCV
	    {
	        return vocal;
	    }
	    else if (esConsonante(a[l1]) && esConsonante(a[l2])) // VCC
	    {
	    	String[] cc = {"tr","gr","pr","br","bl","fr","fl","cl","dr","pl"};
	    	char[] tokenchar = {a[l1], a[2]};
	    	String token = new String(tokenchar).toLowerCase();
	    	for (String s: cc) {
	    		if (s.equals(token)) return vocal;
	    	}
	    	if ("ns".equals(token))
	        {
	            if (a.length>l2+1 && esConsonante(a[l2+1])) return l2; // caso traNSporte
	        }
	        return l1; // baRCo
	    }
	    else if (esVocal(a[l1])) // VV?
	    {
	        if (isHiato(a[vocal], a[l1])) return vocal;
	        else return vocal+next_s(w.substring(l1))+1;
	    }
	    return 0;
	  }
	  
	  public boolean ultimaVocal(int vocal, char[] a)
	  {
	    for (int i=vocal+1; i<a.length; i++)
	    {
	      if (esVocal(a[i]))
	      {
	        return false;
	      }
	    }
	    return true;
	  }
	  
	  public boolean isHiato(char v1, char v2)
	  {
		  // si una de ellas es cerrada y lleva tilde
		  for (char c: cerradas_tilde) {
			  if (c==v1 || c==v2) return true;
		  }
		  // si las dos son abiertas
		  for (char c1: abiertas) {
			  if (c1==v1) {
				  for (char c2: abiertas) {
					  if (c2==v2) return true;
				  }
			  }
		  }
		// si son iguales (aa, ii)
		  return (v1==v2);
	  }

	  public boolean esVocal(Character l) {
		  for (char c: getVocales()) {
			  if (Character.toLowerCase(l)==c) return true;
		  }
		  return false;
	  }
	  public boolean esConsonante(char l) {
	    return (!esVocal(l));
	  }
//	  public boolean esValida(char l) {
//	    return Pattern.matches("\\D", l);
//	    !(l==~/\D/)
//	  }
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
	  public int tonica(ArrayList<String> silabas)
	  {
	    if (silabas.size()==1) return 0;
	    
	    int ret = -1;
	    // si una sílaba tiene tilde, esa es la tónica
	    int i = 0;
	    for(String silaba: silabas) {
	    	if (patron_tilde.matcher(silaba).matches()) return i;
	    	i++;
	    }

	    if (ret != -1) return ret;
	      
	    // sólo puede ser aguda o llana
	    String ultima = silabas[-1]
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
