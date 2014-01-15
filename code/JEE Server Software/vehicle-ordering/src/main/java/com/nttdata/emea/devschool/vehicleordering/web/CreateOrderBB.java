package com.nttdata.emea.devschool.vehicleordering.web;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.nttdata.emea.devschool.vehicleordering.business.Model;
import com.nttdata.emea.devschool.vehicleordering.business.ModelDAO;
import com.nttdata.emea.devschool.vehicleordering.business.OrderBS;

@RequestScoped
@ManagedBean(name="createOrderBB")
public class CreateOrderBB
{
	private final static Logger LOG = Logger.getLogger(CreateOrderBB.class.getName());
	
	@EJB private ModelDAO modelDao;
	
	private String firstName;
	private String lastName;
	private int amount;
	private Date deliveryDate;
	private Long modelId;
	
	private List<Model> availableModels;
	
	@ManagedProperty(value="#{searchOrdersBB}")
	private SearchOrdersBB searchOrdersBB;
	
	@EJB private OrderBS orderBS;
	
	@PostConstruct
	public void initAvailableModels ()
	{
		availableModels = modelDao.findByType(null);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Long getModelId() {
		return modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public List<Model> getAvailableModels () {
		return availableModels;
	}
	public void setAvailableModels (List<Model> availableModels) {
		this.availableModels = availableModels;
	}
	
	public void setSearchOrdersBB(SearchOrdersBB searchOrdersBB) {
		this.searchOrdersBB = searchOrdersBB;
	}
	
	public String createOrder()
	{
		LOG.info("Ordered " + amount + " items for " + firstName + " " + lastName + " for the " + deliveryDate.toString() + "!");
		
		Model model = modelDao.findById(modelId);
		orderBS.createOrder(firstName, lastName, amount, deliveryDate, model);
		
		searchOrdersBB.setFirstName(firstName);
		searchOrdersBB.setLastName(lastName);
		
		return "searchOrders";
	}
}
