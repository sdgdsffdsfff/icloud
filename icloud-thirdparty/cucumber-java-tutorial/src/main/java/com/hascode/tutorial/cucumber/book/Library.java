package com.hascode.tutorial.cucumber.book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Library {
	private final List<Book> store = new ArrayList<Book>();

	public void addBook(final Book book) {
		store.add(book);
	}

	public List<Book> findBooks(final Date from, final Date to) {
		Calendar end = Calendar.getInstance();
		end.setTime(to);
		end.roll(Calendar.YEAR, 1);

		List<Book> list = new ArrayList<Book>();
		for (Book book : store) {
			if (end.getTime().after(book.getPublished())
					&& from.before(book.getPublished())) {
				list.add(book);
			}
		}
		return list;
		// return store.stream().filter(book -> {
		// return from.before(book.getPublished()) &&
		// end.getTime().after(book.getPublished());
		// }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
	}
}
