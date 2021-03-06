package com.example.Steganography;

import java.io.File;

import com.example.Steganography.R;
import com.example.Steganography.PictureTab.EncodeAndDecodeTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TextTab extends Activity{
	
	TextView textTargetUri;
	RadioGroup radioGroup;
	ProgressDialog pd;
	EditText editText;
	EditText editText2;
	String writeTo;
	ImageView imageView;
	Uri targetUri;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texttab);


		Button buttonLoadImage = (Button)findViewById(R.id.button1);
		textTargetUri = (TextView)findViewById(R.id.textView3);
		Button buttonGo = (Button)findViewById(R.id.button3);
		radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		editText = (EditText)findViewById(R.id.editText1);
		editText2 = (EditText)findViewById(R.id.editText2);
		writeTo = new File(Environment.getExternalStorageDirectory() + File.separator + "encoded.png").toString();
		imageView = (ImageView)findViewById(R.id.imageView1);
		
		buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);
			}});
		
		buttonGo.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new EncodeAndDecodeTask().execute();
			}});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		targetUri = data.getData();
		if (targetUri.toString().charAt(0) == 'c')
			textTargetUri.setText(getRealPath(targetUri));
		else
			textTargetUri.setText(targetUri.getPath());
	}
	
	// And to convert the image URI to the direct file system path of the image file
	public String getRealPath(Uri contentUri) {

	        // can post image
	        String [] proj={MediaStore.Images.Media.DATA};
	        Cursor cursor = managedQuery( contentUri,
	                        proj, // Which columns to return
	                        null,       // WHERE clause; which rows to return (all rows)
	                        null,       // WHERE clause selection arguments (none)
	                        null); // Order-by clause (ascending by name)
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();

	        return cursor.getString(column_index);
	}
	
	public class EncodeAndDecodeTask extends AsyncTask<Void, Void, Void>{

		byte[] key;
		Bitmap image;
		String message;
		
		protected void onPreExecute(){
			pd = ProgressDialog.show(TextTab.this, "Working..", "Working on your image.", true, false);
		}
		@Override
		protected Void doInBackground(Void... params) {
			TwoReturn get;
			if (radioGroup.getCheckedRadioButtonId() == (R.id.radio0)){
				get = EncodeAndDecode.encodeText(textTargetUri.getText().toString(), writeTo, editText.getText().toString());
				//key = (byte[]) get.second;
				image = (Bitmap) get.first;
			}
			else if (radioGroup.getCheckedRadioButtonId() == (R.id.radio1)){
				message = EncodeAndDecode.decodeText(textTargetUri.getText().toString(), editText2.getText().toString());
			}
			return null;
		}
		protected void onPostExecute(Void useless){
			pd.dismiss();
			imageView.setImageBitmap(image);
			final AlertDialog alertDialog = new AlertDialog.Builder(TextTab.this).create();
			alertDialog.setMessage(message);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			      public void onClick(DialogInterface dialog, int which) {
			    	 // alertDialog.dismiss();
			    } });
			alertDialog.show();
		}
	}
}
