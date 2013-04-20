package tw.kewang.mapcontroller.samples;

import tw.kewang.mapcontroller.samples.SamplesAdapter.SampleItem;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Main extends ListActivity {
	private SamplesAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setView();
	}

	private void setView() {
		adapter = new SamplesAdapter(this);

		adapter.addSample("Show my location", ShowMyLocation.class);
		adapter.addSample("Tracking my location", TrackingMyLocation.class);
		adapter.addSample("Show specific location", ShowSpecificLocation.class);

		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		SampleItem sample = (SampleItem) adapter.getItem(position);

		startActivity(new Intent(this, sample.clazz));
	}
}