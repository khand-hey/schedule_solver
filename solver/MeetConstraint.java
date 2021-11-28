package solver;

import java.util.HashMap;

/**
* Une classe  pour representer une contraitre specifique pour les activites
* heritant de la classe BinaryConstraint
*/
public class MeetConstraint extends BinaryConstraint
{
	/**
	 * Constructeur de la classe
     *
	 * @param first Instance d'Activity
     * @param second Instance d'Activity
	 */
	public MeetConstraint(Activity first, Activity second)
	{
		super(first, second);
	}

	/**
	 * Verifie si la deuxiemme activite commence a l'heure de la fin de la premiere
	 *
	 * @param EDT HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le l'activite en valeur
	 * represente un emploi du temps
	 * @return boolean si valeur egal
	 */
	@Override
	public boolean isSatifiedBySchedule(HashMap<Activity, Integer> EDT)
	{
		if(EDT.get(second) == EDT.get(first) + this.first.getDuration()) return true;
		return false;
	}

}
