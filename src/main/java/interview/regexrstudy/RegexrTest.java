package interview.regexrstudy;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexrTest {

    @Test
    public void testSingleDot() {
        String[] listaTextos = {
                "¡Hola Mundo!",
                "miCorreo@gmail.com",
                "La teoría de 'Pattern Machine' dice…",
                "correoFalso@yahoo.es",
                "En un lugar de la Mancha, cuyo nombre no quiero acordarme…",
                "+34 91 123 456 789",
                "estoNOesUnCorreoNoTieneArroba.com",
                "RaMoN@jarroba.com",
                "Calle Alcalá 12345 Madrid, Madrid"
        };

        String regex = "[A-Za-z]+@[a-z]+\\.[a-z]+";
        Pattern patron = Pattern.compile(regex);

        for (String texto : listaTextos) {
            Matcher emparejador = patron.matcher(texto);
            boolean esCoincidente = emparejador.find();

            if (esCoincidente) {
                System.out.println("Correo reconocido: " + texto);
            }
        }
    }

    @Test
    public void testGroup() {
        String regex = "(\\d+) de (\\w+) de (\\d+)";
        Pattern patron = Pattern.compile(regex);

        String texto = "30 de Enero de 2015";
        Matcher emparejador = patron.matcher(texto);

        emparejador.find();

        String el1 = emparejador.group(1);
        String el2 = emparejador.group(2);
        String el3 = emparejador.group(3);

        System.out.println("Contenido en la variable $1: \'" + el1 + "\', en la variable $2: \'" + el2 + "\', en la variable $3: \'" + el3 + "\'");
    }

    @Test
    public void testInsert() {
        String regex = ".s.";
        Pattern patron = Pattern.compile(regex);

        String texto = "asa ese aca iso ala oso ato";
        Matcher emparejador = patron.matcher(texto);

        StringBuilder devolver = new StringBuilder();

        int numApariciones = 0;

        while (emparejador.find()) {
            devolver.append("\nPalabra \"" + emparejador.group() + "\" encontrada en la posicion " + emparejador.start());
            numApariciones++;
        }

        devolver.insert(0, "El numero de apariciones del patron .s. es " + numApariciones);

        System.out.append(devolver.toString());
    }
}
