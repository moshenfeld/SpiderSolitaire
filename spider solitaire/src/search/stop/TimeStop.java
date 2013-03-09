package search.stop;

import search.Node;

public class TimeStop extends SimpleStop{
	private long currentTime;
	private long startTime;
	private long timeout;

	

	public TimeStop(int depth,long timeout) {
		super(depth);
		this.timeout = timeout;
	}
	
	@Override
	public boolean isStop(Node node) {
		if(super.isStop(node)){
			return true;
		}
		
		//Check Timeout.
		this.currentTime  = System.currentTimeMillis();
		if (currentTime  - startTime  > timeout)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime() {
		this.startTime = System.currentTimeMillis();
	}

}
