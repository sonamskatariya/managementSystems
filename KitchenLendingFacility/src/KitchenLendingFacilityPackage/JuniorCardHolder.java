package KitchenLendingFacilityPackage;
/**
This class represents younger tenants to encourage children's interest in cooking/baking.
*/
public class JuniorCardHolder extends CardHolder{
	/**
	The name of the Guardian;
	*/
	private String guardian;
	
	
	/**
	This method constructs a junior cald holder with a name ,apartment number,phone number
	and a Guardian.
	*/
	public JuniorCardHolder(String name , int aptNum, String phoneNum, String guardian){
		super(name,aptNum,phoneNum);
		this.guardian = guardian;
	}
	/**
	This method retrieves the guardian.
	*/
	public String getGuardian(){
		return guardian;
	}
	/**
	This method returns a true or false value if the item to return is a restricted item.
	*/
	public boolean signOut(KitechenItem itemToReturn){
        boolean result = true;
		if(!itemToReturn.isRestricted()){
			result = super.signOut(itemToReturn);
		}
		return result;
	}
}