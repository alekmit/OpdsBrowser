package home.example.opdsbrowser;

import java.util.Stack;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.io.BooksAsynkTask;
import home.example.opdsbrowser.utils.OpdsConstants;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView listView;
	private BooksAsynkTask booksTask;
	
	private Stack<String> navStack = new Stack<String>();
	
	private BroadcastReceiver breceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context ctx, Intent intent) {
			Bundle bundle = intent.getExtras();
			int action = intent.getExtras().getInt(OpdsConstants.ACTION_ID);
			switch (action){
			case OpdsConstants.ID_ACTION_XML:
				String xml = new String(bundle.getByteArray(OpdsConstants.ID_DATA));
				startParser(xml);
				break;
			case OpdsConstants.ID_ACTION_BYTES:
				break;
			}
			
		}
	};
	
	private void startParser(String xml){
		booksTask = new BooksAsynkTask(this);
		booksTask.execute(xml);
	}

	public ListView getListView() {
		return listView;
	}

	/*private OnClickListener refreshListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
		}

	};*/
	
	OnItemClickListener goListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, 
				View view, int position, long arg) {
			Book b = (Book) listView.getAdapter().getItem(position);
			String url = b.getLink();
			useService(url);
			navStack.push(url);
		}
		
	};
	
	private void init(){
		navStack = new Stack<String>();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		setContentView(R.layout.activity_main);
		/*Button refbtn = (Button) findViewById(R.id.refreshBtn);
		refbtn.setOnClickListener(refreshListener);
		refbtn.setVisibility(Button.INVISIBLE);*/
		listView = (ListView) findViewById(R.id.listView1);
		listView.setOnItemClickListener(goListener);
		IntentFilter ifilter = new IntentFilter(OpdsConstants.BROADCAST_ACTION);
	    registerReceiver(breceiver, ifilter);
		useService(OpdsConstants.ROOT_URL);
		navStack.push(OpdsConstants.ROOT_URL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void useService(String url){
		Intent intent = new Intent(this, OpdsService.class);
		intent.putExtra(OpdsConstants.ACTION_ID, OpdsConstants.ID_ACTION_XML);
		intent.putExtra(OpdsConstants.URL_ID, OpdsConstants.FLIBUSTA_URL + url);
		startService(intent);
	}
	
	 @Override
	 public void onBackPressed() {
		 if (!navStack.isEmpty()){
			 String url = navStack.pop();
			 useService(url);
		 } else{
			 super.onBackPressed();
		 }
		 return;
	 }

}
