package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.MapController;
import android.app.Application;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;

public class SampleApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		try {
			MapController.initialize(this);
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();

			Toast.makeText(this,
					R.string.common_google_play_services_enable_text,
					Toast.LENGTH_SHORT).show();
		}
	}
}