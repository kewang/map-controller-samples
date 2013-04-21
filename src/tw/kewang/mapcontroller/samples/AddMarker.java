package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.MapClick;
import tw.kewang.mapcontroller.MapController.MarkerAdd;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddMarker extends Activity {
	private MapView mv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_marker);

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
		MapController.whenMapClick(new MapClick() {
			@Override
			public void mapClicked(GoogleMap map, LatLng latLng) {
				MarkerOptions opts = new MarkerOptions();

				opts.position(latLng);
				opts.icon(BitmapDescriptorFactory.defaultMarker());
				opts.title("Test Title");
				opts.snippet("Summary");

				addMarker(opts);
			}
		});
	}

	private void addMarker(MarkerOptions opts) {
		MapController.add(opts, new MarkerAdd() {
			@Override
			public void markerAdded(GoogleMap map, Marker marker) {
				Toast.makeText(AddMarker.this, marker.getId(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void doExtra() {
		MapController.moveToMyLocation(false);
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