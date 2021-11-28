package solver;

import java.util.HashMap;

public class PrecedenceConstraintWithGap extends PrecedenceConstraint
{
	protected int gap;

	/**
	 * Constructeur de la classe
     *
	 * @param first Instance d'Activity
     * @param second Instance d'Activity
	 */
	public PrecedenceConstraintWithGap(Activity first, Activity second, int gap) {
		super(first, second);
		this.gap = gap;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Verifie si l'heure de debut de la deuxiemme activites
	 * est supp a l'heure de fin du premier + le en temps donner
	 *
	 * @param EDT HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le lactivite en valeur
	 * represente un emploi du temps
	 * @return boolean si valeur superieur ou egal
	 */
	@Override
	public boolean isSatifiedBySchedule(HashMap<Activity, Integer> EDT)
	{
		if(EDT.get(second) >= EDT.get(first)+this.first.getDuration() + gap )return true;
		return false;
	}

}
