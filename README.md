anapalabra (spanish)
==========

Programa que separa una palabra en español en sílabas e identifica la tónica.

Splits spanish words into syllables and identifies the accented syllable.

Ejemplo:

Silabeador s = new Silabeador();
ArrayList<String> slbs = s.silabear("hola");
System.out.println(slbs);
// [ho, la]
String tonica = slbs.get(s.tonica(slbs));
System.out.println(tonica);
// ho
