package home.example.opdsbrowser.view;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;
import home.example.opdsbrowser.data.OpdsContext;
import home.example.opdsbrowser.utils.AbstractImageAsyncTask;
import home.example.opdsbrowser.utils.OpdsUtils;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookArrayAdapter extends ArrayAdapter<Book> {
	
	private Context context;
	private List<Book> books;

	private class ViewItem {
        ImageView cover;
        TextView author;
        TextView title;
        TextView genre;
    }
	
	public BookArrayAdapter(Activity context, List<Book> books) {
		super(context, R.layout.book_item, books);
		this.context = context;
		this.books = books;
	}
	
	public Book getItem(int position) {
        return books.get(position);
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewItem viewItem = null;
		View row = convertView;
		if (row == null){
			LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.book_item, parent, false);
			viewItem = new ViewItem();
        	viewItem.cover = (ImageView) row.findViewById(R.id.cover);
        	viewItem.author = (TextView) row.findViewById(R.id.author);
        	viewItem.title = (TextView) row.findViewById(R.id.title);
        	viewItem.genre = (TextView) row.findViewById(R.id.genre);
        	row.setTag(viewItem);
		} else {
			viewItem = (ViewItem) row.getTag();
		}
        Book book = (Book) getItem(position);
        if (book.getImage() == null){
        	ImageAsynkTask imageTask = new ImageAsynkTask(book, this);
        	imageTask.execute(book.getCover());
        }
        Bitmap pic = book.getImage();
        viewItem.cover.setImageBitmap(pic != null ? 
        		pic : OpdsContext.getContext().getImage());
        viewItem.author.setText(book.getAuthor());
        viewItem.title.setText(book.getTitle());
        viewItem.genre.setText(book.getGenre());
        return row;
    }
	
	/*private void setCover(Book book){
		if (book.getCover() == null){
			book.setImage(OpdsContext.getContext().getImage());
			return;
		}
		String imgUrl = book.getCover();
		Bitmap bitmap = null;
		BitmapFactory.Options bmOpts = new BitmapFactory.Options();
		bmOpts.inSampleSize = 16;
		try {
			bitmap = BitmapFactory.decodeStream(
					new URL(FLIBUSTA_URL + imgUrl).openStream(), null, bmOpts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		book.setImage(bitmap);
	}*/
	
	private static class ImageAsynkTask extends AbstractImageAsyncTask {
		
		private BookArrayAdapter context;
		private Book book;

		ImageAsynkTask(Book book, BookArrayAdapter context){
			super(OpdsUtils.SCALE_ICON);
			this.book = book;
			this.context = context;
		}
		
		@Override
	    protected void onPostExecute(Bitmap result) {
			if (result != null){
				book.setImage(result);
				context.notifyDataSetChanged();
			}
		}
		
	}

}
