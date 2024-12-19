package KitchenLendingFacilityPackage;
/**
This class represents a tenant who has a card that allows them sign
items out of the kitchen tools lending facility.
*/

public class CardHolder{
	/**
	The name of the card holder.
	*/
	private String name;
	
	/*
	The apartment number.
	*/
	private int aptNum;
	
	/**
	The phone number of the cardholder.
	*/
	private String phoneNum;
	
	/**
	The cardholder's membership number.
	*/
	private int personMemNum;
	
	private static int memNum=10000;//static variable for the membership number.
	
	/**
	An array to store up to 8 kitchen tool items.
	*/
	private KitechenItem [] items = new KitechenItem [8];
	
	private int lenNum =0; //0-8 items to sign out.
		
		
	/**
	This method constructs a tenant with a name , apartment number,
	phone number and membership number.
	@param name The name of the tenant.
	@param aptNum The apartment number.
	@param phoneNum The phone number.
	*/
	public CardHolder(String name,int aptNum , String phoneNum){
		this.name = name;
		this.aptNum = aptNum;
		this.phoneNum = phoneNum;
		this.personMemNum=memNum;
		memNum++;
	
	}
		/**
		This method retrieves the name of the tenant.
		@return the name of the tenant.
		*/
		public String getName(){
			return name;
		}
		
		/**
		This method retrieves the apartment number.
		@return The apartment number.
		*/
		public int getAptNumber(){
			return aptNum;
		}
		
		/**
		This method retrieves the tenant's phone number.
		@return The tenant's phone number.
		*/
		public String getPhoneNumber(){
			return phoneNum;
		}
		
		/**
		This method retrieves the membership number of the tenant. 
		@return The membership number.
		*/
		public int getMembershipNumber(){
			return memNum;
		}
		
		/**
		This method allows the cardholder to sign out up to 8 kitchen items they wish
		to use and updates the list of signed out kitchen items in an array.
		@param itemToTakeOut The item to take out.
		@return result A true or false value if signing out an item was successful.
		*/
		public boolean signOut(KitechenItem itemToTakeOut){
			boolean result = false;
			
			if(lenNum < items.length){
				items[lenNum]=itemToTakeOut;
				lenNum++;
				result=true;
			}
			return result;
		}	
		/**
		This method allows the cardholder to return the kitchen items they borrowed 
		and updates the array.
		@param itemToReturn The item to return.
		@return result A true or false value if returning an item was successful.
		*/
		
		public boolean returnItem(KitechenItem itemToReturn){
			boolean result = false;
			for (int i=0;i<items.length-1;i++){
				if (itemToReturn == items[i] ){
					lenNum--;
					result = true;
					for(int j =i ;j<lenNum;j++){
						items[j]=items[j+1];
						result = true;
					}
				}
			}
			return result;
			}
			
			/**
			This method returns a copy of the list of borrowed kitchen items the cardholder
			currently has.
			*/
			public KitechenItem[] getSignedOutItems(){
			KitechenItem [] copylist = new KitechenItem[lenNum];
			for(int i = 0 ;i<lenNum;i++){
				copylist[i] = items[i];
			}
			return copylist;
			
		}
		
		/**
		This method sets the phone number of the tenant should it change.
		*/
		public void setPhoneNumber(String p){
			phoneNum = p;
		}
	}
	