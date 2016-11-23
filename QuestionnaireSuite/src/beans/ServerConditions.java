package beans;

public class ServerConditions {	
	public String cpu;
	public String memory;
	public String io;
	public String exchangeRoom;
	public String process;

	public ServerConditions(){
		
	}
	
	public ServerConditions(String cpu, String memory, String io, String exchangeRoom, String process) {
		this.cpu = cpu;
		this.memory = memory;
		this.io = io;
		this.exchangeRoom = exchangeRoom;
		this.process = process;
	}


	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getExchangeRoom() {
		return exchangeRoom;
	}

	public void setExchangeRoom(String exchangeRoom) {
		this.exchangeRoom = exchangeRoom;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	

}
