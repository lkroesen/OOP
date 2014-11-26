package xml;

public class Wedstrijd 
{
	//Een ID zoals 002
	private String ID;
	//Een uitslag zoals: 2-2
	private String uitslag;
	// Twee teams
	private Team thuis;
	private Team ander;
	// Een datum wanneer de wedstrijd gespeeld is
	private String date;
	
	/**
	 * Constructor zonder uitslag
	 * @param id
	 * Voer een String met het wedstrijd ID in
	 * @param Thuis
	 * Voer een Team object in
	 * @param Uit
	 * Voer een Team object in
	 * @param Date
	 * Voer een String met de datum in
	 */
	public Wedstrijd(String id, Team Thuis, Team Ander, String Date)
	{
		ID = id;
		thuis = Thuis;
		ander = Ander;
		date = Date;
	}

	/**
	 * Constructor met uitslag
	 * @param id
	 * Voer een String in met een wedstrijd ID
	 * @param Uitslag
	 * Voer een String in met een uitslag
	 * @param Thuis
	 * Voer een Team object in
	 * @param Ander
	 * Voer een Team object in
	 * @param Date
	 * Voer een String met de Datum in
	 */
	public Wedstrijd(String id, String Uitslag, Team Thuis, Team Ander, String Date)
	{
		this(id,Thuis,Ander,Date);
		uitslag = Uitslag;
	}
	
	/**
	 * set de uitslag van een wedstrijd
	 * @param Uitslag
	 * Voer een string in met de uitslag
	 */
	public void setUitslag(String Uitslag)
	{
		uitslag = Uitslag;
	}
	
	/**
	 * get ID van wedstrijd
	 * @return
	 * Returns een String
	 */
	public String getID()
	{
		return ID;
	}
	
	/**
	 * get de Uitslag van een wedstrijd
	 * @return
	 * Returns een String
	 */
	public String getUitslag()
	{
		return uitslag;
	}
	
	/**
	 * get het thuis spelende team
	 * @return
	 * Return een Team object
	 */
	public Team getThuisTeam()
	{
		return thuis;
	}
	
	/**
	 * get het uit spelende team
	 * @return
	 * Return een Team Object
	 */
	public Team getUitTeam()
	{
		return ander;
	}
	
	/**
	 * get de datum waarop de wedstrijd gespeeld is
	 * @return
	 * Return een String
	 */
	public String getDatum()
	{
		return date;
	}
	
	/**
	 * Voer een object in om te vergelijken met het huidige object, geeft true of false gebaseerd op de gelijkheid
	 * @param other
	 * Voer een object in om te vergelijken
	 * @return
	 * Return true of false
	 */
	public boolean equals(Object other)
	{
		if (other instanceof Wedstrijd)
		{
			Wedstrijd that = (Wedstrijd) other;
			
			// De team equals methode moet hiervoor wel gemaakt zijn.
			if (	
					this.getID().equals(that.getID()) 
				&&	this.getDatum().equals(that.getDatum())
				&&	this.getThuisTeam().equals(that.getThuisTeam())
				&&	this.getUitTeam().equals(that.getUitTeam())
				&&	this.getUitslag().equals(that.getUitslag())
				);
				return true;
		}
		
		return false;
	}
	
	
}
