package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.ClickCallback;
import tw.kewang.mapcontroller.MapController.MarkerCallback;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class WhenInfoWindowClick extends Activity {
	private MapView mv;
	private MapController mc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.when_info_window_click);

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

		mc = new MapController(mv.getMap());
	}

	private void setListener() {
		mc.whenMapClick(new ClickCallback() {
			@Override
			public void clicked(GoogleMap map, LatLng latLng) {
				MarkerOptions opts = new MarkerOptions();

				opts.position(latLng);
				opts.icon(BitmapDescriptorFactory.defaultMarker());
				opts.title("Test Title");
				opts.snippet("Summary");

				mc.addMarker(opts);
			}
		});

		mc.whenInfoWindowClick(new MarkerCallback() {
			@Override
			public void invokedMarker(GoogleMap map, Marker marker) {
				Toast.makeText(WhenInfoWindowClick.this,
						marker.getId() + ": " + marker.getTitle(),
						Toast.LENGTH_SHORT).show();

				marker.hideInfoWindow();
			}
		});
	}

	private void doExtra() {
		mc.moveToMyLocation(false);
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