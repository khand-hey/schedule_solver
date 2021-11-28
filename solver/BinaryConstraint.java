package solver;

import java.util.HashMap;

/**
 * Une classe abstrait pour representer une contraitre pour les activites
 * implementer de l'interface Constraint
 */
public abstract class BinaryConstraint implements Constraint
{
	protected Activity first;
	protected Activity second;

	/**
	 * Constructeur de la classe
     *
	 * @param first Instance d'Activity
     * @param second Instance d'Activity
	 */
	public BinaryConstraint(Activity first, Activity second)
	{
		super();
		this.first = first;
		this.second = second;
	}

	/**
	 * Assesseur get
	 *
	 * @return Instance d'Activity
	 */
	public Activity getFirst()
	{
		return first;
	}

	/**
	 * Assesseur set
	 *
	 * @param first Instance d'Activity
	 */
	public void setFirst(Activity first)
	{
		this.first = first;
	}

	/**
	 * Assesseur get
	 *
	 * @return Instance d'Activity
	 */
	public Activity getSecond()
	{
		return second;
	}

	/**
	 * Assesseur set
	 *
	 * @param second Instance d'Activity
	 */
	public void setSecond(Activity second)
	{
		this.second = second;
	}

	/**
	 * Verifie si les deux activite se chevauche dans le temps
	 *
	 * @param EDT HashMap conteant une instance d'activite en key
	 * et un entier pour le debut le lactivite en valeur
	 * represente un emploi du temps
	 * @return boolean si il y a chevauchement ou pas
	 */
	@Override
	public boolean isSatifiedBySchedule(HashMap<Activity, Integer> EDT) {
		if(EDT.get(this.second) > EDT.get(this.first) + this.first.getDuration()) return true;
		return false;
	}

}
