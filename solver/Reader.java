package solver;

import java.util.*;
import scheduleio.*;
import java.io.*;

/**
 * Une classe pour traiter la lecture de fichier pour les activite et les contrainte
 */
public class Reader
{

    protected String ContraintTXT;
    protected Map<String, Activity> Activities;

    /**
	 * Constructeur de la classe
     * recupere les donnees des activite
	 * enregistre le chemin du fichier contrainte.txt
     *
	 * @param ActivityTXT Chaine de caractere contenant le chemin du fichier activites.txt
     * @param ContraintTXT Chaine de caractere contenant le chemin du fichier constraints.txt
	 *
	 * @throws IOException
	 */
    public Reader(String ActivityTXT, String ContraintTXT) throws IOException
    {
        ActivityReader ActivityReader = new ActivityReader(ActivityTXT);
        Map<String, ActivityDescription> Temp = ActivityReader.readAll();
        this.Activities = new HashMap<>();
        Temp.forEach((k, v) -> this.Activities.put(k, new Activity(v.getName(), v.getDuration())));
        this.ContraintTXT = ContraintTXT;
    }

    /**
	 * Assesseur get
	 *
	 * @return La liste d'activite
	 */
    public Map<String, Activity> getActivities()
    {
        return this.Activities;
    }

    /**
     * Recupere une nouvelle instance de PrecedenceConstraint
     *
     * @param contraint[] Tableau de chaine de caractere
     *
     * @return Instance de PrecedenceConstraint
     * @throws IOException
     */
    private Constraint getPrecedenceContraint(String contraint[]) throws IOException
    {
        if (contraint.length != 2)
        {
			throw new IOException("Expression mal formée, il devrait y avoir deux arguments: " + contraint);
		}
        for (String activity : contraint)
        {
            if (this.Activities.get(activity) == null)
            {
                throw new IOException("Expression mal formée, l'activité n'existe pas: " + activity);
            }
        }
        return new PrecedenceConstraint(this.Activities.get(contraint[0]), this.Activities.get(contraint[1]));
    }

    /**
     * Recupere une nouvelle instance de PrecedenceConstraintWithGap
     *
     * @param contraint[] Tableau de chaine de caractere
     *
     * @return Instance de PrecedenceConstraintWithGap
     * @throws IOException
     */
    private Constraint getPrecedenceContraintWithGap(String contraint[]) throws IOException
    {
        if (contraint.length != 3)
        {
			throw new IOException("Expression mal formée, il devrait y avoir trois arguments: " + contraint);
		}
        int gap = Integer.parseInt(contraint[0]);
        List<String> Constraint = new ArrayList<>();
        Constraint.add(contraint[1]);
        Constraint.add(contraint[2]);
        if (gap < 0)
        {
			throw new IOException("Expression mal formée, la valeur ne peut pas être négative: " + gap);
		}
        for (String activity : Constraint)
        {
            if (this.Activities.get(activity) == null)
            {
                throw new IOException("Expression mal formée, l'activité n'existe pas: " + activity);
            }
        }
        return new PrecedenceConstraintWithGap(this.Activities.get(contraint[1]), this.Activities.get(contraint[2]), gap);
    }

    /**
     * Recupere une nouvelle instance de MeetConstraint
     *
     * @param contraint[] Tableau de chaine de caractere
     *
     * @return Instance de MeetConstraint
     * @throws IOException
     */
    private Constraint getMeetConstraint(String contraint[]) throws IOException
    {
        if (contraint.length != 2)
        {
			throw new IOException("Expression mal formée, il devrait y avoir deux arguments: " + contraint);
		}
        for (String activity : contraint)
        {
            if (this.Activities.get(activity) == null)
            {
                throw new IOException("Expression mal formée, l'activité n'existe pas: " + activity);
            }
        }
        return new MeetConstraint(this.Activities.get(contraint[0]), this.Activities.get(contraint[1]));
    }

    /**
     * Recupere une nouvelle instance de MaxSpanConstraint
     *
     * @param contraint[] Tableau de chaine de caractere
     *
     * @return Instance de MaxSpanConstraint
     * @throws IOException
     */
    private Constraint getMaxSpanConstraint(String contraint[]) throws IOException
    {
        if (contraint.length < 3)
        {
			throw new IOException("Expression mal formée, il devrait y avoir au moins trois arguments: " + contraint);
		}
        int maxSpan = Integer.parseInt(contraint[0]);
        List<String> Constraint = new ArrayList<>();
        for (int i = 1 ; i <= contraint.length - 1; i++ )
        {
            Constraint.add(contraint[i]);
        }
        for (String activity : Constraint)
        {
            if (this.Activities.get(activity) == null)
            {
                throw new IOException("Expression mal formée, l'activité n'existe pas: " + activity);
            }
        }

        List<Activity> activities = new ArrayList<>();
        for (int i = 1 ; i <= contraint.length - 1; i++ )
        {
            activities.add(this.Activities.get(contraint[i]));
        }
        return new MaxSpanConstraint(activities, maxSpan);
    }

    /**
     * Recupere et traite les donnees du fichier contrainte
     *
     * @return Liste contenant les instances des differante Constrainte
     * @throws IOException
     */
    public List<Constraint> ReadContraints() throws IOException
    {
        ConstraintReader ConstraintReader = new ConstraintReader(this.ContraintTXT);
        List<ConstraintDescription> Temp = ConstraintReader.readAll();
        List<Constraint> Constraint = new ArrayList<>();
        for(ConstraintDescription value : Temp)
        {
            switch(value.getKeyword())
            {
                case "MEET":
                    Constraint.add(getMeetConstraint(value.getArguments()));
                    break;
                case "PRECEDENCE":
                    Constraint.add(getPrecedenceContraint(value.getArguments()));
                    break;
                case "PRECEDENCE_GAP":
                    Constraint.add(getPrecedenceContraintWithGap(value.getArguments()));
                    break;
                case "MAX_SPAN":
                    Constraint.add(getMaxSpanConstraint(value.getArguments()));
                    break;
                default:
                    throw new IOException("Expression mal formée, le mot clef est invalide: " + value.getKeyword());
            }
        }
        return Constraint;
    }
}
