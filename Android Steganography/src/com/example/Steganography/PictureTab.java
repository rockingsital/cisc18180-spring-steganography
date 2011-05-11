package com.example.Steganography;

import com.example.Steganography.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PictureTab extends Activity{
	TextView textTargetUri1;
	TextView textTargetUri2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picturetab);

		Button buttonLoadImage = (Button)findViewById(R.id.button1);
		textTargetUri1 = (TextView)findViewById(R.id.textView3);
		Button buttonLoadImage2 = (Button)findViewById(R.id.button2);
		textTargetUri2 = (TextView)findViewById(R.id.textView4);

		buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);
			}});

		buttonLoadImage2.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 1);
			}});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Uri targetUri = data.getData();
		if (resultCode == RESULT_OK){
			if (requestCode == 0)
				textTargetUri1.setText(targetUri.toString());
			else if (requestCode ==1)
				textTargetUri2.setText(targetUri.toString());
		}
	}
}
