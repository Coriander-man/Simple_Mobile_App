package com.feva.myapp.data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class JSONFeed 
{
	private String title = null;
	private String pubdate = null;
	private int itemcount = 0;
	private List<JSONItem> itemlist;
	
	
	 public JSONFeed()
	{
		itemlist = new Vector(0); 
	}
	public int addItem(JSONItem item)
	{
		itemlist.add(item);
		itemcount++;
		return itemcount;
	}
	public JSONItem getItem(int location)
	{
		return itemlist.get(location);
	}
	public List getAllItems()
	{
		return itemlist;
	}
	public List getAllItemsForListView(){
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		int size = itemlist.size();
		for(int i=0;i<size;i++){
			HashMap<String, Object>	item = new HashMap<String, Object>();
			item.put(JSONItem.TITLE, itemlist.get(i).getTitle());
			item.put(JSONItem.PUBDATE, itemlist.get(i).getPubDate());
			data.add(item);
		}
		return data;
	}
	int getItemCount()
	{
		return itemcount;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setPubDate(String pubdate)
	{
		this.pubdate = pubdate;
	}
	public String getTitle()
	{
		return title;
	}
	public String getPubDate()
	{
		return pubdate;
	}
	
	
	
}
