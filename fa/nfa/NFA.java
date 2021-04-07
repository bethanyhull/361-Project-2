package fa.nfa;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
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
		NFAState from = checkIfExists(fromState);
		NFAState to = checkIfExists(toState);
		if(from == null){
			System.err.println("ERROR: No NFA state exists with name " + fromState);
			System.exit(2);
		} else if (to == null){
			System.err.println("ERROR: No NFA state exists with name " + toState);
			System.exit(2);
		}
		from.addTransition(onSymb, to);
		
		if(!abc.contains(onSymb)){
			abc.add(onSymb);
		}
		
	}

	@Override
	public Set<? extends State> getStates() {
		
		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		Set<NFAState> ret = new LinkedHashSet<NFAState>();
		for(NFAState s : states){
			if(s.isFinal()){
				ret.add(s);
			}
		}
		return ret;
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
		
		//Psudocode
		//Create queue of SETS of NFA's. First set will always be the start state-- DONE
		//loop while queue is not empty-- DONE
		//on each char loop through set of NFA's to create DFA states and transitions 
		//add all NFA's in poweer set to NFA set
		//put in queue and repeat
		
		
		DFA dfa = new DFA();
		
		Queue<Set<NFAState>> nq = new LinkedList<Set<NFAState>>(); //Queue of NFA sets
					//Queue<DFAState> dq = new LinkedList<DFAState>();
					//DFAState dfaStart = new DFAState(start.getName());
		
		//Create set with start state and put it in the queue
		LinkedHashSet<NFAState> startSet = new LinkedHashSet<NFAState>();
		//??
		startSet.add((NFAState) getStartState());
		dfa.addStartState(startSet.toString());
		nq.add(startSet);
					//dfa.addStartState(start.getName());
					//q.add(dfa.getStartState());
		
		//for each set of NFAstates dequeued
		while(!nq.isEmpty()) {
			
			Set<NFAState> ncurrent = nq.remove();
					//DFAState dcurrent = dq.remove();
			
			
			for(Character c : abc) {
				if(c != 'e') {
				Set<NFAState> getTo = new LinkedHashSet<NFAState>();
					for(NFAState state : ncurrent) {
						getTo = state.getTo(c);
						
						if (getTo != null) {
							
							for (NFAState s : getTo) {
								if(s.hasNextE()) { // check each next state for e closure
									
						
									if(eClosure(s) != null) {
										Set<NFAState> setWithE = eClosure(s);
										
//										//TODO: Delete print statement
//										System.out.println("Eclosure for " + state.getName());
//										System.out.println(setWithE);
		
										getTo.addAll(setWithE);
									}
								}
							}
						}
					}
//							//TODO: Delete print statement
//							System.out.println("Get to");
//							System.out.println(getTo);
							
							//Add state name and transitions to DFA state
							Boolean isFinal = false;
							for (NFAState s : getTo) {
								for(State f : getFinalStates()) {
									if(f.getName().equals(s.getName())) {
										isFinal = true;
									}
								}
							}
							
							Boolean inDFA = false;
							
//							char[] newStateArray = nextDFAName.toCharArray();
//							Arrays.sort(newStateArray);
//							nextDFAName = newStateArray.toString();
							
							for(DFAState dstate : dfa.getStates()) {
								
								if(dstate.getName().equals(getTo.toString())) {
									inDFA = true;
								}
							}
							
							if(!inDFA) {
								//check if final
								if(isFinal) {
									dfa.addFinalState(getTo.toString());
								}else {
									dfa.addState(getTo.toString());
								}
								nq.add(getTo); //add new set to queue
							}
							
							dfa.addTransition(ncurrent.toString(), c, getTo.toString());
							
							//eclosure
							
						
					
				
				/////////////////////////////////////////////////////////////////
				// refactor stopping point. Not sure whats happening below this line....
				 
	//if
			}
			//add new states to queue
		}}
			
		//System.out.println(dfa.toString());
		return dfa;
		
		
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		return from.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		Set<NFAState> visited = new LinkedHashSet<NFAState>();
		visited = eClosureHelper(s, visited);
		
		return visited;
	}
	
	
	private Set<NFAState> eClosureHelper(NFAState s, Set<NFAState> visited ) {
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
					return visited;
				}
			}
			visited.add(state);
			visited.addAll(eClosureHelper(state, visited));
			
		}
		
		return visited;
		
		
	}

	
	

}
