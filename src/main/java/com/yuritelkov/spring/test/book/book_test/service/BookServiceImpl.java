package com.yuritelkov.spring.test.book.book_test.service;



import com.yuritelkov.spring.test.book.book_test.dao.BookRepository;
import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly=true)
    public Book getBookById(int id) {
        Book book = null;
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            book = optional.get();
        } else { throw new RuntimeException(" Book not found for id ::  " + id);
        }
        return book;
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);

    }

    @Override
    public Page<Book> findPaginated(int pageNo, int pageSize) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.bookRepository.findAll(pageable);
    }


//
//    @Override
//    @Transactional(readOnly=true)
//    public Page<Book> search(String term, String publication, Pageable pageable) {
//        return bookRepository.getBySearchParams(term, publication, pageable);
//    }
//
//    @Override
//    @Transactional(readOnly=true)
//    public Page<Book> search(String term, String publication, boolean readAlReady, Pageable pageable) {
//        return bookRepository.getBySearchParamsAndReadAlready(term, publication, readAlReady, pageable);
//    }



}
