package fa.nfa;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

public class NFAState extends State implements Comparable<NFAState> {

	//private HashMap<Character, Set<NFAState>> delta;//delta
	private HashMap<Character,TreeSet<NFAState>> delta;
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

	public Set<NFAState> getTo(char onSymb) {
		Set<NFAState> ret = new TreeSet<NFAState>();// = delta.get(onSymb);

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
			TreeSet<NFAState> f = new TreeSet<NFAState>();
			f.add(to);
			delta.put(onSymb, f);
		}else {
			TreeSet<NFAState> g = delta.remove(onSymb);
			g.add(to);
			delta.put(onSymb, g);
	
		}
		
		
	}

	public boolean isFinal() {
		return isFinal;
	}

	@Override
	public int compareTo(NFAState o) {
		return getName().compareTo(o.getName());
	}
	
	// If your implementation requires it, you can add additional instance variables and methods to your NFAState
	// class.- For now model if after the DFAState class 

}
