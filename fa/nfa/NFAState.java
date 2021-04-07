package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

public class NFAState extends State{

	//private HashMap<Character, Set<NFAState>> delta;//delta
	private HashMap<Character,LinkedHashSet<NFAState>> delta;
	private boolean isFinal;//remembers its type
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public NFAState(String name) {
		this.name = name;
		delta = new HashMap<Character, LinkedHashSet<NFAState>>();
		isFinal = false;
	}

	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param b the type of state: true - final, false - nonfinal.
	 */
	public NFAState(String name, boolean b) {
		this.name = name;
		delta = new HashMap<Character, LinkedHashSet<NFAState>>();
		this.isFinal = b;
	}

	public Set<NFAState> getTo(char onSymb) {
		Set<NFAState> ret = new LinkedHashSet<NFAState>();// = delta.get(onSymb);

		if (delta.containsKey(onSymb)) {
			ret = delta.get(onSymb);
		}
//		System.out.println(ret);
		return ret;	
	}
	
	public boolean hasNextE() {
		
		if(delta.containsKey('e')) {
			return true;
		}

		return false;	
		
//		for(Entry<Character, NFAState> set: delta.entrySet()) {
//
//			if (set.getKey() == 'e') {
//				return true;
//			}
//		}
//		return false;
	}

	public void addTransition(char onSymb, NFAState to) {
		if (!delta.containsKey(onSymb)) {
			LinkedHashSet<NFAState> f = new LinkedHashSet<NFAState>();
			f.add(to);
			delta.put(onSymb, f);
		}else {
			LinkedHashSet<NFAState> g = delta.remove(onSymb);
			g.add(to);
			delta.put(onSymb, g);
	
		}
		
		
	}

	public boolean isFinal() {
		return isFinal;
	}
	
	// If your implementation requires it, you can add additional instance variables and methods to your NFAState
	// class.- For now model if after the DFAState class 

}
