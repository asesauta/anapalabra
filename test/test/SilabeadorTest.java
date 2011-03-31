package test;

import java.util.ArrayList;

import org.asesauta.anapalabra.Silabeador;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Ejemplos de: http://www.rae.es/rae/gestores/gespub000015.nsf/(voanexos)/
 * arch7E8694F9D6446133C12571640039A189/$FILE/Ortografia.pdf
 */
public class SilabeadorTest {
	Silabeador s = new Silabeador();
	ArrayList<String> slbs = new ArrayList<String>();
	ArrayList<String> testdata = new ArrayList<String>();

	@Test
	public void testconversiones() {
		String w = "guitarra";
		assertTrue(w.equals(s.unformat(s.format(w))));
	}

	@Test
	public void testMonosilabos() {
		testdata.add("sol");
		testdata.add("mar");
		testdata.add("ah");
		testdata.add("él");
		testdata.add("perl");
		for (String t : testdata) {
			slbs = s.silabear(t);
			assertTrue(slbs.size() == 1);
			assertTrue(slbs.get(0).equals(t));
		}
	}

	@Test
	public void testHiatos() {
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
}