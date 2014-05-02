// Created by Luzius on May 2, 2014

package ch.fhnw.tankland.tanks;

public class TankAction {
	
	private int workDone;
	private int totWork;
	private ETankAction action;
	
	public TankAction(ETankAction action, int work){
		this.action = action;
		this.totWork = work;
		this.workDone = 0;
	}
	
	public void work(){
		workDone++;
	}
	
	public boolean isComplete(){
		return workDone == totWork;
	}
	
	public ETankAction getAction(){
		return action;
	}

}
