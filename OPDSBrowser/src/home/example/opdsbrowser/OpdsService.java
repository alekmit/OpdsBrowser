package home.example.opdsbrowser;

import java.util.List;

import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.io.OpdsAsynkTask;
import home.example.opdsbrowser.utils.IOpdsService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OpdsService extends Service {
	
	private OpdsAsynkTask opdsTask;
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		opdsTask = new OpdsAsynkTask(this);
		opdsTask.execute(IOpdsService.FLIBUSTA_URL + "/opds"); //hard-coded -TBD
	    return super.onStartCommand(intent, flags, startId);
	  }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public void sendBackData(List<Book> books){
		 Intent intent = new Intent("home.example.opdsbrowser").putExtra("books", books.get(0));
	     sendBroadcast(intent);
	     stopSelf();
	}

}
