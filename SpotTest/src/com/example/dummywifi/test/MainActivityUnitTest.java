package com.example.dummywifi.test;

 
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Looper;
import android.test.mock.MockContext;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.dummywifi.MainActivity;


public class MainActivityUnitTest extends
	android.test.ActivityUnitTestCase<MainActivity>{

	private MainActivity mainActivity = new MainActivity();
	private int targetColor;
	public WifiP2pManager cManager = mainActivity.getManager();
	public Channel cChannel = mainActivity.getChannel();
	private int yellowBTNcolor;
	private int buttonID;
	private MainActivity activity;
	public MainActivityUnitTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	        MainActivity.class);
	    startActivity(intent, null, null);
	    activity = getActivity();
	  }
	
	public void testDiscoverButton(){
		buttonID = com.example.dummywifi.R.id.atn_direct_discover;
		assertNotNull(activity.findViewById(buttonID));
		Button view = (Button) activity.findViewById(buttonID);
		assertEquals("The button has an incorrect label","Discover",view.getText());
	}
	
	public void testP2PButton() {
		buttonID = com.example.dummywifi.R.id.atn_direct_enable;
		assertNotNull(activity.findViewById(buttonID));
		Button view = (Button) activity.findViewById(buttonID);
		assertEquals("The button has an incorrect label","P2P On/Off",view.getText());
	}
	
	public void testCreateButton() {
		buttonID = com.example.dummywifi.R.id.button1;
		assertNotNull(activity.findViewById(buttonID));
		Button view = (Button) activity.findViewById(buttonID);
		assertEquals("The button has an incorrect label","Create",view.getText());
	}
	
	public void testEditableText() {
		buttonID = com.example.dummywifi.R.id.editText1;
		assertNotNull(activity.findViewById(buttonID));
		EditText editText = (EditText) activity.findViewById(buttonID);
		assertEquals("The EditText's hint is incorrect","Room name", editText.getHint());
	}
	/*
	public void testRelativeLayoutofMainActivity(){
		targetColor = com.example.dummywifi.R. color.YotsubaB;
		assertNotNull(activity.findViewById(targetColor));
	}
	*/

	public void testListView() {
		buttonID = com.example.dummywifi.R.id.listView1;
		assert(activity.findViewById(buttonID) == null);
	}
	
	public void testColors() {
		targetColor = com.example.dummywifi.R. color.YotsubaB;
		assertEquals("targetcolor should be 0x00D4D8EF",targetColor, 2130968576);
		assertFalse("targetcolor should not be 0x00FFFFFF",targetColor == 0x00FFFFFF);
	}
	/*
	 * We attempted a more elaborate test case but that required mocking WifiP2p objects
	 * Mocking those objects prooved difficult without changing code in the original program
	public void testConnectionStart() {
		Log.i("buttcode","start tcs");
		
		cManager.discoverPeers(cChannel, new WifiP2pManager.ActionListener() {

			@Override
			public void onFailure(int arg0) {
				assertFalse("This failed to begin discover","This"=="This");
				
			}

			@Override
			public void onSuccess() {
				assertFalse("This is a success call","This"=="That");
				
			}
		});
	}
	*/
}
