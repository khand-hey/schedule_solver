package solver;

/**
 * Une classe pour cree une activite
 */
public class Activity
{
	private String description;
	private int duration;

	/**
	 * Constructeur de la classe
     *
	 * @param description Chaine de caractere contenant la description de l'activite
     * @param duration Entier correspondant a la durer de l'activite
	 */
	public Activity(String description, int duration)
	{
		super();
		this.description = description;
		this.duration = duration;
	}

	/**
	 * Assesseur get
	 *
	 * @return La description de l'activite
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Assesseur set
	 *
	 * @param description Chaine de caractere contenant la description de l'activite
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Assesseur get
	 *
	 * @return La durer de l'activite
	 */
	public int getDuration()
	{
		return duration;
	}

	/**
	 * Assesseur set
	 *
	 * @param duration Entier correspondant a la durer de l'activite
	 */
	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	/**
	 * Concataine les donnees de l'activite
	 *
	 * @return Chaine de caractere decrivant estetiquement l'activite
	 */
	@Override
	public String toString()
	{
		return "Activity [description=" + description + ", duration=" + (this.duration/60) + "h" + (this.duration - (this.duration/60)*60) + "min" + "]";
	}
}
