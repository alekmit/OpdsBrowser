package home.example.opdsbrowser.view;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;

import java.util.List;

import android.app.Activity;
import android.content.Context;
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
		LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.book_item, parent, false);
        ViewItem viewItem = new ViewItem();
        viewItem.cover = (ImageView) row.findViewById(R.id.cover);
        viewItem.author = (TextView) row.findViewById(R.id.author);
        viewItem.title = (TextView) row.findViewById(R.id.title);
        viewItem.genre = (TextView) row.findViewById(R.id.genre);
        Book book = (Book) getItem(position);
        viewItem.cover.setImageBitmap(book.getImage());
        viewItem.author.setText(book.getAuthor());
        viewItem.title.setText(book.getTitle());
        viewItem.genre.setText(book.getGenre());
        return row;
    }

}
