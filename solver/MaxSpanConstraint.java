package solver;

import java.util.HashMap;
import java.util.*;

/**
 * Une classe pour representer une contraitre pour les activites
 * implementer de l'interface Constraint
 */
public class MaxSpanConstraint implements Constraint
{

	private List<Activity> activities = new ArrayList<>();
	private int maxSpan;

	/**
	 * Constructeur de la classe
     *
	 * @param acts List contenant les activites
     * @param maxSpan Entier represantant l'ecart maximun entre les activite
	 */
	public MaxSpanConstraint(List<Activity> acts, int maxSpan)
	{
		super();
		for (Activity act : acts)
		{
			this.activities.add(act);
		}
		this.maxSpan = maxSpan;
	}

	/**
	 * Verifie si les ecart entre les activite ne depasse pas la valeur autorise
	 *
	 * @param EDT HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le lactivite en valeur
	 * represente un emploi du temps
	 * @return boolean si il y a chevauchement ou pas
	 */
	@Override
	public boolean isSatifiedBySchedule(HashMap<Activity,Integer> EDT)
	{
		for(Activity act1 : this.activities)
		{
      		for(Activity act2 : this.activities)
      		{
               	if( EDT.get(act2) - ( EDT.get(act1) + act1.getDuration()) > this.maxSpan && EDT.get(act1) - (EDT.get(act2) + act2.getDuration()) > this.maxSpan)
				{
					return false;
				}
       		}
		}
		return true;
	}
}
