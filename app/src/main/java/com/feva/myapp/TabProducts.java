package com.feva.myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.feva.myapp.data.JSONFeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class TabProducts extends Activity implements RecyclerItemClickListener.OnItemClickListener {
	final static int WP_ID = 187635514;
	String URL_LINK = "https://public-api.wordpress.com/wp/v2/sites/" + WP_ID + "/pages/?_embed&order=asc&orderby=menu_order&parent[]=";
	int child_id;

	private JSONFeed feed = null;
	ArrayList<Page> listItems = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_products);

		Intent startingIntent = getIntent();

		Bundle bundle = startingIntent.getBundleExtra("android.intent.extra.products");
		child_id = bundle.getInt("child_id");
		URL_LINK += child_id;

		if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD)
		{
		    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		        .detectDiskReads()
		        .detectDiskWrites()
		        .detectNetwork()   // or .detectAll() for all detectable problems
		        .penaltyLog()
		        .build());
		}

		feed = new JSONFeed();
		listItems = fetchPublicTimeline();

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		ProductAdapter adapter = new ProductAdapter(this, listItems);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);

		recyclerView.addOnItemTouchListener(
				new RecyclerItemClickListener(this, recyclerView , this)
		);
	}

	public ArrayList<Page> fetchPublicTimeline() {
		ArrayList<Page> listTitles = new ArrayList<Page>();

		try {
			URL wp_backend = new URL(URL_LINK);
			URLConnection tc = wp_backend.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

			String line;
			while ((line = in.readLine()) != null) {
				JSONArray jProdCat = new JSONArray(line);

				for (int i = 0; i < jProdCat.length(); i++) {
					JSONObject jo = (JSONObject) jProdCat.get(i);
					listTitles.add(new Page(
							jo.getInt("id")
							,
							jo.getJSONObject("title").getString("rendered").replace("&nbsp;", " ")
							,
							jo.getJSONObject("content").getString("rendered")
							,
							jo.getString("modified")
							,
							jo.getJSONObject("_embedded").getJSONArray("wp:featuredmedia").getJSONObject(0).getString("source_url")
					));
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTitles;
	}

	@Override
	public void onItemClick(View view, int position) {

		Intent itemintent = new Intent(this, DetailsActivity.class);
		int prod_id = listItems.get(position).getId();

		Bundle b = new Bundle();
		b.putString("title", listItems.get(position).getTitle());
		b.putString("description", listItems.get(position).getContent());
		b.putString("pubdate", listItems.get(position).getDate());

		itemintent.putExtra("android.intent.extra.jsonItem.fullTest", b);
		startActivityForResult(itemintent, 0);
	}

	@Override
	public void onLongItemClick(View view, int position) {

	}

}
