package KitchenLendingFacilityPackage;

import java.text.NumberFormat;

/** 
 Test driver class to kitchenLendingFacility the CardHolder, KitechenItem
 and JuniorCardHolder classes.
*/
public class LendingFacilityTestDriver {
  public static void main(String[] args) {
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    
    //*********************************************************************************
    
    System.out.println("\n*** Test case #1: Create a CardHolder object & kitchenLendingFacility accessors");
    CardHolder sally = new CardHolder("Sally Smith",
                                      152,
                                      "454-1234");
    System.out.println("Name:     " + sally.getName()
                   + "\nAppt #:   " + sally.getAptNumber()
                   + "\nPhone:    " + sally.getPhoneNumber()
                   + "\nMember #: " + sally.getMembershipNumber());
    
    KitechenItem[] sallysItemList = sally.getSignedOutItems();
    if (sallysItemList.length == 0) {
      System.out.println("\nCorrect result: Sally has zero lending items.");
    }
    else {
      System.out.println("\n>> ERROR: Sally has more than zero lending items.");
    }
    
    //*********************************************************************************
    
    System.out.println
      ("\n*** Test case #2: Create a JuniorCardHolder object & kitchenLendingFacility accessors");
    JuniorCardHolder tommy = new JuniorCardHolder("Tommy Smith",
                                                  152,
                                                  "454-1234",
                                                  "Sally Smith");
    System.out.println("Name:     " + tommy.getName()
                   + "\nAppt #:   " + tommy.getAptNumber()
                   + "\nPhone:    " + tommy.getPhoneNumber()
                   + "\nMember #: " + tommy.getMembershipNumber()
                   + "\nGuardian: " + tommy.getGuardian());
    
    KitechenItem[] tommysItemList = tommy.getSignedOutItems();
    if (tommysItemList.length == 0) {
      System.out.println("\nCorrect result: Tommy has zero lending items.");
    }
    else {
      System.out.println("\n>> ERROR: Tommy has more than zero lending items.");
    }


    //*********************************************************************************
    
    System.out.println("\n*** Test case #3: Automatically generate a member number");
    CardHolder testMember = new CardHolder("John Doe",
                                           10,
                                          "455-1111");
    if (testMember.getMembershipNumber() == 1002) {
      System.out.println("Correct result: 1002 is the correct member number.");
    }
    else {
      System.out.println(">> ERROR: Invalid member number: " + 
                         testMember.getMembershipNumber());
    }
    
    //*********************************************************************************
    
    System.out.println
      ("\n*** Test case #4: Create KitechenItem objects & kitchenLendingFacility accessors");
    
    // Create several KitechenItem objects and kitchenLendingFacility the first one
    final int MAXITEMS = 8;
    KitechenItem[] testItemList = new KitechenItem[MAXITEMS + 1];
    String[] testItemDescription = {"Whisk",
                                    "Paring Knife",
                                    "Metric Measuring Spoon Set",
                                    "Creme Brule Torch",
                                    "Kitchen Scale",
                                    "Electric Knife",
                                    "16 Quart Stock Pot",                                    
                                    "Deep Fryer",
                                    "Crock-Pot"
                                    };
    for (int i=0; i<=MAXITEMS; i++) {
      testItemList[i] = new KitechenItem(testItemDescription[i],
                                        10.0 + (i * 5) + (i * 0.25),     // Original price
                                        (i%2) == 0);   // Every 2nd item is restricted
    } // end for loop
    
    System.out.println("Description: " + testItemList[0].getDescription()
                   + "\nOrig. Price: " + formatter.format(testItemList[0].getPrice())
                   + "\nRestricted:  " + testItemList[0].isRestricted());
    
    //*********************************************************************************
    
    System.out.println
      ("\n*** Test case #5: Change phone number for both CardHolder types");
    String testPhone1 = "453-4566";
    String testPhone2 = "555-1212";
    sally.setPhoneNumber(testPhone1);
    tommy.setPhoneNumber(testPhone2);

    if (sally.getPhoneNumber().equals(testPhone1)) {
      System.out.println("Correct result: Sally's phone number successfully changed.");
    }
    else {
      System.out.println(">> ERROR: Sally's phone number is incorrect: "
                         + sally.getPhoneNumber());
    }

    if (tommy.getPhoneNumber().equals(testPhone2)) {
      System.out.println("Correct result: Tommy's phone number successfully changed.");
    }
    else {
      System.out.println(">> ERROR: Tommy's phone number is incorrect: "
                         + tommy.getPhoneNumber());
    }
    
    //*********************************************************************************
    
    System.out.println("\n*** Test case #6: Sign out one KitechenItem");
    
    if(sally.signOut(testItemList[0])) {
      System.out.println("Correct result: Sally signed out an item successfully.");
      sallysItemList = sally.getSignedOutItems();
      if (sallysItemList.length == 1) {
        System.out.println("Correct result: Sally has one lending item.");
      }
      else {
        System.out.println(">> ERROR: Sally has other than one lending item.");
      }
    }
    else {
      System.out.println(">> ERROR: Sally was unable to sign out an item.");
    }

    //*********************************************************************************
    
    System.out.println("\n*** Test case #7: Sign out multiple LendingItems");
    
    boolean successfulSignOut = true;
    for(int i=1; i<=2; i++) {
      successfulSignOut = successfulSignOut && sally.signOut(testItemList[i]);
    }
    if (successfulSignOut) {
      System.out.println("Correct result: Sally signed out two more items successfully.");
      sallysItemList = sally.getSignedOutItems();
      if (sallysItemList.length == 3) {
        System.out.println("Correct result: Sally has three lending items.");
      }
      else {
        System.out.println(">> ERROR: Sally has other than three lending items.");
      }
    }
    else {
      System.out.println(">> ERROR: Sally was unable to sign out two more items.");
    }
    
    //*********************************************************************************
    
    System.out.println("\n*** Test case #8: Intentionally exceed the sign out limit");
    
    for(int i=3; i<MAXITEMS; i++) {
      sally.signOut(testItemList[i]);
    }
    if (!sally.signOut(testItemList[MAXITEMS])) {
      System.out.println("Correct result: Sally was prevented from signing out more than "
                         + MAXITEMS + " lending items.");
    }
    else {
      System.out.println(">> ERROR: Sally was able to sign out more than "
                         + MAXITEMS + " lending items.");
    }

    //*********************************************************************************
    
    System.out.println
      ("\n*** Test case #9: A junior card holder tries to sign out a restricted item");
    
    KitechenItem tommysItem = null;
    
    for (int i=0; i<2; i++) {
      if (tommy.signOut(testItemList[i])) {
        tommysItem = testItemList[i];        // Remember this for Test case #10
        if (testItemList[i].isRestricted()) {
          System.out.println(">> ERROR: Tommy was able to sign out a restricted item.");
        }
        else {
          System.out.println
            ("Correct result: Tommy was able to sign out an unrestricted item.");
        }
      }
      else {
        if (testItemList[i].isRestricted()) {
          System.out.println
            ("Correct result: Tommy was prevented from signing out a restricted item.");
        }
        else {
          System.out.println
            (">> ERROR: Tommy was prevented from signing out an unrestricted item.");
        }
      }
    }

    //*********************************************************************************
    
    System.out.println
      ("\n*** Test case #10: Returning the only item that was signed out");
    
    int tommyListLength = tommy.getSignedOutItems().length;

    if (tommy.returnItem(tommysItem)) {
      System.out.println("Correct result: Tommy's item was successfully returned.");
    }
    else {
      System.out.println(">> ERROR: Tommy's item was not successfully returned.");
    }

    if (tommy.getSignedOutItems().length == tommyListLength - 1) {
      System.out.println("Correct result: Tommy's list length changed appropriately.");
    }
    else {
      System.out.println(">> ERROR: Tommy's list length did not change appropriately.");
    }


    //*********************************************************************************
    
    System.out.println("\n*** Test case #11: Returning an item that was not signed out");
    
    if (tommy.returnItem(testItemList[3])) {
      System.out.println(">> ERROR: Returned an item that was not signed out.");
    }
    else {
      System.out.println("Correct result: "
        + "Unsuccessful attempt to return an item that was not signed out.");
    }

    //*********************************************************************************
    
    System.out.println
      ("\n*** Test case #12: Returning the first item that was signed out");
    
    int sallyListLength = sally.getSignedOutItems().length;

    if (sally.returnItem(testItemList[0])) {
      System.out.println("Correct result: Sally's first item was successfully returned.");
    }
    else {
      System.out.println(">> ERROR: Sally's first item was not successfully returned.");
    }

    if (sally.getSignedOutItems().length == sallyListLength - 1) {
      System.out.println("Correct result: Sally's list length changed appropriately.");
    }
    else {
      System.out.println(">> ERROR: Sally's list length did not change appropriately.");
    }

    System.out.println
      ("\nConfirm return: Whisk should be absent from the following list:");
    sallysItemList = sally.getSignedOutItems();
    for (int i=0; i < sallysItemList.length; i++) {
      System.out.println(sallysItemList[i].getDescription());
    }


    //*********************************************************************************
    
    System.out.println("\n*** Test case #13: Returning a mid-list item");
    
    sallyListLength = sally.getSignedOutItems().length;

    if (sally.returnItem(testItemList[3])) {
      System.out.println("Correct result: Creme Brule Torch was successfully returned.");
    }
    else {
      System.out.println(">> ERROR: Hidden Creme Brule was not successfully returned.");
    }

    if (sally.getSignedOutItems().length == sallyListLength - 1) {
      System.out.println("Correct result: Sally's list length changed appropriately.");
    }
    else {
      System.out.println(">> ERROR: Sally's list length did not change appropriately.");
    }

    System.out.println
      ("\nConfirm return: Creme Brule Torch should be absent from the following list:");
    sallysItemList = sally.getSignedOutItems();
    for (int i=0; i < sallysItemList.length; i++) {
      System.out.println(sallysItemList[i].getDescription());
    }

    //*********************************************************************************
    
    System.out.println("\n************* End of Test Cases ***************\n");
    
  } // end main method


} // end LendingTestDriver class