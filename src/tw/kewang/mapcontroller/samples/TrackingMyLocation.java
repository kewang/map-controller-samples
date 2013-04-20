package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MoveMyLocation;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class TrackingMyLocation extends Activity {
	private MapView mv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tracking_my_location);

		findView();
		setView(savedInstanceState);
		setListener();
		doExtra();
	}

	private void findView() {
		mv = (MapView) findViewById(R.id.map);
	}

	private void setView(Bundle savedInstanceState) {
		mv.onCreate(savedInstanceState);

		try {
			MapController.attach(this, mv.getMap());
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}
	}

	private void setListener() {
	}

	private void doExtra() {
		MapController.moveToMyLocation(true, new MoveMyLocation() {
			@Override
			public void moved(GoogleMap map, Location location) {
				Toast.makeText(TrackingMyLocation.this, location.toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		mv.onResume();
	}

	@Override
	protected void onPause() {
		mv.onPause();

		super.onPause();
	}

	@Override
	protected void onDestroy() {
		MapController.detach();

		mv.onDestroy();

		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();

		mv.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		mv.onSaveInstanceState(outState);
	}
}