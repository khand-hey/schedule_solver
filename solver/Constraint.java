package solver;

import java.util.HashMap;

/**
 * Interface pour categoriser tout les classe definissant une contraite specifique comme une Constraint
 */
public interface Constraint {

	abstract boolean isSatifiedBySchedule(HashMap<Activity, Integer> EDT);
}
