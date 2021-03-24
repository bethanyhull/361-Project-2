package fa.nfa;

import java.util.HashMap;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

public class NFAState extends State{

	private HashMap<Character, Set<NFAState>> delta;//delta
	private boolean isFinal;//remembers its type
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public NFAState(String name) {
		this.name = name;
		delta = new HashMap<Character, Set<NFAState>>();
		isFinal = false;
	}

	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param b the type of state: true - final, false - nonfinal.
	 */
	public NFAState(String name, boolean b) {
		this.name = name;
		delta = new HashMap<Character, Set<NFAState>>();
		this.isFinal = b;
	}

	public Set<NFAState> getTo(char onSymb) {
		Set<NFAState> ret = delta.get(onSymb);
		if(ret == null){
			 System.err.println("ERROR: NFAState.getTo(char symb) returns null on " + onSymb + " from " + name);
			 System.exit(2);
			}
		return ret;	
	}

	public void addTransition(char onSymb, NFAState to) {
		//delta.put(onSymb, to);
	}

	public boolean isFinal() {
		return isFinal;
	}
	
	// If your implementation requires it, you can add additional instance variables and methods to your NFAState
	// class.- For now model if after the DFAState class 

}
