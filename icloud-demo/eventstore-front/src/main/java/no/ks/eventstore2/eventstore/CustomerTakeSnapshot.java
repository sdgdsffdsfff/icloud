package no.ks.eventstore2.eventstore;

public class CustomerTakeSnapshot {
	private int jid = -1;

	public int getJid() {
		return jid;
	}

	public void setJid(int jid) {
		this.jid = jid;
	}

	public CustomerTakeSnapshot(int jid) {
		super();
		this.jid = jid;
	}

}
