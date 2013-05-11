package home.example.opdsbrowser;

import home.example.opdsbrowser.io.OpdsAsynkTask;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OpdsService extends Service {
	
	private OpdsAsynkTask opdsTask;
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		opdsTask = new OpdsAsynkTask(this);
		opdsTask.execute(intent.getStringExtra("url"));
	    return super.onStartCommand(intent, flags, startId);
	  }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
