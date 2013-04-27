package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.MapController;
import tw.kewang.mapcontroller.MapController.ChangePosition;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class ShowSpecificLocation extends Activity {
	private MapView mv;
	private LatLng latLng = new LatLng(25.03338, 121.56463);
	private Button btnMove;
	private Button btnAnimate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.show_specific_location);

		findView();
		setView(savedInstanceState);
		setListener();
		doExtra();
	}

	private void findView() {
		mv = (MapView) findViewById(R.id.map);
		btnMove = (Button) findViewById(R.id.button_move);
		btnAnimate = (Button) findViewById(R.id.button_animate);
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
		btnMove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MapController.moveTo(latLng);
			}
		});

		btnAnimate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MapController.animateTo(latLng, new ChangePosition() {
					@Override
					public void changed(GoogleMap map, CameraPosition position) {
						Toast.makeText(ShowSpecificLocation.this,
								position.toString(), Toast.LENGTH_SHORT).show();
					}
				});
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