package home.example.opdsbrowser.view;

import home.example.opdsbrowser.R;
import home.example.opdsbrowser.data.Book;

import java.util.List;

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
	
	public BookArrayAdapter(Context context, List<Book> books) {
		super(context, R.layout.book_item, books);
		this.context = context;
		this.books = books;
	}
	
	public Book getItem(int position) {
        return books.get(position);
    }
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewItem viewItem = new ViewItem();
		LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.book_item, null);
            viewItem.cover = (ImageView) convertView.findViewById(R.id.cover);
            viewItem.author = (TextView) convertView.findViewById(R.id.author);
            viewItem.title = (TextView) convertView.findViewById(R.id.title);
            viewItem.genre = (TextView) convertView.findViewById(R.id.genre);
            convertView.setTag(convertView);
        } else {
        	viewItem = (ViewItem) convertView.getTag();
        }
        Book book = (Book) getItem(position);
        viewItem.cover.setImageBitmap(book.getImage());
        viewItem.author.setText(book.getAuthor());
        viewItem.title.setText(book.getTitle());
        viewItem.genre.setText(book.getGenre());
        return convertView;
    }

}
