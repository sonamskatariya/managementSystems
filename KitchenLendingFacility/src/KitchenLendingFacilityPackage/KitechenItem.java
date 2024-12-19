package KitchenLendingFacilityPackage;
/**
This class represents any kitchen tool that is available at the facility.
*/

public class KitechenItem{
	/**
	The name of the kitchen tool item.
	*/
	private final String itemName;
	
	/**
	The price of the kitchen tool item.
	*/
	private final double price;
	
	/**
	Whether or not the kitchen item is restricted.
	*/
	private final boolean restricted;
	
	
	/**
	This method constructs a lending item using the item name , price and whether or
	not it is a restricted item.
	@param A brief description of the kitchen tool.
	@param The original price of the item.
	@param Whether or not the item is restricted.
	*/
	public KitechenItem(String itemName, double price, boolean restricted){
		this.itemName = itemName;
		this.price = price;
		this.restricted = restricted;
		}
		
	
	/**
	This method retrieves the name of the item.
	@return The name of the kitchen tool item.
	*/
	public String getDescription(){
		return itemName;
	}
	
	/**
	This method retrieves the price of the item.
	@return The price of the item.
	*/
	public double getPrice(){
		return price;
	}
	
	/**
	This method retrieves whether or not the item is restricted.
	@return whether or not the kitchen item is restricted.
	*/
	public boolean isRestricted(){
		return restricted;
	}
		
}