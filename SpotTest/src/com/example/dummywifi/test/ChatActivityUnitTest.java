package com.example.dummywifi.test;

 
import android.content.Intent;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import com.example.dummywifi.ChatActivity;;


public class ChatActivityUnitTest extends
	android.test.ActivityUnitTestCase<ChatActivity>{

	private int buttonID;
	private ChatActivity activity;
	
	public ChatActivityUnitTest() {
		super(ChatActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	        ChatActivity.class);
	    startActivity(intent, null, null);
	    activity = getActivity();
	  }
	
	
	public void testEditableText() {
		buttonID = com.example.dummywifi.R.id.send;
		assertNotNull(activity.findViewById(buttonID));
	}

	public void testListView() {
		buttonID = com.example.dummywifi.R.id.list;
		assertNotNull(activity.findViewById(buttonID));
	}
	
}
