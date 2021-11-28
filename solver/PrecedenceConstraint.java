package solver;

import java.util.HashMap;

/**
* Une classe  pour representer une contraitre specifique pour les activites
* heritant de la classe BinaryConstraint
*/
public class PrecedenceConstraint extends BinaryConstraint {

	/**
	 * Constructeur de la classe
     *
	 * @param first Instance d'Activity
     * @param second Instance d'Activity
	 */
	public PrecedenceConstraint(Activity first, Activity second) {
		super(first, second);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Verifie si l'heure de debut due la deuxiemme activite
	 * est superieur a l'heure de fin du premier
	 *
	 * @param EDT HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le lactivite en valeur
	 * represente un emploi du temps
	 * @return boolean si valeur superieur ou égal
	 */
	@Override
	public boolean isSatifiedBySchedule(HashMap<Activity, Integer> EDT)
	{
		if(EDT.get(second) >= EDT.get(first) + this.first.getDuration()) return true;
		return false;
	}
}
