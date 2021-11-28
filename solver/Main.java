package solver;

import java.io.*;
import java.util.*;

/**
 * Une classe pour faire fonctionner l'application
 */
public class Main
{
    /**
	 * Lit les activite et les contraintes puis récupère le meilleure
	 * emploi du temps  genere et affiche ce dernier dans l'ordre croissant
	 *
	 * @param args
	 */
    public static void main (String[] args) throws IOException
    {
        //Instanciation de l'objet Reader
        Reader Reader = new Reader("data/activities.txt", "data/constraints.txt");

        //Récupération des activites
        Map<String, Activity> activities = new HashMap<>();
        activities = Reader.getActivities();

        //Récupération des contraintes
        List<Constraint> constraints = new ArrayList<>();
        constraints = Reader.ReadContraints();

        //Instanciation de l'objet RandomScheduler et ajout des activites et des contraintes a ce premier
        RandomScheduler RandomScheduler = new RandomScheduler();
        for (Constraint constraint : constraints) RandomScheduler.add(constraint);
        activities.forEach((k, v) -> RandomScheduler.add(v));

        //Demande de saisie d'un entier par l'utlisateur
        Scanner saisieUtilisateur = new Scanner(System.in);
        System.out.println("Veuillez saisir un entier :");
        int nbGenerator = saisieUtilisateur.nextInt();

        //Generation de l'emploi du temps
        HashMap<Activity, Integer> scheduler = RandomScheduler.bestRamdomCalendar(nbGenerator);

        //Trie des activite et affichache de celui ci avec le nombre de contraintes satisfait
        System.out.println(RandomScheduler.getNbConstraintSatify() + "/" + RandomScheduler.getNbConstraint() + " des contraintes ont ete satisfait !");
        Tri tri = new Tri();
        for (Map.Entry<Activity, Integer> entry : tri.triAvecValeur(scheduler).entrySet())
        {
            System.out.println("\n" + (entry.getValue()/60 + 8) + "h" + (entry.getValue() - (entry.getValue()/60)*60) + "min");
            System.out.println(entry.getKey().toString());
        }
    }
}
