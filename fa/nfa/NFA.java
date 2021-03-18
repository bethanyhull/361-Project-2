package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

public class NFA implements NFAInterface {
	
//	Use correct data-structures for each of NFA’s element, e.g., set of states should be
//	modeled by a class implementing java.util.Set interface- probably use the ones in th DFA class
	private Set<NFAState> states;
	private NFAState start;
	private Set<Character> abc;
	
	public NFA() {
		states = new LinkedHashSet<NFAState>();
		abc = new LinkedHashSet<Character>();
	}
	
	@Override
	public void addStartState(String name) {
		NFAState s = checkIfExists(name);
		if(s == null){
			s = new NFAState(name);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the NFA");
		}
		start = s;
		
	}

	/**
	 * Check if a state with such name already exists
	 * @param name
	 * @return null if no state exist, or NFAState object otherwise.
	 */
	private NFAState checkIfExists(String name) {
		NFAState ret = null;
		for(NFAState s : states){
			if(s.getName().equals(name)){
				ret = s;
				break;
			}
		}
		return ret;
	}

	private void addState(NFAState s) {
		states.add(s);
		
	}

	@Override
	public void addState(String name) {
		NFAState s = checkIfExists(name);
		if( s == null){
			s = new NFAState(name);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the NFA");
		}
		
	}

	@Override
	public void addFinalState(String name) {
		NFAState s = checkIfExists(name);
		if( s == null){
			s = new NFAState(name, true);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the NFA");
		}
		
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		// TODO Auto-generated method stub- copy DFA for now
		
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub- copy DFA for now
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub- copy DFA for now
		return null;
	}

	@Override
	public State getStartState() {
		return start;
	}

	@Override
	public Set<Character> getABC() {
		return abc;
	}

	@Override
	public DFA getDFA() {
		// TODO Auto-generated method stub
		// each DFA state corresponds to a set of NFA states.You should track
		// inside getDFA() method whether a DFA state with the label(name) corresponding to the
		// string representation of the NFA states has been created or not. There is no requirements
		// on the order in which NFA states appear in a DFA’s label.
		
		// The implementation of public DFA getDFA() will require walking through an NFA, i.e.,
		// traversing a graph. To do so, you must implement the breadth-first search (BFS)
		// algorithm ( a loop iterating over a queue; an element of a queue is a set of NFA states).
		// Create a private method for this maybe??
		
		return null;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		// TODO Auto-generated method stub- Do this method before getDFA!!!
		// computes the set of NFA states that can be reached from the argument state s by going
		// only along ε transitions, including s itself. You must implement it using the depth-first
		// search algorithm (DFS) using a recursion, i.e., eClosure should invoke itself or another
		// helper method, e.g., private Set<NFAState> eClosure(NFAState s, Set<NFASate> visited)
		// that invokes itself.
		
		
		return null;
	}

}
