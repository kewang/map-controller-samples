package tw.kewang.mapcontroller.samples;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;

import tw.kewang.mapcontroller.samples.SamplesAdapter.SampleItem;

public class Main extends ListActivity {
    private static final int APP_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private SamplesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setView();
        requestPermission();
    }

    private void setView() {
        adapter = new SamplesAdapter(this);

        adapter.addSample("Show my location", ShowMyLocation.class);
        adapter.addSample("Tracking my location", TrackingMyLocation.class);
        adapter.addSample("Show specific location", ShowSpecificLocation.class);
        adapter.addSample("Show multiple maps", ShowMultiMaps.class);
        adapter.addSample("When map click", WhenMapClick.class);
        adapter.addSample("Add marker", AddMarker.class);
        adapter.addSample("Add bulk markers", AddBulkMarkers.class);
        adapter.addSample("When info window click", WhenInfoWindowClick.class);
        adapter.addSample("Show traffic layer", ShowTrafficLayer.class);
        adapter.addSample("Show indoor layer", ShowIndoorLayer.class);
        adapter.addSample("Show specific bounds", ShowSpecificBounds.class);

        setListAdapter(adapter);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, APP_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        SampleItem sample = (SampleItem) adapter.getItem(position);

        startActivity(new Intent(this, sample.clazz));
    }
}