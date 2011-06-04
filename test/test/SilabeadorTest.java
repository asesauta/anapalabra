package test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.asesauta.anapalabra.Silabeador;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Ejemplos de: http://www.rae.es/rae/gestores/gespub000015.nsf/(voanexos)/
 * arch7E8694F9D6446133C12571640039A189/$FILE/Ortografia.pdf
 */
public class SilabeadorTest
{
	Silabeador s = new Silabeador();
	ArrayList<String> slbs = new ArrayList<String>();
	ArrayList<String> testdata = new ArrayList<String>();

	@Test
	public void testConversiones()
	{
		String w = "guitarra";
		assertTrue(w.equals(s.unformat(s.format(w))));
	}

	@Test
	public void testMonosilabos()
	{
		testdata.add("sol");
		testdata.add("mar");
		testdata.add("ah");
		testdata.add("él");
		testdata.add("perl");
		for (String t : testdata)
		{
			slbs = s.silabear(t);
			assertTrue(slbs.size() == 1);
			assertTrue(slbs.get(0).equals(t));
		}
	}

	@Test
	public void testHiatos()
	{
		String w = "guía";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("guí"));
		assertTrue(slbs.get(1).equals("a"));

		w = "teatro";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("te"));
		assertTrue(slbs.get(1).equals("a"));
		assertTrue(slbs.get(2).equals("tro"));

		w = "aéreo";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 4);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("é"));
		assertTrue(slbs.get(2).equals("re"));
		assertTrue(slbs.get(3).equals("o"));

		w = "vigía";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("vi"));
		assertTrue(slbs.get(1).equals("gí"));
		assertTrue(slbs.get(2).equals("a"));

		w = "veo";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("ve"));
		assertTrue(slbs.get(1).equals("o"));

		w = "salías";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("sa"));
		assertTrue(slbs.get(1).equals("lí"));
		assertTrue(slbs.get(2).equals("as"));

		w = "caía";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("ca"));
		assertTrue(slbs.get(1).equals("í"));
		assertTrue(slbs.get(2).equals("a"));

		w = "silabear";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 4);
		assertTrue(slbs.get(0).equals("si"));
		assertTrue(slbs.get(1).equals("la"));
		assertTrue(slbs.get(2).equals("be"));
		assertTrue(slbs.get(3).equals("ar"));
	}

	@Test
	public void testTriptongos()
	{
		String w = "amortiguáis";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 4);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("mor"));
		assertTrue(slbs.get(2).equals("ti"));
		assertTrue(slbs.get(3).equals("guáis"));

		slbs = s.silabear("buey");
		assertTrue(slbs.size() == 1);
		assertTrue(slbs.get(0).equals("buey"));

		w = "despreciéis";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("des"));
		assertTrue(slbs.get(1).equals("pre"));
		assertTrue(slbs.get(2).equals("ciéis"));

		w = "miau";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 1);
		assertTrue(slbs.get(0).equals("miau"));

		w = "limpiáis";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("lim"));
		assertTrue(slbs.get(1).equals("piáis"));

		w = "averigüéis";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 4);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("ve"));
		assertTrue(slbs.get(2).equals("ri"));
		assertTrue(slbs.get(3).equals("güéis"));

		w = "Paraguay";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("Pa"));
		assertTrue(slbs.get(1).equals("ra"));
		assertTrue(slbs.get(2).equals("guay"));

		w = "cacahuey";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("ca"));
		assertTrue(slbs.get(1).equals("ca"));
		assertTrue(slbs.get(2).equals("huey"));

		w = "apacigüéis";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 4);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("pa"));
		assertTrue(slbs.get(2).equals("ci"));
		assertTrue(slbs.get(3).equals("güéis"));

		w = "estudiáis";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("es"));
		assertTrue(slbs.get(1).equals("tu"));
		assertTrue(slbs.get(2).equals("diáis"));
	}

	@Test
	public void testDiptongos()
	{
		String w = "mierda";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("mier"));
		assertTrue(slbs.get(1).equals("da"));

		w = "adiós";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("diós"));
	}

	@Test
	public void testFaciles()
	{
		String w = "casa";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("ca"));
		assertTrue(slbs.get(1).equals("sa"));

		w = "azul";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("zul"));
	}

	@Test
	public void test2Consonantes()
	{
		String w = "África";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("Á"));
		assertTrue(slbs.get(1).equals("fri"));
		assertTrue(slbs.get(2).equals("ca"));

		w = "transformar";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("trans"));
		assertTrue(slbs.get(1).equals("for"));
		assertTrue(slbs.get(2).equals("mar"));
	}
	
	@Test
	public void patronTilde()
	{
		Pattern patron = s.patron_tilde;
		String[] tildes = {"á", "é", "í", "ó", "ú", "aá", "nás", "ée", "asdaaaaaecúwe"};
		for (String sl: tildes)
			assertTrue( patron.matcher(sl).matches() );

		String[] no_tildes = {"a", "e", "i", "o", "u", "aa", "nws", "5e", "asdaaaaaecwe"};
		for (String sl: no_tildes)
			assertFalse( patron.matcher(sl).matches() );
	}

	@Test
	public void testTonica()
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add("mo");
		assertTrue((s.tonica(al) == 0));

		al.clear();
		al.add("ár");
		al.add("bol");
		assertTrue((s.tonica(al) == 0));

		al.clear();
		al.add("a");
		al.add("vión");
		assertTrue((s.tonica(al) == 1));

		al.clear();
		al.add("a");
		al.add("VIÓN");
		assertTrue((s.tonica(al) == 1));

		al.clear();
		al.add("es");
		al.add("drú");
		al.add("ju");
		al.add("la");
		assertTrue((s.tonica(al) == 1));

		al.clear();
		al.add("al");
		al.add("ga");
		assertTrue((s.tonica(al) == 0));

		al.clear();
		al.add("al");
		al.add("bor");
		assertTrue(s.tonica(al) == 1);
	}

	/**
	 * Pilladas que han hecho los usuarios
	 */
	@Test
	public void testPilladas()
	{
		String w = "esternocleidomastoideo";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 9);
		assertTrue(slbs.get(0).equals("es"));
		assertTrue(slbs.get(1).equals("ter"));
		assertTrue(slbs.get(2).equals("no"));
		assertTrue(slbs.get(3).equals("clei"));
		assertTrue(slbs.get(4).equals("do"));
		assertTrue(slbs.get(5).equals("mas"));
		assertTrue(slbs.get(6).equals("toi"));
		assertTrue(slbs.get(7).equals("de"));
		assertTrue(slbs.get(8).equals("o"));

		w = "cuadrado";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("cua"));
		assertTrue(slbs.get(1).equals("dra"));
		assertTrue(slbs.get(2).equals("do"));

		w = "cuadrilátero";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 5);
		assertTrue(slbs.get(0).equals("cua"));
		assertTrue(slbs.get(1).equals("dri"));
		assertTrue(slbs.get(2).equals("lá"));
		assertTrue(slbs.get(3).equals("te"));
		assertTrue(slbs.get(4).equals("ro"));

		w = "picaflor";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("pi"));
		assertTrue(slbs.get(1).equals("ca"));
		assertTrue(slbs.get(2).equals("flor"));

		w = "aeroplano";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 5);
		assertTrue(slbs.get(0).equals("a"));
		assertTrue(slbs.get(1).equals("e"));
		assertTrue(slbs.get(2).equals("ro"));
		assertTrue(slbs.get(3).equals("pla"));
		assertTrue(slbs.get(4).equals("no"));

		w = "subrayar";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("sub"));
		assertTrue(slbs.get(1).equals("ra"));
		assertTrue(slbs.get(2).equals("yar"));

		w = "submarino";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 4);
		assertTrue(slbs.get(0).equals("sub"));
		assertTrue(slbs.get(1).equals("ma"));
		assertTrue(slbs.get(2).equals("ri"));
		assertTrue(slbs.get(3).equals("no"));

		w = "subir";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 2);
		assertTrue(slbs.get(0).equals("su"));
		assertTrue(slbs.get(1).equals("bir"));

		w = "mubarak";
		slbs = s.silabear(w);
		assertTrue(slbs.size() == 3);
		assertTrue(slbs.get(0).equals("mu"));
		assertTrue(slbs.get(1).equals("ba"));
		assertTrue(slbs.get(2).equals("rak"));
	}

	@Test
	public void testVocalTonica()
	{
		assertTrue((s.vocalTonica("mas") == 1));
		assertTrue((s.vocalTonica("vión") == 2));
		assertTrue((s.vocalTonica("más") == 1));
		assertTrue((s.vocalTonica("a") == 0));
		assertTrue((s.vocalTonica("ví") == 1));
		assertTrue((s.vocalTonica("mier") == 2));
	}
}