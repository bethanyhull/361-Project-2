package fa.nfa;

import java.util.HashMap;

import fa.State;

public class NFAState extends State{
	

	private HashMap<Character,NFAState> delta;//delta
	private boolean isFinal;//remembers its type
	private boolean isInitial;
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public NFAState(String name){
		isFinal = false;
		isInitial = false;
		this.name = name;
		delta = new HashMap<Character, NFAState>();
		
	}
	
	public void isInitial() {
		isInitial = true;
	}
	
	public void isFinal() {
		isFinal = true;
	}

}
