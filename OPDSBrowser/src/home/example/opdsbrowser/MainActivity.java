package home.example.opdsbrowser;

import java.util.List;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;
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
	
	private ListView listView;
	
	/*private BroadcastReceiver receiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context ctx, Intent intent) {
			Bundle bundle = intent.getExtras();
			Book book = (Book) bundle.getSerializable("books");
		}
	};*/

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
		Button refbtn = (Button) findViewById(R.id.refreshBtn);
		refbtn.setOnClickListener(refreshListener);
		listView = (ListView) findViewById(R.id.listView1);
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
