package fa.nfa;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

/**
 * 
 * NFA implementation that can convert to a DFA
 * @author Bethany Hull and Marie Phelan
 *
 */

public class NFA implements NFAInterface {
	
	private Set<NFAState> states;//set of states in the NFA
	private NFAState start;//keeps track of the start state
	private Set<Character> abc;//alphabet for the NFA
	
	/**
	 * Default constructor of an NFA
	 */
	public NFA() {
		states = new TreeSet<NFAState>();
		abc = new TreeSet<Character>();
	}
	
	/**
	 * Creates a start state for the NFA
	 * @param name of the start state
	 */
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
	 * @return null if no state exists, or NFAState object otherwise.
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

	/**
	 * Adds a state to the NFA
	 * @param s the state to be added to the NFA
	 */
	private void addState(NFAState s) {
		states.add(s);
	}

	/**
	 * Adds a state with the given name to the NFA
	 * @param name of the new state
	 */
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

	/**
	 * Adds a final state with the given name to the NFA
	 * @param name of the new state
	 */
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

	/**
	 * Creates a transition from a given state to another on a given character
	 * @param fromState the state to add the transition to
	 * @param onSymb the symbol to transition on
	 * @param toState the state to transition to
	 */
	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		//ensure that the given states exist
		NFAState from = checkIfExists(fromState);
		NFAState to = checkIfExists(toState);
		if(from == null){
			System.err.println("ERROR: No NFA state exists with name " + fromState);
			System.exit(2);
		} else if (to == null){
			System.err.println("ERROR: No NFA state exists with name " + toState);
			System.exit(2);
		}
		//add the transition
		from.addTransition(onSymb, to);
		//If the symbol is not already in the alphabet, add it 
		if(!abc.contains(onSymb)){
			abc.add(onSymb);
		}
		
	}

	/**
	 * @return the set of states for the NFA
	 */
	@Override
	public Set<? extends State> getStates() {
		return states;
	}

	/**
	 * @return the set of final states for the NFA
	 */
	@Override
	public Set<? extends State> getFinalStates() {
		Set<NFAState> ret = new TreeSet<NFAState>();
		for(NFAState s : states){
			if(s.isFinal()){
				ret.add(s);
			}
		}
		return ret;
	}

	/**
	 * @return the start state for the NFA
	 */
	@Override
	public State getStartState() {
		return start;
	}

	/**
	 * @return the alphabet for the NFA
	 */
	@Override
	public Set<Character> getABC() {
		return abc;
	}

	/**
	 * Creates a DFA from the NFA
	 * 	uses eClosure
	 * @return DFA version of the NFA
	 */
	@Override
	public DFA getDFA() {
		//create a new DFA
		DFA dfa = new DFA();
		//Create a queue of NFA sets for traversal
		Queue<Set<NFAState>> nq = new LinkedList<Set<NFAState>>();
		
		//Create set with start state and put it in the queue
		TreeSet<NFAState> startSet = new TreeSet<NFAState>();
		startSet.add((NFAState) getStartState());
		dfa.addStartState(startSet.toString());
		nq.add(startSet);
		
		//for each set of NFAstates dequeued
		while(!nq.isEmpty()) {
			
			Set<NFAState> ncurrent = nq.remove();			
			
			//loops through each character that is not 'e' to complete transitions
			for(Character c : abc) {
				if(c != 'e') {
					Set<NFAState> getTo = new TreeSet<NFAState>();
					for(NFAState state : ncurrent) {
						getTo.addAll(state.getTo(c));
						
						if (getTo != null) {
							
							for (NFAState s : getTo) {
								if(s.hasNextE()) { // check each next state for e closure
									if(eClosure(s) != null) {
										//add the states from eclosure to getTo set
										Set<NFAState> setWithE = eClosure(s);		
										getTo.addAll(setWithE);
									}
								}
							}
						}
					}			
					
					//Add state name and transitions to DFA state
					
					//check if the state is final
					Boolean isFinal = false;
					for (NFAState s : getTo) {
						for(State f : getFinalStates()) {
							if(f.getName().equals(s.getName())) {
								isFinal = true;
							}
						}
					}
						
					//check if the state already exists in the DFA
					Boolean inDFA = false;		
					for(DFAState dstate : dfa.getStates()) {
						if(dstate.getName().equals(getTo.toString())) {
							inDFA = true;
						}
					}
							
					//add the state to DFA if it is not already
					if(!inDFA) {
						//check if final
						if(isFinal) {
							dfa.addFinalState(getTo.toString());
						}else {
							dfa.addState(getTo.toString());
						}
						nq.add(getTo); //add new set to queue
					}
					//create the transition		
					dfa.addTransition(ncurrent.toString(), c, getTo.toString());
				}//end if
			}//end for
		}//end while
		return dfa;
	}

	/**
	 * @param from state
	 * @param onSymb character to transition on 
	 * @return NFAState transitioned to
	 */
	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getTo(onSymb);
	}

	/**
	 * This method creates a set to hold the visited state and calls the recursive helper method to find eClosure
	 * @param s the state to determine eclosure for
	 * @return the set of NFAStates that can be transitioned to on 'e'
	 */
	@Override
	public Set<NFAState> eClosure(NFAState s) {
		Set<NFAState> visited = new TreeSet<NFAState>();
		//invoke recursive helper
		visited = eClosureHelper(s, visited);
		
		return visited;
	}
	
	/**
	 * Recursive method to determine the set of eclosure states
	 * @param s the current state to find eclosure for
	 * @param visited the set of states visited on 'e' transitions
	 * @return the set of visited states
	 */
	private Set<NFAState> eClosureHelper(NFAState s, Set<NFAState> visited ) {
		//check if eClosure is possible on the given state
		if (!s.hasNextE()) {
			if (visited.size() == 0) {
				return null;
			}
			return visited;
		}
		
		Set<NFAState> newStates = s.getTo('e');
		
		for (NFAState state: newStates) {
			for(NFAState visitedState : visited) {
				if(state.getName().equals(visitedState.getName())) {
					//if the current state is already in visited, return to avoid an infinite loop
					return visited;
				}
			}
			visited.add(state);
			visited.addAll(eClosureHelper(state, visited));
			
		}
		
		return visited;

	}

}
