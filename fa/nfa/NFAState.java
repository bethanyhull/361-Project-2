package fa.nfa;

import java.util.Set;

import fa.State;

public class NFAState extends State{

	public NFAState(String name) {
		// TODO Auto-generated constructor stub
	}

	public NFAState(String name, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public Set<NFAState> getTo(char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTransition(char onSymb, NFAState to) {
		// TODO Auto-generated method stub
		
	}

	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// If your implementation requires it, you can add additional instance variables and methods to your NFAState
	// class.- For now model if after the DFAState class 

}
