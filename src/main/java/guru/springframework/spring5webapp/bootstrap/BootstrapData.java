package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric=new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Author JKRowling=new Author();
        JKRowling.setFirstName("JK");
        JKRowling.setLastName("Rowling");

        Book ddd=new Book();
        ddd.setTitle("Domain driven design");
        ddd.setIsbn("123456");

        Book hp=new Book();
        hp.setTitle("Prisioner of Azkaban");
        hp.setIsbn("3355666");

        Publisher pu=new Publisher();
        pu.setAddress("Alkmaarstraat 31");
        pu.setCity("Amsterdam");
        pu.setZip("1103MR");
        pu.setState("Amsterdam");
        pu.setPublisherName("MyPublisher");

        Author ericSaved=authorRepository.save(eric);
        Author jkSaved=authorRepository.save(JKRowling);

        Book dddSaved=bookRepository.save(ddd);
        Book hpSaved=bookRepository.save(hp);

        Publisher puSaved=publisherRepository.save(pu);

        dddSaved.setPublisher(puSaved);
        hp.setPublisher(puSaved);


        ericSaved.getBooks().add(dddSaved);
        dddSaved.getAuthors().add(ericSaved);

        jkSaved.getBooks().add(hpSaved);
        hpSaved.getAuthors().add(jkSaved);

        bookRepository.save(dddSaved);
        authorRepository.save(ericSaved);

        bookRepository.save(hpSaved);
        authorRepository.save(jkSaved);



        System.out.println("In Bootstrap");
        System.out.println("Author count "+authorRepository.count());
        System.out.println("Publisher count "+ publisherRepository.count() );
    }
}
