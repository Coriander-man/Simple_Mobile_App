package com.feva.myapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TabShops extends FragmentActivity implements OnMarkerClickListener {

	private GoogleMap gMap;

    final static LatLng POINT_TST = new LatLng(22.296532, 114.171478);
    final static String TITLE_TST = "尖沙咀分店";
    final static String ADDR_TST = "尖沙咀北京道123號MTR C1出口";
	final static int IMG_TST = R.drawable.shop1;
    private Marker marker_TST;

    final static LatLng POINT_MK = new LatLng(22.322401, 114.172483);
    final static String TITLE_MK = "旺角分店";
    final static String ADDR_MK = "旺角東店18號鋪";
	final static int IMG_MK = R.drawable.shop2;
    private Marker marker_MK;

    final static LatLng POINT_TKO = new LatLng(22.303073, 114.262447);
    final static String TITLE_TKO = "將軍澳分店";
    final static String ADDR_TKO = "將軍澳高爾夫球場";
	final static int IMG_TKO = R.drawable.shop3;
    private Marker marker_TKO;

	final static LatLng POINT_START = POINT_TST;
	final static int zoomLevel = 11;
	final static int imgPin = R.drawable.pin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_shops);

		GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());

		gMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		if (gMap != null) {
			gMap.moveCamera(CameraUpdateFactory.newLatLng(POINT_START));
			gMap.animateCamera(CameraUpdateFactory.zoomTo(zoomLevel));

			marker_TST = gMap.addMarker(new MarkerOptions().position(POINT_TST)
					.title(TITLE_TST).snippet(ADDR_TST)
					.icon(BitmapDescriptorFactory.fromResource(imgPin)));

			marker_MK = gMap.addMarker(new MarkerOptions().position(POINT_MK)
					.title(TITLE_MK).snippet(ADDR_MK)
					.icon(BitmapDescriptorFactory.fromResource(imgPin)));

			marker_TKO = gMap.addMarker(new MarkerOptions().position(POINT_TKO)
					.title(TITLE_TKO).snippet(ADDR_TKO)
					.icon(BitmapDescriptorFactory.fromResource(imgPin)));

			gMap.setMyLocationEnabled(true);
			gMap.setOnMarkerClickListener(this);
			gMap.setInfoWindowAdapter(new InfoWindowAdapter() {

				@Override
				public View getInfoWindow(Marker marker) {
					return null;
				}

				@Override
				public View getInfoContents(Marker marker) {

					View v = getLayoutInflater().inflate(
							R.layout.layout_map_popup, null);

					TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
					tvTitle.setText(marker.getTitle());

					TextView tvSnippet = (TextView) v	.findViewById(R.id.tvSnippet);
					tvSnippet.setText(marker.getSnippet());

					ImageView ivShop = (ImageView) v.findViewById(R.id.ivShop);

					if (marker.getTitle().equals(TITLE_TST)) {
						ivShop.setImageResource(IMG_TST);
					}
					if (marker.getTitle().equals(TITLE_MK)) {
						ivShop.setImageResource(IMG_MK);
					}
					if (marker.getTitle().equals(TITLE_TKO)) {
						ivShop.setImageResource(IMG_TKO);
					}
					return v;

				}

			});
		}

	}

	

	@Override
	public boolean onMarkerClick(Marker marker) {
		marker.showInfoWindow();
		return true;
	}

}
