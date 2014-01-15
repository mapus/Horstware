package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.nttdata.emea.devschool.vehicleordering.business.Customer;
import com.nttdata.emea.devschool.vehicleordering.business.CustomerDAO;
import com.nttdata.emea.devschool.vehicleordering.business.Model;
import com.nttdata.emea.devschool.vehicleordering.business.ModelDAO;
import com.nttdata.emea.devschool.vehicleordering.business.Order;
import com.nttdata.emea.devschool.vehicleordering.business.OrderDAO;
import com.nttdata.emea.devschool.vehicleordering.business.Type;
import com.nttdata.emea.devschool.vehicleordering.business.TypeDAO;

@SessionScoped
@ManagedBean(name="indexBB")
public class IndexBB
{
	@EJB TypeDAO typeDao;
	@EJB ModelDAO modelDao;
	@EJB CustomerDAO customerDao;
	@EJB OrderDAO ordersDao;
	
	public String createDummyContent ()
	{
		Customer customer1 = new Customer("Jon", "Doe");
		Customer customer2 = new Customer("Jane", "Dove");
		Customer customer3 = new Customer("Jane", "Doe");
		customerDao.persist(customer1);
		customerDao.persist(customer2);
		customerDao.persist(customer3);
		
		Type type1 = new Type("Dreiraeder");
		Type type2 = new Type("Speed-Boats");
		typeDao.persist(type1);
		typeDao.persist(type2);
		
		String loremIpsum = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.";
		
		Model model1 = new Model("Todestreter Treitausend", type1, "Holy Cow, das Ding geht ab! " + loremIpsum, "http://www.bikeonlineshop.de/images/pukydreiradlillifee.jpg", 10000);
		Model model2 = new Model("Tripletastic", type1, "Da legts di nieder! " + loremIpsum, "http://www.kokua-shop.com/WebRoot/Store7/Shops/62501574/511A/120B/8AA8/526D/41C6/C0A8/28BB/A79B/LIKEaBIKE_midi_Dreirad_klein.jpg", 9999);
		Model model3 = new Model("Gammelkanu", type2, "Meh, nicht so knorke. " + loremIpsum, "http://demos.appthemes.com/classipress/files/2012/06/656479.jpg", 10000000);
		Model model4 = new Model("Bremser-Boye", type2, "Da schwimmt man schneller. " + loremIpsum, "http://tzach.edublogs.org/files/2011/01/speedBoat-szx5d2.jpg", 8000000);
		modelDao.persist(model1);
		modelDao.persist(model2);
		modelDao.persist(model3);
		modelDao.persist(model4);
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DAY_OF_MONTH, 24);
		Date deliveryDate = calendar.getTime();
		
		Order order1 = new Order(customer1, model1, 6, deliveryDate);
		Order order2 = new Order(customer1, model2, 1, deliveryDate);
		Order order3 = new Order(customer2, model1, 5, deliveryDate);
		Order order4 = new Order(customer2, model3, 2, deliveryDate);
		Order order5 = new Order(customer3, model2, 4, deliveryDate);
		Order order6 = new Order(customer3, model4, 3, deliveryDate);
		ordersDao.persist(order1);
		ordersDao.persist(order2);
		ordersDao.persist(order3);
		ordersDao.persist(order4);
		ordersDao.persist(order5);
		ordersDao.persist(order6);
		
		return "index";
	}

}