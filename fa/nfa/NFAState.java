package fa.nfa;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

/**
 * Implementation of NFA State class to be used in the NFA class
 * @author Marie Phelan and Bethany Hull
 *
 */

public class NFAState extends State implements Comparable<NFAState> {

	private HashMap<Character,TreeSet<NFAState>> delta;//Holds transitions
	private boolean isFinal;//remembers its type
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public NFAState(String name) {
		this.name = name;
		delta = new HashMap<Character, TreeSet<NFAState>>();
		isFinal = false;
	}

	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param b the type of state: true - final, false - nonfinal.
	 */
	public NFAState(String name, boolean b) {
		this.name = name;
		delta = new HashMap<Character, TreeSet<NFAState>>();
		this.isFinal = b;
	}

	/**
	 * Method used to traverse the NFA - finds the next state on a given character
	 * @param onSymb the character to transition on
	 * @return The Set of NFAStates that can be transitioned to on the given symbol
	 */
	public Set<NFAState> getTo(char onSymb) {
		//Create a return set (rather than having to return null if there is no transition, the empty set will be returned)
		Set<NFAState> ret = new TreeSet<NFAState>();
		if (delta.containsKey(onSymb)) {
			//Set the return value if there is a transition on the given symbol
			ret = delta.get(onSymb);
		}
		return ret;	
	}
	
	/**
	 * 
	 * @return true if there is a transition on the empty character represented by 'e' otherwise return false
	 */
	public boolean hasNextE() {
		//check if there is a transition possible on 'e'
		if(delta.containsKey('e')) {
			return true;
		}
		return false;	
	}

	/**
	 * Method to map transitions
	 * @param onSymb the character to transition on, the key for the HashMap
	 * @param to the state to be transitioned to, the value for the HashMap
	 */
	public void addTransition(char onSymb, NFAState to) {
		//If there is not already a transition for this key, create a set with the to state and create the transition
		if (!delta.containsKey(onSymb)) {
			TreeSet<NFAState> f = new TreeSet<NFAState>();
			f.add(to);
			delta.put(onSymb, f);
		}else {
			//In the case that there is already a transition on the given character, remove the set, add the new transition, and add the new transition set
			TreeSet<NFAState> g = delta.remove(onSymb);
			g.add(to);
			delta.put(onSymb, g);
		}
	}

	/**
	 * 
	 * @return true if the given state is a final state, false otherwise
	 */
	public boolean isFinal() {
		return isFinal;
	}

	/**
	 * Compares two NFAStates by checking if they have the same name
	 */
	@Override
	public int compareTo(NFAState o) {
		return getName().compareTo(o.getName());
	}

}
