package tw.kewang.mapcontroller.samples;

import java.util.ArrayList;

import tw.kewang.mapcontroller.MapController;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

public class ShowMultiMaps extends Activity {
	private static final LatLng[] latLngs = { new LatLng(25.0391667, 121.525),
			new LatLng(22.6252777778, 120.3088888889),
			new LatLng(24.754938, 121.752033),
			new LatLng(24.147498, 120.673313) };

	private ArrayList<MapView> lstMv = new ArrayList<MapView>();
	private ArrayList<MapController> lstMc = new ArrayList<MapController>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show_multi_maps);

		findView();
		setView(savedInstanceState);
		setListener();
		doExtra();
	}

	private void findView() {
		lstMv.add((MapView) findViewById(R.id.map1));
		lstMv.add((MapView) findViewById(R.id.map2));
		lstMv.add((MapView) findViewById(R.id.map3));
		lstMv.add((MapView) findViewById(R.id.map4));
	}

	private void setView(Bundle savedInstanceState) {
		for (MapView mv : lstMv) {
			mv.onCreate(savedInstanceState);
			lstMc.add(new MapController(mv.getMap()));
		}
	}

	private void setListener() {
	}

	private void doExtra() {
		for (int i = 0; i < lstMc.size(); i++) {
			MapController mc = lstMc.get(i);
			LatLng latLng = latLngs[i];

			mc.moveTo(latLng, 17);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		for (MapView mv : lstMv) {
			mv.onResume();
		}
	}

	@Override
	protected void onPause() {
		for (MapView mv : lstMv) {
			mv.onPause();
		}

		super.onPause();
	}

	@Override
	protected void onDestroy() {
		for (MapView mv : lstMv) {
			mv.onDestroy();
		}

		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();

		for (MapView mv : lstMv) {
			mv.onLowMemory();
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		for (MapView mv : lstMv) {
			mv.onSaveInstanceState(outState);
		}
	}
}