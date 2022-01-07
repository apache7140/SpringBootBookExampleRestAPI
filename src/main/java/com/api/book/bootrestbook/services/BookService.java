package com.api.book.bootrestbook.services;

//import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//creating a fake service inorder to achevie database UIDR operations
@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // private static List<Book> list =new ArrayList<>();
    
    // static{
    //     list.add(new Book(12,"The Complete Reference","Herbert Schield"));
    //     list.add(new Book(13,"The Monk who sold his ferrari","Robin Sharma"));
    //     list.add(new Book(14,"Who will cry when you die","Robin Sharma"));
    // }
    //get all books
    public List<Book> getAllBooks()
    {
        List<Book> list= (List<Book>)this.bookRepository.findAll();
        return list;
    }
    //get Single Book by id

    public Book getBookById(int id)
    {
        Book book =null;
        try{
        //book=list.stream().filter(bid -> bid.getId()==id).findFirst().get();
        book = this.bookRepository.findById(id);
        }
        catch(Exception e)
        { 
            e.printStackTrace();
        }
        return book;
    }
    public Book addBook(Book book)
    {
        //list.add(book);
        Book result = this.bookRepository.save(book);
        return result;
    }

    public void deleteBook(int id)
    {

    //    Book book =list.stream().filter(bid->bid.getId()==id).findFirst().get();
    //    System.out.println("++++++++++++++++++++++++"+book);
    //     return list.remove(book);
         //another way of doing deletion by overridding
      //  list = list.stream().filter(e->e.getId()!= id).collect(Collectors.toList());
        this.bookRepository.deleteById(id);

    }
    //ipdate the book
    public void updateBook(Book book,int bookId)
    {
        /*
         list= list.stream().map(b->{
            if(b.getId()==bookId)
            {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
        */

        /*
        if(list.contains(book))
        {
            int index = list.indexOf(book);
            list.set(index, book);
            return true;
        }
        return false;
        */
        /*
        for(int i=0;i<list.size();i++)
        {
            if(list.contains(book))
            {
                list.set(i, book);
                return true;
            }
        }*/
        book.setId(bookId);
        bookRepository.save(book);
    }
    
    public List<Book> getBooksByAuthorName(String author)
    {
        List <Book> bookswrittenByAuthor =bookRepository.findByAuthorContaining(author);
        return bookswrittenByAuthor;
    }
    public List<Book> fetchingBooksByTitle(String title)
    {
        List <Book> books =bookRepository.findByTitleContaining(title);
        return books;
    }

}
 