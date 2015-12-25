package lir;

import java.util.ArrayList;
import java.util.List;

public class StringLiterals {
	List<String> literals;
	List<StringLiteral> sLiterals;

	public StringLiterals() {
		literals = new ArrayList<String>();
		sLiterals = new ArrayList<StringLiteral>();
	}

	
	public int add(String s) {
		if(!literals.contains(s)) {
			literals.add(s);
			StringLiteral sl = new StringLiteral("str"+literals.indexOf(s),"\""+ s+"\"");
			sl.assignAddress(literals.indexOf(s));
			sLiterals.add(sl);
		}
		return literals.indexOf(s);
	}

	
	public List<StringLiteral> toStringLiteralList() {
		return sLiterals;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		for(int i=0; i<literals.size(); i++) {
			res.append("str"+i+": \""+literals.get(i)+"\"\n");
		}

		return res.toString();
	}
}
