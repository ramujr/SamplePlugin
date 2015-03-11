package com.example.myplugin;

import java.util.ArrayList;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.widget.Toast;

public class SamplePlugin extends CordovaPlugin{
	public static final String action1="addContact";
	private ArrayList<ContentProviderOperation> operations; 
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Toast.makeText(cordova.getActivity().getApplicationContext(), "First toast::"+action,30).show();
		//System.out.println("Action::::"+action+" Arrgs::"+args);
		try {
            if (action1.equals(action)) {            	
					Toast.makeText(cordova.getActivity().getApplicationContext(), "Second toast",30).show();
                JSONObject arg_object = args.getJSONObject(0);
                //Intent calIntent = new Intent(Intent.ACTION_INSERT, Contacts.CONTENT_URI);
                String displayName = arg_object.getString("name");
            	String phoneNumber = arg_object.getString("number");
//                ContentResolver cr = cordova.getActivity().getContentResolver();
//                ContentValues cv = new ContentValues();
//                cv.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, displayName);
//                cv.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
//                cv.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
//                cr.insert(ContactsContract.RawContacts.CONTENT_URI, cv);
            	//String DisplayName = "XYZ";
            	// String MobileNumber = "123456";
            	// String HomeNumber = "1111";
            	// String WorkNumber = "2222";
            	// String emailID = "email@nomail.com";
            	// String company = "bad";
            	// String jobTitle = "abcd";

            	 ArrayList < ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();

            	 ops.add(ContentProviderOperation.newInsert(
            	 ContactsContract.RawContacts.CONTENT_URI)
            	     .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
            	     .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
            	     .build());

            	 //------------------------------------------------------ Names
            	 if (displayName != null) {
            	     ops.add(ContentProviderOperation.newInsert(
            	     ContactsContract.Data.CONTENT_URI)
            	         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            	         .withValue(ContactsContract.Data.MIMETYPE,
            	     ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            	         .withValue(
            	     ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
            	     displayName).build());
            	 }

            	 //------------------------------------------------------ Mobile Number                     
            	 if (phoneNumber != null) {
            	     ops.add(ContentProviderOperation.
            	     newInsert(ContactsContract.Data.CONTENT_URI)
            	         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            	         .withValue(ContactsContract.Data.MIMETYPE,
            	     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            	         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
            	         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
            	     ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
            	         .build());
            	 }

            	 //------------------------------------------------------ Home Numbers
            /*	 if (HomeNumber != null) {
            	     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            	         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            	         .withValue(ContactsContract.Data.MIMETYPE,
            	     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            	         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
            	         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
            	     ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
            	         .build());
            	 }

            	 //------------------------------------------------------ Work Numbers
            	 if (WorkNumber != null) {
            	     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            	         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            	         .withValue(ContactsContract.Data.MIMETYPE,
            	     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
            	         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
            	         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
            	     ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
            	         .build());
            	 }

            	 //------------------------------------------------------ Email
            	 if (emailID != null) {
            	     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            	         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            	         .withValue(ContactsContract.Data.MIMETYPE,
            	     ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
            	         .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
            	         .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
            	         .build());
            	 }

            	 //------------------------------------------------------ Organization
            	 if (!company.equals("") && !jobTitle.equals("")) {
            	     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            	         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
            	         .withValue(ContactsContract.Data.MIMETYPE,
            	     ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
            	         .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
            	         .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
            	         .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
            	         .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
            	         .build());
            	 }*/

            	 // Asking the Contact provider to create a new contact                 
            	 try {
            		 
            	     cordova.getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            	     Toast.makeText(cordova.getActivity().getApplicationContext(), "Successfully inserted: " , Toast.LENGTH_SHORT).show();
            	     callbackContext.success();
            	     return true;
            	    
            	     
            	 } catch (Exception e) {
            	     e.printStackTrace();
            	     Toast.makeText(cordova.getActivity().getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            	 } 
                
                  
                  
            }
            callbackContext.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        } 
    }
}
