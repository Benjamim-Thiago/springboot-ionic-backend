package com.btsistemas.cursomc.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.btsistemas.cursomc.domain.Address;
import com.btsistemas.cursomc.domain.Category;
import com.btsistemas.cursomc.domain.City;
import com.btsistemas.cursomc.domain.Client;
import com.btsistemas.cursomc.domain.ItemRequestSale;
import com.btsistemas.cursomc.domain.Payment;
import com.btsistemas.cursomc.domain.PaymentWithCard;
import com.btsistemas.cursomc.domain.PaymentWithTicket;
import com.btsistemas.cursomc.domain.Product;
import com.btsistemas.cursomc.domain.RequestSale;
import com.btsistemas.cursomc.domain.State;
import com.btsistemas.cursomc.domain.enums.PaymentStatus;
import com.btsistemas.cursomc.domain.enums.Profile;
import com.btsistemas.cursomc.domain.enums.TypeClient;
import com.btsistemas.cursomc.repositories.AddressRepository;
import com.btsistemas.cursomc.repositories.CategoryRepository;
import com.btsistemas.cursomc.repositories.CityRepository;
import com.btsistemas.cursomc.repositories.ClientRepository;
import com.btsistemas.cursomc.repositories.ItemRequestSaleRepository;
import com.btsistemas.cursomc.repositories.PaymentRepository;
import com.btsistemas.cursomc.repositories.ProductRepository;
import com.btsistemas.cursomc.repositories.RequestSaleRepository;
import com.btsistemas.cursomc.repositories.StateRepository;

@Service
public class DBService {
	@Autowired
	private BCryptPasswordEncoder pe;
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
	@Autowired
	private ItemRequestSaleRepository itemRequestSaleRepository;

	public void instantiateTestDatabase() throws ParseException {
		// Produto Categoria

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama Mesa & Banho");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		Category cat8 = new Category(null, "Moveis");
		Category cat9 = new Category(null, "Brinquedo");
		Category cat10 = new Category(null, "Esporte & Lazer");
		Category cat11 = new Category(null, "Bebe");
		Category cat12 = new Category(null, "Suplemento & Vitamina");
		Category cat13 = new Category(null, "Construção");
		Category cat14 = new Category(null, "Musica");

		Product product1 = new Product(null, "Computador", new BigDecimal("2400.35"));
		Product product2 = new Product(null, "Impressora", new BigDecimal("840.55"));
		Product product3 = new Product(null, "Mouser", new BigDecimal("30.65"));
		Product product4 = new Product(null, "Mesa Escritório", new BigDecimal("300.00"));
		Product product5 = new Product(null, "Toalha", new BigDecimal("50.00"));
		Product product6 = new Product(null, "Cochão", new BigDecimal("200.00"));
		Product product7 = new Product(null, "TV de LED", new BigDecimal("2450.00"));
		Product product8 = new Product(null, "TV True Color", new BigDecimal("1450.00"));
		Product product9 = new Product(null, "Furadeira", new BigDecimal("70.00"));
		Product product10 = new Product(null, "Violão Giannini", new BigDecimal("150.00"));

		cat1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		cat2.getProducts().addAll(Arrays.asList(product2, product4));
		cat3.getProducts().addAll(Arrays.asList(product5, product6));
		cat4.getProducts().addAll(Arrays.asList(product7, product8));
		cat13.getProducts().addAll(Arrays.asList(product9));
		cat14.getProducts().addAll(Arrays.asList(product10));

		product1.getCategories().addAll(Arrays.asList(cat1));
		product2.getCategories().addAll(Arrays.asList(cat1, cat2));
		product3.getCategories().addAll(Arrays.asList(cat1));
		product4.getCategories().addAll(Arrays.asList(cat2));
		product5.getCategories().addAll(Arrays.asList(cat3));
		product6.getCategories().addAll(Arrays.asList(cat3));
		product7.getCategories().addAll(Arrays.asList(cat4));
		product8.getCategories().addAll(Arrays.asList(cat4));
		product9.getCategories().addAll(Arrays.asList(cat13));
		product10.getCategories().addAll(Arrays.asList(cat14));

		categoryRepository.saveAll(
				Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12, cat13, cat14));

		productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7,
				product8, product9, product10));

		// Cidade Estado
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

		// Client & Phones & Address
		Client cli1 = new Client(null, "Benjamim", "bebenjamimthiago@gmail.com",
				"36180027692", TypeClient.PESSOA_FISICA,pe.encode("123456"));
		cli1.addProfile(Profile.ADMIN);

		cli1.getPhones().addAll(Arrays.asList("9225163444", "92981905512"));

		Address address1 = new Address(null, "Rua José Francisco Nunes", "1344", "", "Mocambinho", "64010750", cli1,
				city1);
		Address address2 = new Address(null, "Avenida Sales Monte", "322", "proximo a Academia D.M.X", "Centro",
				"64010400", cli1, city1);

		cli1.getAddresses().addAll(Arrays.asList(address1, address2));

		Client cli2 = new Client(null, "Jose Felismino", "felismino@gmail.com", "31234567692",
				TypeClient.PESSOA_FISICA,pe.encode("54321"));

		cli2.getPhones().addAll(Arrays.asList("9221113444", "92222205512"));

		Address address3 = new Address(null, "Rua Jão Firmino", "4008", "proximidade a Helena modal", "Piraguitin",
				"6400000", cli2, city1);
		Address address4 = new Address(null, "Avenida Marechal", "1268", "", "Primavera", "64010300", cli2, city1);

		cli2.getAddresses().addAll(Arrays.asList(address3, address4));

		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(address1, address2, address3, address4));

		// Pedido

		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		RequestSale requestSale1 = new RequestSale(null, date.parse("14/05/2018 22:20"), cli1, address1);
		RequestSale requestSale2 = new RequestSale(null, date.parse("14/05/2018 01:09"), cli1, address2);

		Payment pg1 = new PaymentWithCard(null, PaymentStatus.SETTLED, requestSale1, 6);
		requestSale1.setPayment(pg1);

		Payment pg2 = new PaymentWithTicket(null, PaymentStatus.PENDING, requestSale2, date.parse("18/06/2018 00:00"),
				null);
		requestSale2.setPayment(pg2);

		cli1.getRequestSales().addAll(Arrays.asList(requestSale1, requestSale2));

		requestSaleRepository.saveAll(Arrays.asList(requestSale1, requestSale2));
		paymentRepository.saveAll(Arrays.asList(pg1, pg2));

		ItemRequestSale itemRequestSale1 = new ItemRequestSale(requestSale1, product1, 0.00, 1, product1.getPrice());
		ItemRequestSale itemRequestSale2 = new ItemRequestSale(requestSale1, product3, 0.00, 2, product3.getPrice());
		ItemRequestSale itemRequestSale3 = new ItemRequestSale(requestSale2, product2, 100.00, 1, product2.getPrice());

		requestSale1.getItems().addAll(Arrays.asList(itemRequestSale1, itemRequestSale2));
		requestSale2.getItems().addAll(Arrays.asList(itemRequestSale3));

		product1.getItems().addAll(Arrays.asList(itemRequestSale1));
		product2.getItems().addAll(Arrays.asList(itemRequestSale3));
		product3.getItems().addAll(Arrays.asList(itemRequestSale2));

		itemRequestSaleRepository.saveAll(Arrays.asList(itemRequestSale1, itemRequestSale2, itemRequestSale3));

	}
}
