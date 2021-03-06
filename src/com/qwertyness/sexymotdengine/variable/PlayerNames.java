package com.qwertyness.sexymotdengine.variable;

import com.qwertyness.sexymotdengine.MotdState;

public class PlayerNames extends Variable {

	public PlayerNames() {
		super("playernames", VariableType.DYNAMIC);
	}
	
	public String getValue(String playerName, String ip) {
		String playerNames = "";
		for (String name : MotdState.getActivePlugin().playerNames()) {
			playerNames += name + ", ";
		}
		if (playerNames.length() >= 2) {
			playerNames = playerNames.substring(0, playerNames.length()-2);
		}
		return playerNames;
	}
	
	public String[] getRawValue() {
		return MotdState.getActivePlugin().playerNames();
	}
	
	public Value handleOperators(String operatorString, String playerName, String ip) {
		String value = this.getValue(playerName, ip);
		String operator = "";
		if (operatorString.startsWith("[") && operatorString.contains("]")) { 
			int index = Integer.parseInt(operatorString.substring(1, operatorString.indexOf("]")));
			if (index < this.getRawValue().length) {
				value = this.getRawValue()[index];
			}
			else {
				value = MotdState.getActiveMode().DEFAULT_PLAYER_NAME;
			}
			operator = "[" + index + "]";
		}
		return new Value(value, "%" + this.name + "%" + operator);
	}
}
