package home.example.opdsbrowser.data;

import android.os.Parcel;
import android.os.Parcelable;

public class BookWrapper implements Parcelable {
	
	public static final Parcelable.Creator PCreator = new Parcelable.Creator(){

		@Override
		public Object createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
		
	};
	
	private Book book;
	
	public BookWrapper(Book book){
		this.book = book;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

}
