package cn.tuyucheng.taketoday.springdoc.controller;

import cn.tuyucheng.taketoday.springdoc.exception.BookNotFoundException;
import cn.tuyucheng.taketoday.springdoc.model.Book;
import cn.tuyucheng.taketoday.springdoc.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
@RequestMapping("/api/book")
public class BookController {

   @Autowired
   private BookRepository repository;

   @Operation(summary = "Get a book by its id")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Found the book",
               content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))}),
         @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
         @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)})
   @GetMapping("/{id}")
   public Book findById(@Parameter(description = "id of book to be searched") @PathVariable long id) {
      return repository.findById(id)
            .orElseThrow(BookNotFoundException::new);
   }

   @GetMapping("/")
   public Collection<Book> findBooks() {
      return repository.getBooks();
   }

   @GetMapping("/filter")
   public Page<Book> filterBooks(@ParameterObject Pageable pageable) {
      return repository.getBooks(pageable);
   }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Book updateBook(@PathVariable("id") final String id, @RequestBody final Book book) {
      return book;
   }

   @PatchMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public Book patchBook(@PathVariable("id") final String id, @RequestBody final Book book) {
      return book;
   }

   @PostMapping("/")
   @ResponseStatus(HttpStatus.CREATED)
   public Book postBook(@NotNull @Valid @RequestBody final Book book) {
      return book;
   }

   @RequestMapping(method = RequestMethod.HEAD, value = "/")
   @ResponseStatus(HttpStatus.OK)
   public Book headBook() {
      return new Book();
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
   public long deleteBook(@PathVariable final long id) {
      return id;
   }
}