package home.example.opdsbrowser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private OnClickListener refreshListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			startService(new Intent(view.getContext(), OpdsService.class));
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button refbtn = (Button) findViewById(R.id.refreshBtn);
		refbtn.setOnClickListener(refreshListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
