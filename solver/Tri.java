package solver;

import java.io.*;
import java.util.*;

/**
* Une classe pour trier les activite de l'emploi du temps selon le temps
*/
public class Tri
{
    /**
	 * Constructeur de la classe
	 */
    public Tri()
    {

    }

    /**
	 * Trie un HashMap dans l'ordre croissant selon ses valeur
     *
     * @param map HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le l'activite en valeur
	 * represente un emploi du tempsChaine de caractere decrivant estetiquement l'activite
     * @return HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le l'activite en valeur
	 * represente un emploi du tempsChaine de caractere decrivant estetiquement l'activite
	 */
    public static HashMap<Activity, Integer> triAvecValeur( HashMap<Activity, Integer> map )
    {
        List<Map.Entry<Activity, Integer>> list =
            new LinkedList<Map.Entry<Activity, Integer>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<Activity, Integer>>()
        {
            /**
        	 * Compare deux valuer entre
             *
             * @param o1 HashMap conteant une instance d'activite en key
        	 * et un entier pour le debut le l'activite en valeur
        	 * represente un emploi du tempsChaine de caractere decrivant estetiquement l'activite
             * @param o2 HashMap conteant une instance d'activite en key
        	 * et un entier pour le debut le l'activite en valeur
        	 * represente un emploi du tempsChaine de caractere decrivant estetiquement l'activite
             * @return Entier
        	 */
            public int compare( Map.Entry<Activity, Integer> o1, Map.Entry<Activity, Integer> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue());
            }
        });

        HashMap<Activity, Integer> map_apres = new LinkedHashMap<Activity, Integer>();
        for(Map.Entry<Activity, Integer> entry : list)
            map_apres.put( entry.getKey(), entry.getValue() );
        return map_apres;
    }
}
