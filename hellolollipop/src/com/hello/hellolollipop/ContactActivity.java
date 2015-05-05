package com.hello.hellolollipop;

import java.lang.reflect.Array;
import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactActivity extends Activity {
	private ListView listView;
	private ArrayList<ContactInfo> list;
	private LayoutInflater inflater;
	private ContactAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		list = new ArrayList<>();
		listView = (ListView) findViewById(R.id.contact_list);
		adapter = new ContactAdapter(list,this);
		listView.setAdapter(adapter);
		this.getData();
	}

	public void getData() {
		ContactInfo contactsInfo;
		String[] projection = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup.NUMBER};
		String[] strsStrings = {Phone.CONTACT_ID, Phone.DISPLAY_NAME, Phone.NUMBER, Phone.PHOTO_ID};
		// getContentResolver().query(Contacts.CONTENT_URI, projection,
		// ContactsContract.CommonDataKinds.Phone.NUMBER + " = '"
		// + "'", selectionArgs, sortOrder)
		Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, strsStrings, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				contactsInfo = new ContactInfo();
				contactsInfo.setContractsPhone(cursor.getString(cursor.getColumnIndex(Phone.NUMBER)));// 得到手机号码
				contactsInfo.setContactsName(cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME)));
				// contactsInfo.setContactsPhotoId(cur.getLong(cur.getColumnIndex(Phone.PHOTO_ID)));
				long contactid = cursor.getLong(cursor.getColumnIndex(Phone.CONTACT_ID));
				long photoid = cursor.getLong(cursor.getColumnIndex(Phone.PHOTO_ID));
				// 如果photoid 大于0 表示联系人有头像 ，如果没有给此人设置头像则给他一个默认的
				if (photoid > 0) {
					// Uri uri = ContentUris.withAppendedId(
					// ContactsContract.Contacts.CONTENT_URI, contactid);
					// InputStream input = ContactsContract.Contacts
					// .openContactPhotoInputStream(cr, uri);
					// contactsInfo.setBitmap(BitmapFactory.decodeStream(input));
				} else {
					// contactsInfo.setBitmap(BitmapFactory.decodeResource(
					// this.getResources(), R.drawable.ic_launcher));
				}
				list.add(contactsInfo);
				System.out.println("---------联系人电话--:" + contactsInfo.getContactsName() + "---->" + contactsInfo.getContractsPhone());
				// localList.add(contactsInfo);
			}
			adapter.setList(list);
		}

	}
	class ContactAdapter extends BaseAdapter {
		private ArrayList<ContactInfo>  lists;
		public ContactAdapter(ArrayList<ContactInfo> lists,Context context) {
			inflater = LayoutInflater.from(context);
			this.lists=lists;
		}
		@Override
		public int getCount() {
			int count = list.size();
			if (count > 0) {
				return count;
			}
			return 0;
		}
		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		public void setList(ArrayList<ContactInfo> _list){
			this.lists=_list;
			notifyDataSetChanged();
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = inflater.inflate(R.layout.contact_item, parent, false);
				viewHolder.contactName_tv = (TextView) convertView.findViewById(R.id.contact_name);
				viewHolder.contactNumber_tv = (TextView) convertView.findViewById(R.id.contact_number);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			ContactInfo contactInfo = lists.get(position);
			viewHolder.contactName_tv.setText(contactInfo.getContactsName());
			viewHolder.contactName_tv.setText(contactInfo.getContractsPhone());
			return convertView;
		}
		class ViewHolder {
			TextView contactName_tv;
			TextView contactNumber_tv;
		}

	}
	class ContactInfo {
		private String contractsPhone;
		private String contactsName;
		public String getContractsPhone() {
			return contractsPhone;
		}
		public void setContractsPhone(String contractsPhone) {
			this.contractsPhone = contractsPhone;
		}
		public String getContactsName() {
			return contactsName;
		}
		public void setContactsName(String contactsName) {
			this.contactsName = contactsName;
		}
	}
}