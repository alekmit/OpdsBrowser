package home.example.opdsbrowser;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.io.BooksAsynkTask;
import home.example.opdsbrowser.utils.IOpdsService;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	public static final String BROADCAST_ACTION = "home.example.opdsbrowser.bookservice";
	
	public static final String ID_XML = "xml";
	
	private ListView listView;
	private BooksAsynkTask booksTask;
	
	private BroadcastReceiver breceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context ctx, Intent intent) {
			Bundle bundle = intent.getExtras();
			String xml = bundle.getString(ID_XML);
			startParser(xml);
		}
	};
	
	private void startParser(String xml){
		booksTask = new BooksAsynkTask(this);
		booksTask.execute(xml);
	}

	public ListView getListView() {
		return listView;
	}

	private OnClickListener refreshListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			//startService(new Intent(view.getContext(), OpdsService.class));
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*Button refbtn = (Button) findViewById(R.id.refreshBtn);
		refbtn.setOnClickListener(refreshListener);
		refbtn.setVisibility(Button.INVISIBLE);*/
		listView = (ListView) findViewById(R.id.listView1);
		IntentFilter ifilter = new IntentFilter(BROADCAST_ACTION);
	    registerReceiver(breceiver, ifilter);
		Intent intent = new Intent(this, OpdsService.class);
		intent.putExtra("url", IOpdsService.FLIBUSTA_URL + "/opds");
		startService(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
