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
			assert (slbs.size() == 1);
			assert slbs.get(0).equals(t);
		}
	}

	@Test
	public void testHiatos() {
		String w = "guía";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("guí");
		assert slbs.get(1).equals("a");

		w = "teatro";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("te");
		assert slbs.get(1).equals("a");
		assert slbs.get(2).equals("tro");

		w = "aéreo";
		slbs = s.silabear(w);
		assert slbs.size() == 4;
		assert slbs.get(0).equals("a");
		assert slbs.get(1).equals("é");
		assert slbs.get(2).equals("re");
		assert slbs.get(3).equals("o");

		w = "vigía";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("vi");
		assert slbs.get(1).equals("gí");
		assert slbs.get(2).equals("a");

		w = "veo";
		slbs = s.silabear(w);
		assert slbs.size() == 2;
		assert slbs.get(0).equals("ve");
		assert slbs.get(1).equals("o");

		w = "salías";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("sa");
		assert slbs.get(1).equals("lí");
		assert slbs.get(2).equals("as");

		w = "caía";
		slbs = s.silabear(w);
		assert slbs.size() == 3;
		assert slbs.get(0).equals("ca");
		assert slbs.get(1).equals("í");
		assert slbs.get(2).equals("a");

		w = "silabear";
		slbs = s.silabear(w);
		assert slbs.size() == 4;
		assert slbs.get(0).equals("si");
		assert slbs.get(1).equals("la");
		assert slbs.get(2).equals("be");
		assert slbs.get(3).equals("ar");
	}
}