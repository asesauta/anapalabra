package test;

import java.util.ArrayList;

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

//	@Test
	public void testConversiones()
	{
		String w = "guitarra";
		assertTrue(w.equals(s.unformat(s.format(w))));
	}

//	@Test
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
		assert slbs.size() == 4;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("mor");
		assert slbs.get(2).equals("ti");
		assert slbs.get(3).equals("guáis");

		slbs = s.silabear("buey");
		assert slbs.size() == 1;
		assert slbs.get(0).equals("buey");

		w = "despreciéis";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("des");
		assert slbs.get(1).equals("pre");
		assert slbs.get(2).equals("ciéis");

		w = "miau";
		slbs = s.silabear(w);
		assert slbs.size() == 1;
		assert slbs.get(0).equals("miau");

		w = "limpiáis";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("lim");
		assert slbs.get(1).equals("piáis");

		w = "averigüéis";
		slbs = s.silabear(w);
		assert slbs.size() == 4;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("ve");
		assert slbs.get(2).equals("ri");
		assert slbs.get(3).equals("güéis");

		w = "Paraguay";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("Pa");
		assert slbs.get(1).equals("ra");
		assert slbs.get(2).equals("guay");

		w = "cacahuey";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("ca");
		assert slbs.get(1).equals("ca");
		assert slbs.get(2).equals("huey");

		w = "apacigüéis";
		slbs = s.silabear(w);
		assert slbs.size() == 4;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("pa");
		assert slbs.get(2).equals("ci");
		assert slbs.get(3).equals("güéis");

		w = "estudiáis";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("es");
		assert slbs.get(1).equals("tu");
		assert slbs.get(2).equals("diáis");
	}

	@Test
	public void testDiptongos()
	{
		String w = "mierda";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("mier");
		assert slbs.get(1).equals("da");

		w = "adiós";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("diós");
	}

	@Test
	public void testFaciles()
	{
		String w = "casa";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("ca");
		assert slbs.get(1).equals("sa");

		w = "azul";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("zul");
	}

	@Test
	public void test2Consonantes()
	{
		String w = "África";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("Á");
		assert slbs.get(1).equals("fri");
		assert slbs.get(2).equals("ca");

		w = "transformar";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("trans");
		assert slbs.get(1).equals("for");
		assert slbs.get(2).equals("mar");

	}

	@Test
	public void testTonica()
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add("mo");
		assert (s.tonica(al) == 0);

		al.clear();
		al.add("ár");
		al.add("bol");
		assert (s.tonica(al) == 0);

		al.clear();
		al.add("a");
		al.add("vión");
		assert (s.tonica(al) == 1);

		al.clear();
		al.add("a");
		al.add("VIÓN");
		assert (s.tonica(al) == 1);

		al.clear();
		al.add("es");
		al.add("drú");
		al.add("ju");
		al.add("la");
		assert (s.tonica(al) == 1);

		al.clear();
		al.add("al");
		al.add("ga");
		assert (s.tonica(al) == 0);

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
		assert slbs.size() == 9;
		assert slbs.get(0).equals("es");
		assert slbs.get(1).equals("ter");
		assert slbs.get(2).equals("no");
		assert slbs.get(3).equals("clei");
		assert slbs.get(4).equals("do");
		assert slbs.get(5).equals("mas");
		assert slbs.get(6).equals("toi");
		assert slbs.get(7).equals("de");
		assert slbs.get(8).equals("o");

		w = "cuadrado";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("cua");
		assert slbs.get(1).equals("dra");
		assert slbs.get(2).equals("do");

		w = "cuadrilátero";
		slbs = s.silabear(w);
		assert slbs.size() == 5;
		assert slbs.get(0).equals("cua");
		assert slbs.get(1).equals("dri");
		assert slbs.get(2).equals("lá");
		assert slbs.get(3).equals("te");
		assert slbs.get(4).equals("ro");

		w = "picaflor";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("pi");
		assert slbs.get(1).equals("ca");
		assert slbs.get(2).equals("flor");

		w = "aeroplano";
		slbs = s.silabear(w);
		assert slbs.size() == 5;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("e");
		assert slbs.get(2).equals("ro");
		assert slbs.get(3).equals("pla");
		assert slbs.get(4).equals("no");

		w = "subrayar";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("sub");
		assert slbs.get(1).equals("ra");
		assert slbs.get(2).equals("yar");

		w = "submarino";
		slbs = s.silabear(w);
		assert slbs.size() == 4;
		assert slbs.get(0).equals("sub");
		assert slbs.get(1).equals("ma");
		assert slbs.get(2).equals("ri");
		assert slbs.get(3).equals("no");

		w = "subir";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("su");
		assert slbs.get(1).equals("bir");

		w = "mubarak";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("mu");
		assert slbs.get(1).equals("ba");
		assert slbs.get(2).equals("rak");
	}

	@Test
	public void testVocalTonica()
	{
		assert (s.vocalTonica("mas") == 1);
		assert (s.vocalTonica("vión") == 2);
		assert (s.vocalTonica("más") == 1);
		assert (s.vocalTonica("a") == 0);
		assert (s.vocalTonica("ví") == 1);
		assert (s.vocalTonica("mier") == 2);
	}
}