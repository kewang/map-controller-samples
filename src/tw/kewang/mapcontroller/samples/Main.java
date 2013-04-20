package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.MapController;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;

public class Main extends Activity {
	private MapView mv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		findView();
		setView();
		setListener();
		doExtra();
	}

	private void findView() {
		mv = (MapView) findViewById(R.id.map);
	}

	private void setView() {
		MapController.attach(this, mv.getMap());
	}

	private void setListener() {
	}

	private void doExtra() {
		MapController.moveToMyLocation(false);
	}
}