package cn.settile.sblog.service.impl;

import cn.settile.sblog.model.entity.Book;
import cn.settile.sblog.model.entity.User;
import cn.settile.sblog.repository.BookDao;
import cn.settile.sblog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : lzjyz
 * @date : 2020-04-30 23:08
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public Book getBookById(long id) {
        return bookDao.getOne(id);
    }

    @Override
    public Set<Book> findBooksByUsername(String username) {
        return bookDao.findBooksByUser_UsernameOrderByIdDesc(username);
    }

    @Override
    public Set<Book> findBooksByUser(User user) {
        return bookDao.findBooksByUserOrderByIdDesc(user);
    }

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public void saveAll(Set<Book> books) {
        bookDao.saveAll(books);
        bookDao.flush();
    }

    @Override
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    public void delete(long bookId) {
        bookDao.deleteById(bookId);
    }

    @Override
    public boolean existsBookByUsername(long id, String username) {
        return bookDao.existsBookByIdAndUser_Username(id,username);
    }
}
