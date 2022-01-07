package com.api.book.bootrestbook.controllers;
import java.util.List;
import java.util.Optional;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController --> Combination of @Controller+@ResponseBody
@RestController
public class BookController {
    
    //@RequestMapping(value = "/books",method = RequestMethod.GET)
    
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks()
    {
        List<Book> books = this.bookService.getAllBooks();

        if(books.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.of(Optional.of(books)) ;
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id")int id)
    {
        Book book = this.bookService.getBookById(id);
        if(book==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(book));
    }
   
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        Book returnedBook = null;
        try{
        returnedBook =bookService.addBook(book);
        System.out.println(returnedBook);
        return ResponseEntity.of(Optional.of(returnedBook));
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
    {
        try{
        bookService.deleteBook(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    //update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId)
    {
        try{
            this.bookService.updateBook(book,bookId);
            return ResponseEntity.ok().body(book);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/books/findByAuthor/{authorName}")
    public ResponseEntity<List<Book>> getBookByAuthorName(@RequestBody Book book,@PathVariable("authorName") String authorName)
    {
        try{
           List<Book> booksWrittenByAuthor =bookService.getBooksByAuthorName(authorName);
           if(booksWrittenByAuthor.size()<=0)
           {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
           }
           return ResponseEntity.of(Optional.of(booksWrittenByAuthor));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/books/findByTitle/{title}")
    public ResponseEntity<List<Book>> findByTitle(@PathVariable("title") String title)
    {
        try {
            List<Book> booksContainingTitleName = bookService.fetchingBooksByTitle(title);
            if(booksContainingTitleName.size()<=0)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.of(Optional.of(booksContainingTitleName));
        } catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
