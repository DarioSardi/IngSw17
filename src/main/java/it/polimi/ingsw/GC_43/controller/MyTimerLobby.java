package it.polimi.ingsw.GC_43.controller;

public class MyTimerLobby implements java.lang.Runnable{

    private Integer time;
	private Lobby lobby;
	
    /**
     * 
     * @param time in secondsssss
     */
	public MyTimerLobby(Integer time,Lobby lobby) {
		this.time = time;
		this.lobby=lobby;
	}

	@Override
    public void run() {
        this.runTimer();
    }

    public void runTimer(){
         while (time>0){
          if (time%10==0) {
			this.lobby.broadcastMsg("Remaining: " + time + " seconds");
		}
		try {
			time--;
			if(this.lobby.getPlayersNumber()==this.lobby.maxPlayers){time=0;}
            Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
           }
           catch (InterruptedException e) {
             
           }
         }
    }

}