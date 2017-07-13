package plan;

import java.util.PriorityQueue;

public abstract class CommonPredicate implements Predicate{
	protected Object[] params;
	boolean satisfied;
	
	public CommonPredicate() {
	
	}
	
	public CommonPredicate(int size){
		params = new Object[size];
	}
	
	@Override
	public Object[] getParams() {
		return params;
	}
	
	@Override
	public boolean isSatisfied() {
		return satisfied;
	}
	
	@Override
	public void setSatisfied(boolean satisfy) {
		satisfied = satisfy;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		Predicate p = (Predicate)obj;
		if(this.getClass().equals(p.getClass()))
			if(p.getParams().length == params.length)
			{
				for(int i=0;i<params.length;i++)
				{
					if(!params[i].equals(p.getParams()[i]))
						return true;
				}
			}
		return false;
	}
	
	@Override
	public int hashCode() {
		String hash = this.getClass().toString();
		for(Object o : params)
			hash+=" "+o.hashCode();
		return hash.hashCode();
	}
	
	public String hashCodeString()
	{
		String hash = this.getClass().toString();
		for(Object o : params)
			hash+=" "+o.hashCode();
		return hash;
	}
	
}
