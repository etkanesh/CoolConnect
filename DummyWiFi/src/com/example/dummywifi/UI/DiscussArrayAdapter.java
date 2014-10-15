package com.example.dummywifi.UI;

import java.util.ArrayList;
import java.util.List;

import com.example.dummywifi.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiscussArrayAdapter extends ArrayAdapter<TextBubble> {

	private TextView bubbleTextView;
	private List<TextBubble> textBubbles = new ArrayList<TextBubble>();
	private LinearLayout wrapper;

	@Override
	public void add(TextBubble object) {
		textBubbles.add(object);
		super.add(object);
	}

	public DiscussArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public void setTextBubbleList(List<TextBubble> list) {
		this.textBubbles = list;
	}

	public int getCount() {
		return this.textBubbles.size();
	}

	public TextBubble getItem(int index) {
		return this.textBubbles.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listitem_discuss, parent, false);
		}

		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

		TextBubble messageBubble = getItem(position);

		bubbleTextView = (TextView) row.findViewById(R.id.comment);
		
		if (messageBubble.comment != null && bubbleTextView != null) {
			bubbleTextView.setText(messageBubble.comment);
	
			bubbleTextView.setBackgroundResource(messageBubble.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
			wrapper.setGravity(messageBubble.left ? Gravity.LEFT : Gravity.RIGHT);
		}
		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}