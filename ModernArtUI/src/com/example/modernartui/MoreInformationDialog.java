package com.example.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MoreInformationDialog extends DialogFragment {

	private static final String TAG = "VisitMomaActivity";
	private static String MOMA_PAGE_URI = "http://www.moma.org/";

	// Build Information Dialog using InformationDialog.Builder
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// set the current activity as context
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setMessage(R.string.alert_text);

		// User cannot dismiss dialog by hitting back button
		builder.setCancelable(false);

		// Set up No Button to cancel the dialog
		builder.setNegativeButton(R.string.no_button,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});

		// Set up "Visit Moma" Button to open a webview ctivity with the
		// requested page
		builder.setPositiveButton(R.string.visit__moma_button,
				new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, int id) {
						loadMomaPage();
					}
				});
		return builder.create();
	}

	// Start a Browser Activity to view a web page or its URL
	private void loadMomaPage() {
		Log.d(TAG, "Starting Moma page");

		Uri webPage = Uri.parse(MOMA_PAGE_URI);
		Intent baseIntent = new Intent(Intent.ACTION_VIEW, webPage);
		String title = "Moma Site";

		Intent chooserIntent = Intent.createChooser(baseIntent, title);

		Log.i(TAG, "Chooser Intent Action:" + chooserIntent.getAction());
		// Verify the original intent will resolve to at least one activity
		startActivity(chooserIntent);
	}
}
