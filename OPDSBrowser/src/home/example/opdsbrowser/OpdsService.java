package home.example.opdsbrowser;

import home.example.opdsbrowser.io.OpdsAsynkTask;
import home.example.opdsbrowser.utils.IOpdsService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OpdsService extends Service {
	
	private OpdsAsynkTask opdsTask;
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		opdsTask = new OpdsAsynkTask();
		opdsTask.execute(IOpdsService.FLIBUSTA_URL);
	    return super.onStartCommand(intent, flags, startId);
	  }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
