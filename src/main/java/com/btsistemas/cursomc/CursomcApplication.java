package com.btsistemas.cursomc;

import com.btsistemas.cursomc.domain.Address;
import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.domain.City;
import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.domain.Payment;
import com.btsistemas.cursomc.domain.PaymentWithCard;
import com.btsistemas.cursomc.domain.PaymentWithTicket;
import com.btsistemas.cursomc.domain.Product;
import com.btsistemas.cursomc.domain.RequestSale;
import com.btsistemas.cursomc.domain.State;
import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import com.btsistemas.cursomc.domain.enums.TypeClient;
import com.btsistemas.cursomc.repositories.AddressRepository;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import com.btsistemas.cursomc.repositories.CityRepository;
import com.btsistemas.cursomc.repositories.ClientRepository;
import com.btsistemas.cursomc.repositories.PaymentRepository;
import com.btsistemas.cursomc.repositories.ProductRepository;
import com.btsistemas.cursomc.repositories.RequestSaleRepository;
import com.btsistemas.cursomc.repositories.StateRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RequestSaleRepository requestSaleRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Produto Categoria

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", new BigDecimal("2400.35"));
        Product p2 = new Product(null, "Impressora", new BigDecimal("840.55"));
        Product p3 = new Product(null, "Mouser", new BigDecimal("30.65"));

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        //Cidade Estado
        State state1 = new State(null, "Piauí");
        State state2 = new State(null, "Maranhão");
        State state3 = new State(null, "Ceará");

        City city1 = new City(null, "Teresina", state1);
        City city2 = new City(null, "Floriano", state1);

        City city3 = new City(null, "Timon", state2);
        City city4 = new City(null, "Caxias", state2);

        City city5 = new City(null, "Fortaleza", state3);

        state1.getCities().addAll(Arrays.asList(city1, city2));
        state2.getCities().addAll(Arrays.asList(city3, city4));
        state3.getCities().addAll(Arrays.asList(city5));

        stateRepository.saveAll(Arrays.asList(state1, state2, state3));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3, city4, city5));

        //Client & Phones & Address
        Client cli1 = new Client(null, "Carolina Alana Sônia Monteiro", "ccarolinaalanasoniamonteiro@lucaslima.com", "36180027692", TypeClient.PHYSICALPERSON);

        cli1.getPhones().addAll(Arrays.asList("9225163444", "92981905512"));

        Address address1 = new Address(null, "Rua José Luis Fortes", "4888", "proximo ao Hospital do mocambinho", "Mocambinho", "64010760", cli1, city1);
        Address address2 = new Address(null, "Avenida Campos Sales", "1268", "proximo a Academia Demostenes Ribeiro", "Centro", "64010300", cli1, city1);

        cli1.getAddresses().addAll(Arrays.asList(address1, address2));

        Client cli2 = new Client(null, "Jose Felismino", "felismino@gmail.com", "31234567692", TypeClient.PHYSICALPERSON);

        cli2.getPhones().addAll(Arrays.asList("9221113444", "92222205512"));

        Address address3 = new Address(null, "Rua Jão Firmino", "4008", "proximidade a Helena modal", "Piraguitin", "6400000", cli2, city1);
        Address address4 = new Address(null, "Avenida Marechal", "1268", "", "Primavera", "64010300", cli2, city1);

        cli2.getAddresses().addAll(Arrays.asList(address3, address4));

        clientRepository.saveAll(Arrays.asList(cli1, cli2));
        addressRepository.saveAll(Arrays.asList(address1, address2, address3, address4));
        
        //Pedido
        
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        RequestSale requestSale1 = new RequestSale(null, date.parse("14/05/2018 22:20"), cli1, address1);
        RequestSale requestSale2 = new RequestSale(null, date.parse("14/05/2018 01:09"), cli1, address2);
        
        Payment pg1  = new PaymentWithCard(null, PaymentStatus.SETTLED, requestSale1, 6);
        requestSale1.setPayment(pg1);
        
        Payment pg2  = new PaymentWithTicket(null, PaymentStatus.PENDING, requestSale2, date.parse("18/06/2018 00:00"), null);
        requestSale2.setPayment(pg2);
        
        cli1.getRequestSales().addAll(Arrays.asList(requestSale1, requestSale2));
        
        requestSaleRepository.saveAll(Arrays.asList(requestSale1, requestSale2));
        paymentRepository.saveAll(Arrays.asList(pg1, pg2));
        
        
    }
}
