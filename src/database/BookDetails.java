package database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BOOK_DETAILS", schema = "SYSTEM")
public class BookDetails {
    private int bookid;
    private String bookname;
    private UserDetails userDetails;

    @Id
    @Column(name = "BOOKID", nullable = false, precision = 0)
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "BOOKNAME", nullable = true, length = 20)
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDetails that = (BookDetails) o;
        return bookid == that.bookid &&
                Objects.equals(bookname, that.bookname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookid, bookname);
    }

    @ManyToOne
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "( id = "+bookid +", bookname = "+bookname +", userdetails = "+userDetails + ")";
    }
}
