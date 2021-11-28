package solver;

import java.util.Random;
import java.util.*;
import java.io.*;

/**
* Une classepour generer un emploi du temps aleatoirement
* tout en verifiant si les contraite sont respecter totalement, en parti, ou pas du tout
*/
public class RandomScheduler
{
    private Set<Activity> activities;
    private List<Constraint> constraints;
    private Random randomGenerator;
    private int nbConstraintsSatify;

    /**
	 * Constructeur de la classe
     * Initialise un HashSet vide
	 * Initialise une List vide
     * Initialise une instance de Random
	 */
    public RandomScheduler()
    {
        this.activities = new HashSet<>();
        this.constraints = new ArrayList<>();
        this.randomGenerator = new Random();
        this.nbConstraintsSatify = -1;
    }

    /**
	 * Assesseur get
	 *
	 * @return La taille de la List de Contraintre
	 */
    public int getNbConstraint()
    {
        return this.constraints.size();
    }

    /**
	 * Assesseur get
	 *
	 * @return Le nombre de contrainte satisfait par l'emploie du temps generer
	 */
    public int getNbConstraintSatify()
    {
        return this.nbConstraintsSatify;
    }

    /**
	 * Assesseur set special
	 *
	 * @param activity Instance d'Activity
	 */
    public void add(Activity activity)
    {
        this.activities.add(activity);
    }

    /**
	 * Assesseur set special
	 *
	 * @param constraint Instance de Contraint
	 */
    public void add(Constraint constraint)
    {
        this.constraints.add(constraint);
    }

    /**
	 * Genere un emploi du temps aleatoire
	 *
	 * @return HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le l'activite en valeur
	 * represente un emploi du tempsChaine de caractere decrivant estetiquement l'activite
	 */
    private HashMap<Activity, Integer> ramdomCalendar()
    {
        HashMap<Activity, Integer> Calendar = new HashMap<>();
        for (Activity activity : this.activities)
        {
            Calendar.put(activity,this.randomGenerator.nextInt(601));
        }
        return Calendar;
    }

    /**
	 * Verifie si les contraite sont statisfaite pour l'emploi du temps donner
	 *
     * @param EDT HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le l'activite en valeur
	 * represente un emploi du temps
	 * @return Entier represantant le nombre de contraitre satisfaite
	 */
    private int satisfyConstraints(HashMap<Activity, Integer> EDT)
    {
        int validConstraint = 0;
        for (Constraint constraint : this.constraints)
        {
            if(constraint.isSatifiedBySchedule(EDT) == true)
            {
                validConstraint++;
            }
        }
        return validConstraint;
    }

    /**
	 * Genere un nombre definie d'emploie du temps pour avoir le meilleur possible
	 *
     * @param nbGenerator Entier correspondant au nombre d'emploi du temps a generer
	 * @returnHashMap conteant une instance d'activite en key
	 * et un entier pour le debut le l'activite en valeur
	 * represente un emploi du temps
     *
     * @throws IOException
	 */
    public HashMap<Activity, Integer> bestRamdomCalendar(int nbGenerator) throws IOException
    {
        if (nbGenerator <= 0)
        {
			throw new IOException("Expression mal formée, la valeur entré ne peut être négative ou égal à 0: " + nbGenerator);
		}

        //Map ou sera enregistrer le meilleur emploi du temps
        HashMap<Activity, Integer> bestCalendar = new HashMap<>();

        //Map ou sera enregistrer les emplois du temps generer
        HashMap<Activity, Integer> Temp = new HashMap<>();
        int tempNbConstraintsSatify;

        //Selon la valeur entrer de l'utilisateur generera le meme nombre d'emploi du temps
        for (int i = 0; i <= nbGenerator; i++)
        {
            //Generation de l'emploi du temps
            Temp = ramdomCalendar();

            //Enregistre le nombre de de contrainte sont satisfait par l'emploi du temps generer
            tempNbConstraintsSatify = satisfyConstraints(Temp);

            //Enregistre l'emploi du temps generer si il est meilleur que celui deja enregistrer
            if ( tempNbConstraintsSatify > this.nbConstraintsSatify)
            {
                bestCalendar = Temp;
                this.nbConstraintsSatify = tempNbConstraintsSatify;
            }

            //Interromp le tant que si l'emploi du temps satisfait toute les contraites
            if (this.nbConstraintsSatify == getNbConstraint())
            {
                return bestCalendar;
            }
        }
        return bestCalendar;
    }
}
