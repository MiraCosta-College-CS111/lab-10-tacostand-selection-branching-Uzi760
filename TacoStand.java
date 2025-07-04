public class TacoStand
{
    /* CONSTANT VARIABLES */
	public static final String BAR = "----------------------------------------";
	public static final String CARNE_ASADA_STEAK = "Carne Asada (Steak)";
	public static final String POLLO_ASADA_CHICKEN = "Pollo Asado (Chicken)"; 
	public static final String LENGUA_BEEF_TONGUE = "Lengua (Beef Tongue)";
	public static final String ULTIMATE_TACO = "Ultimate Taco";
	public static final double PRICE_CARNE_ASADA = 2.5; 
	public static final double PRICE_POLLO_ASADO = 1.75;
	public static final double PRICE_LENGUA = 3.0;
	public static final double PRICE_ULTIMATE = 18.0;
	public static final double COST_PER_TACO= 0.75;
	/* STATIC VARIABLES */
	private static int numAsada = 0, numPollo = 0, numLengua = 0, numUltimate = 0;
	private static double totalFunds = 0;

	/**
	 * Sets the store to zero for use in automated testing.
	 */
	public static void initialize()
	{
		numAsada = numPollo = numLengua = numUltimate = 0;
		totalFunds = 0.0D;
	}

	/**
	 * Outputs menu options (kinds of tacos) customer can use to order
	 */
	public static void printMenu()
	{
		System.out.println("Menu options:\n" + TacoStand.BAR);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 1, CARNE_ASADA_STEAK , PRICE_CARNE_ASADA);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 2, POLLO_ASADA_CHICKEN , PRICE_POLLO_ASADO);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 3, LENGUA_BEEF_TONGUE , PRICE_LENGUA);
		System.out.printf("%2d. %-21s [$%5.2f]%n", 4, ULTIMATE_TACO , PRICE_ULTIMATE);
		System.out.println(TacoStand.BAR);
	}
	
	/**
	* Returns a summary (all static variables) of the CURRENT status of the taco stand
	*
	* @return String containing current values related to taco stand, no new line at end
	*/
	public static String getStatus()
	{
		return String.format("%s%nMCC Taco Stand Status%n%s%n" +
			"%-23s$%-7.2f%n%s%n" +
			"%-23s%2d tacos%n" +
			"%-23s%2d tacos%n" +
			"%-23s%2d tacos%n" +
			"%-23s%2d tacos%n%s",
			TacoStand.BAR, TacoStand.BAR, 
      "Funds Available:", TacoStand.totalFunds, TacoStand.BAR,
      "# of Asada Left:", TacoStand.numAsada,
			"# of Pollo Left:", TacoStand.numPollo,
      "# of Lengua Left:", TacoStand.numLengua,
      "# of Ultimate Left:",TacoStand.numUltimate, TacoStand.BAR);
	}

	/**
	 * Increases totalFunds static variable
	 * 
	 * @param funds assumes >0 value added to total funds available for cart
	 */
	public static void addTotalFunds(int funds)
	{
		TacoStand.totalFunds += funds;
	}
	
	/**
	 * Checks if proposed budget to order supplies can be used to buy more supplies. If within total funds,
	 * will update total funds and increment number of each option of tacos based on budget. Otherwise,
	 * no variables are changed.
	 * 
	 * @param budget funds to use to order supplies
	 * 
	 * @return boolean representing if supplies could be ordered (within total funds)
	 */
	public static boolean orderSupplies(double budget)
	{
	if(budget <= TacoStand.totalFunds){
		 int tacosEach = (int)(Math.round(budget / COST_PER_TACO / 4));

	    TacoStand.totalFunds -= budget;

	    TacoStand.numAsada += tacosEach;
	    TacoStand.numPollo += tacosEach;
	    TacoStand.numLengua += tacosEach;
	    TacoStand.numUltimate += tacosEach;
	
		return true; 
	}
	else{ 
	return false;
	}
}

	/**
	 * Adds funds to total (static variable) based on kind of taco (different prices) and number of tacos
	 * in order. Will also update appropriate number of tacos left (static variables).
	 * 
	 * @param tacoOption menu option (kind of taco)
	 * @param numTacos number of tacos as part of order, assume > 0
	 */
	public static void updateTotalFunds(int tacoOption, int numTacos)
	{
		double price = 0;
		switch (tacoOption)
		{
		case 1: price = PRICE_CARNE_ASADA; numAsada -= numTacos; break;
		case 2: price = PRICE_POLLO_ASADO; numPollo -= numTacos; break; 
		case 3: price = PRICE_LENGUA; numLengua -= numTacos; break; 
		case 4: price = PRICE_ULTIMATE; numUltimate -= numTacos; break; 
		default: return; 
		}
	totalFunds += price * numTacos; 
	}
	
	
	/**
	 * Determines if taco order can be fullfilled (number of tacos for specific kinda are available)
	 * 
	 * @param tacoOption menu option (kind of taco)
	 * @param numTacos number of tacos as part of order
	 * 
	 * @return boolean representing if specific kind of tacos, for the number in order, are available
	 */
	public static boolean areTacosAvailable(int tacoOption, int numTacos)
	{
		switch (tacoOption) {
			case 1: return numAsada >= numTacos;
			case 2: return numPollo >= numTacos;
			case 3: return numLengua >= numTacos;
			case 4: return numUltimate >= numTacos; 
			default : return false; 
		}
	}
}